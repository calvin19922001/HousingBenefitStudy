package hk.gov.housingauthority.nhs.rules.test.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.util.RecommendationUtils;
import hk.gov.housingauthority.nhs.vettingcheck.library.EligibilityCheckRuleLibrary;
import hk.gov.housingauthority.nhs.vettingcheck.rules.EligibilityCheckRule;

/**
 * Utility functions for tests on the Eligibility Check
 * 
 * @author BRE Revamp POC Team
 *
 */
public class EligibilityTestUtils {

	/**
	 * This function is for simulating retrieving the rule version by phase code
	 * from the database
	 * 
	 * @param phaseCode Phase Code of the phase
	 * @return rule version of the input phase code
	 */
	public static String getEligibilityCheckRuleVersion(String phaseCode) {
		String ruleVersion = "";
	
		if (phaseCode.equals("019")) {
			ruleVersion = "EGBL_019";
		} else if (phaseCode.equals("WF19")) {
			ruleVersion = "EGBL_WF19";
		}
	
		return ruleVersion;
	}

	/**
	 * This function simulates the process of running the eligibility check.
	 * 
	 * @param phase               Phase of the application
	 * @param application         Application for checking
	 * @param currentBusinessDate Current business date of the system
	 * 
	 * @return recommendation of the eligibility check
	 */
	public static Recommendation runEligibilityCheck(PhaseVo phase, MaintainApplicationVO application,
			Date currentBusinessDate, EligibilityCheckRuleLibrary eligibilityCheckRuleLibrary) {
		String ruleVersion = getEligibilityCheckRuleVersion(application.getPhaseCode());
		List<EligibilityCheckRule> eligibilityCheckRuleList = eligibilityCheckRuleLibrary
				.getRuleListByRuleVersion(ruleVersion);
	
		Recommendation eligibilityCheckRecommendation = new Recommendation();
		List<Recommendation> recomendationChildList = new ArrayList<Recommendation>();
	
		for (EligibilityCheckRule rule : eligibilityCheckRuleList) {
			Recommendation recommendation = rule.getRecommendation(phase, application, currentBusinessDate);
			if (recommendation != null) {
				recomendationChildList.add(recommendation);
			}
		}
	
		if (recomendationChildList.isEmpty()) {
			recomendationChildList.add(RecommendationUtils.createAllAcceptRecommendation(application,
					null, null));
		}
		eligibilityCheckRecommendation.setChildRecommendationList(recomendationChildList);
		return eligibilityCheckRecommendation;
	}

}
