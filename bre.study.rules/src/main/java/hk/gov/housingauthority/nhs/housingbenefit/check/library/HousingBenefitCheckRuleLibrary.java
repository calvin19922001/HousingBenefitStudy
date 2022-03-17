package hk.gov.housingauthority.nhs.housingbenefit.check.library;

import java.util.Map;

public class HousingBenefitCheckRuleLibrary {

	protected Map<String, HousingBenefitCheckRuleMap> ruleMapByVersionMap;

	public Map<String, HousingBenefitCheckRuleMap> getRuleMapByVersionMap() {
		return ruleMapByVersionMap;
	}

	public void setRuleMapByVersionMap(Map<String, HousingBenefitCheckRuleMap> ruleMapByVersionMap) {
		this.ruleMapByVersionMap = ruleMapByVersionMap;
	}

	public HousingBenefitCheckRuleMap getRuleMapByRuleVersion(String ruleVersion) {
		return ruleMapByVersionMap.get(ruleVersion);
	}
}
