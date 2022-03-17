package hk.gov.housingauthority.nhs.vettingcheck.library;

import java.util.List;
import java.util.Map;

import hk.gov.housingauthority.nhs.vettingcheck.rules.EligibilityCheckRule;

/**
 * The eligibility check rule library holds the list of eligibility check rules by
 * rule version (depending on Phase)
 * 
 * @author BRE Revamp POC Team
 *
 */
public class EligibilityCheckRuleLibrary {
	/**
	 * The rule list by version map contains rule version (i.e. version) as key and
	 * list of {@link EligibilityCheckRule} (i.e. rule list) as value.
	 */
	protected Map<String, List<EligibilityCheckRule>> ruleListByVersionMap;

	/**
	 * @return the {@link #ruleListByVersionMap}
	 */
	public Map<String, List<EligibilityCheckRule>> getRuleListByVersionMap() {
		return ruleListByVersionMap;
	}

	/**
	 * @param ruleListByVersionMap the {@link #ruleListByVersionMap} to set
	 */
	public void setRuleListByVersionMap(Map<String, List<EligibilityCheckRule>> ruleListByVersionMap) {
		this.ruleListByVersionMap = ruleListByVersionMap;
	}

	/**
	 * @param ruleVersion
	 * @return the rule list for the rule version by looking up the {@link #ruleListByVersionMap}
	 */
	public List<EligibilityCheckRule> getRuleListByRuleVersion(String ruleVersion) {
		return ruleListByVersionMap.get(ruleVersion);
	}
}
