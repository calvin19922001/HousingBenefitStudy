package hk.gov.housingauthority.nhs.housingbenefit.check.library;

import java.util.Map;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;

public class HousingBenefitCheckRuleMap {

	protected Map<String, HousingBenefitCheckRuleListForType> ruleMap;

	public Map<String, HousingBenefitCheckRuleListForType> getRuleMap() {
		return ruleMap;
	}

	public void setRuleMap(Map<String, HousingBenefitCheckRuleListForType> ruleMap) {
		this.ruleMap = ruleMap;
	}

	public HousingBenefitCheckRuleListForType getHousingBenefitCheckRuleListForType(String benefitType) {
		return ruleMap.get(benefitType);
	}

	public Recommendation getRecommendationForHousingBenefit(PhaseVo phase, MaintainApplicationVO application,
			ApplicationMemberVO member, HousingBenefit benefit) {

		Recommendation benefitRecommendation = null;

		if (ruleMap.containsKey(benefit.getBenefitTypeCode())) {
			benefitRecommendation = ruleMap.get(benefit.getBenefitTypeCode())
					.getRecommendationForHousingBenefit(phase, application, member, benefit);
		}
		return benefitRecommendation;
	}
}