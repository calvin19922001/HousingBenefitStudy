package hk.gov.housingauthority.nhs.categorisation.library;

import java.util.List;
import java.util.Map;

import hk.gov.housingauthority.nhs.categorisation.rules.CategorisationRule;

/**
 * The categorisation rule library holds the list of categorisation rules by
 * rule version (depending on Phase)
 * 
 * @author BRE Revamp POC Team
 *
 */
public class CategorisationRuleLibrary {

	/**
	 * The rule list by version map contains rule version (i.e. version) as key and
	 * list of {@link CategorisationRule} (i.e. rule list) as value.
	 */
	protected Map<String, List<CategorisationRule>> ruleListByVersionMap;

	/**
	 * @return the {@link #ruleListByVersionMap}
	 */
	public Map<String, List<CategorisationRule>> getRuleListByVersionMap() {
		return ruleListByVersionMap;
	}

	/**
	 * @param ruleListByVersionMap the {@link #ruleListByVersionMap} to set
	 */
	public void setRuleListByVersionMap(Map<String, List<CategorisationRule>> ruleListByVersionMap) {
		this.ruleListByVersionMap = ruleListByVersionMap;
	}

	/**
	 * @param ruleVersion
	 * @return the rule list for the rule version by looking up the {@link #ruleListByVersionMap}
	 */
	public List<CategorisationRule> getRuleListByRuleVersion(String ruleVersion) {
		return ruleListByVersionMap.get(ruleVersion);
	}

}
