package hk.gov.housingauthority.nhs.vettingcheck.rules;

import java.text.MessageFormat;
import java.util.Date;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.util.RecommendationUtils;

/**
 * The Eligibility rule determines the eligibility of an application by looking
 * at the recommendation
 * 
 * @author BRE Revamp POC Team
 *
 */
public abstract class EligibilityCheckRule {
	/**
	 * @param phase               Phase of the application
	 * @param application         Application for checking
	 * @param currentBusinessDate Current business date of the system
	 * @throws Throwable Exception encountered when determining if the criteria is
	 *                   met
	 * @return true if the application is identified with issue (i.e. result =
	 *         "reject" or "follow up") when performing the eligibility check
	 */
	protected abstract boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, Date currentBusinessDate)
			throws Throwable;

	/**
	 * Rule ID of the this eligibility check rule
	 */
	protected String ruleId;

	/**
	 * @return the {@link #ruleId}
	 */
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId the {@link #ruleId} to set
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return result for the recommendation if {@link criteriaMet} returns true
	 */
	protected abstract String getResultStatusIfCriteriaMet();

	/**
	 * @param phase               Phase of the application
	 * @param application         Application for checking
	 * @param currentBusinessDate Current business date of the system
	 * @return message for the recommendation if {@link criteriaMet} returns true
	 */
	protected String getMessage(PhaseVo phase, MaintainApplicationVO application, Date currentBusinessDate) {
		return RecommendationUtils.getMessage(getRuleId());
	}

	/**
	 * @param phase               Phase of the application
	 * @param application         Application for checking
	 * @param currentBusinessDate Current business date of the system
	 * 
	 * @return a recommendation object if {@link #criteriaMet} returns true; otherwise
	 *         return null
	 */
	public Recommendation getRecommendation(PhaseVo phase, MaintainApplicationVO application,
			Date currentBusinessDate) {
		Recommendation recommendation = null;

		try {
			if (criteriaMet(phase, application, currentBusinessDate) == true) {
				recommendation = new Recommendation();
				recommendation.setResult(getResultStatusIfCriteriaMet());
				recommendation.setMessage(getMessage(phase, application, currentBusinessDate));
				if (getRuleId().equals("VET-EGBL-0005")) {
					recommendation.setMessage(MessageFormat.format(recommendation.getMessage(), 
							application.getApplicationMemberList().get(1).getIdTypeCode() + " " + 
							application.getApplicationMemberList().get(1).getHkid() + "."));
				}
				recommendation.setRuleId(getRuleId());
			}
		} catch (Throwable t) {
			recommendation = new Recommendation();
			recommendation.setMessage(t.getMessage());

		}

		return recommendation;
	}

}
