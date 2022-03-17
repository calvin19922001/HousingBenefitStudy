package hk.gov.housingauthority.nhs.rules.util;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;

/**
 * Utility functions for the recommendation class
 * 
 * @author BRE Revamp POC Team
 *
 */
public class RecommendationUtils {

	/**
	 * Class path to the vetting rule message properties file
	 */
	protected static final String RULE_MESSAGE_PROPERTIES_PATH = "messages/vetting_rule_messages.properties";

	/**
	 * The {@link Propoerties} object holding the result after reading the
	 * properties file in path {@link #RULE_MESSAGE_PROPERTIES_PATH}
	 */
	protected static Properties descriptionProperties;

	/**
	 * Predicate used for filtering recommendation with result "rejected"
	 */
	protected static final Predicate<Recommendation> REJECT_RECOMMENDATION_PREDICATE = new Predicate<Recommendation>() {
		/**
		 * @param recommendation
		 * 
		 * @return true if the recommendation if the result is "rejected"
		 */
		@Override
		public boolean evaluate(Recommendation recommendation) {
			return Recommendation.RESULT__REJECTED.equals(recommendation.getResult());
		}
	};

	/**
	 * Predicate used for filtering recommendation with result "follow up"
	 */
	protected static final Predicate<Recommendation> FOLLOW_UP_RECOMMENDATION_PREDICATE = new Predicate<Recommendation>() {
		/**
		 * @param recommendation
		 * 
		 * @return true if the recommendation if the result is "follow up"
		 */
		@Override
		public boolean evaluate(Recommendation recommendation) {
			return Recommendation.RESULT__FOLLOW_UP.equals(recommendation.getResult());
		}
	};

	/**
	 * Compute the result recursively for the recommendation base on its children
	 * recommendation
	 * 
	 * @param recommendation
	 */
	public static void computeResultForRecommendation(Recommendation recommendation) {
		if (recommendation.getChildRecommendationList() != null) {
			for (Recommendation childRecommendation : recommendation.getChildRecommendationList()) {
				computeResultForRecommendation(childRecommendation);
			}

			if (IterableUtils.matchesAny(recommendation.getChildRecommendationList(),
					REJECT_RECOMMENDATION_PREDICATE)) {
				recommendation.setResult(Recommendation.RESULT__REJECTED);
			} else if (IterableUtils.matchesAny(recommendation.getChildRecommendationList(),
					FOLLOW_UP_RECOMMENDATION_PREDICATE)) {
				recommendation.setResult(Recommendation.RESULT__FOLLOW_UP);
			} else {
				recommendation.setResult(Recommendation.RESULT__ACCEPTED);
			}
		}
	}

	/**
	 * This function is for generating the recommendation when all vetting rules
	 * passed.
	 * 
	 * @param application The related application for the recommendation
	 * @param member      The related application member for the recommendation
	 * @param benefit     The related housing benefit for the recommendation
	 * 
	 * @return Recommendation object with result "accepted"
	 */
	public static Recommendation createAllAcceptRecommendation(MaintainApplicationVO application,
			ApplicationMemberVO member, HousingBenefit benefit) {
		Recommendation recommendation = new Recommendation();
		if (application != null) {
			recommendation.setApplicationKey(application.getApplicationKey());
		}
		if (member != null) {
			recommendation.setMemberIdTypeCode(member.getIdTypeCode());
			recommendation.setMemberHkid(member.getHkid());
			recommendation.setMemberCertificateNum(member.getCertificateNum());
		}
		if (benefit != null) {
			recommendation.setHousingBenefit(benefit);
			recommendation.setDuplicatedReportMasterKey(benefit.getDuplicatedReportMasterKey());
			recommendation.setReportMasterReference(benefit.getReportMasterReference());
			recommendation.setMasterListId(benefit.getMasterListId());
		}
		recommendation.setResult(Recommendation.RESULT__ACCEPTED);
		recommendation.setRuleId("VET-CMM-0001");
		recommendation.setMessage(getMessage(recommendation.getRuleId()));
		return recommendation;
	}

	/**
	 * @param ruleId    Rule ID for looking up the properties file
	 * @param arguments Optional arguments supply to the message
	 * 
	 * @return the generated message by looking up the messages in the properties
	 *         file {@link #RULE_MESSAGE_PROPERTIES_PATH} by ruleId and using the
	 *         arguments provided
	 */
	public static Recommendation createNoHousingBenefitFoundRecommendation(MaintainApplicationVO application,
			ApplicationMemberVO member) {
		Recommendation recommendation = new Recommendation();
		recommendation.setApplicationKey(application.getApplicationKey());
		recommendation.setMemberIdTypeCode(member.getIdTypeCode());
		recommendation.setMemberHkid(member.getHkid());
		recommendation.setMemberCertificateNum(member.getCertificateNum());
		recommendation.setResult(Recommendation.RESULT__ACCEPTED);
		recommendation.setRuleId("VET-BNFT-EMPTY");
		return recommendation;
	}

	/**
	 * @param ruleId    Rule ID for looking up the properties file
	 * @param arguments Optional arguments supply to the message
	 * 
	 * @return the generated message by looking up the messages in the properties
	 *         file {@link #RULE_MESSAGE_PROPERTIES_PATH} by ruleId and using the
	 *         arguments provided
	 */
	public static String getMessage(String ruleId, Object... arguments) {

		if (descriptionProperties == null) {
			try {
				descriptionProperties = new Properties();
				descriptionProperties
						.load(RuleUtils.class.getClassLoader().getResourceAsStream(RULE_MESSAGE_PROPERTIES_PATH));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String pattern = descriptionProperties.getProperty(ruleId);
		return MessageFormat.format(pattern, arguments);
	}
}
