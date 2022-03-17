package hk.gov.housingauthority.nhs.rules.test.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;

import hk.gov.housingauthority.nhs.categorisation.library.CategorisationRuleLibrary;
import hk.gov.housingauthority.nhs.categorisation.rules.CategorisationRule;
import hk.gov.housingauthority.nhs.common.vo.assignApplication.PriorityInfo;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;

/**
 * Utility functions for tests on the Categorisation
 * 
 * @author BRE Revamp POC Team
 *
 */
public class CategorisationTestUtils {

	/**
	 * This function is for simulating retrieving the rule version by phase code
	 * from the database
	 * 
	 * @param phaseCode Phase Code of the phase
	 * @return rule version of the input phase code
	 */
	public static String getCategorisationRuleVersion(String phaseCode) {
		String ruleVersion = "";

		if (phaseCode.equals("019")) {
			ruleVersion = "CATG_019";
		} else if (phaseCode.equals("WF19")) {
			ruleVersion = "CATG_WF19";
		}

		return ruleVersion;
	}

	/**
	 * @param application  The application
	 * @param categoryCode Category code
	 * 
	 * @return true if the application is assigned with category code
	 */
	public static boolean containCategory(MaintainApplicationVO application, final String categoryCode) {
		return IterableUtils.matchesAny(application.getCategoryPriority(), new Predicate<PriorityInfo>() {
			@Override
			public boolean evaluate(PriorityInfo priorityInfo) {
				return priorityInfo.getCategoryCode().equals(categoryCode);
			}
		});
	}

	/**
	 * This function simulates the process of running the categorisation on the
	 * application.
	 * 
	 * @param categorisationRuleLibrary The categorisation rule library
	 * @param application               The application
	 */
	public static void runCategorisation(CategorisationRuleLibrary categorisationRuleLibrary,
			MaintainApplicationVO application) {
		String ruleVersion = getCategorisationRuleVersion(application.getPhaseCode());
		List<CategorisationRule> categorisationRuleList = categorisationRuleLibrary
				.getRuleListByRuleVersion(ruleVersion);

		List<PriorityInfo> categoryPriorityList = new ArrayList<PriorityInfo>();

		for (CategorisationRule rule : categorisationRuleList) {
			if (rule.allCriteriaMet(application)) {
				PriorityInfo priorityInfo = new PriorityInfo();
				priorityInfo.setCategoryCode(rule.getCategoryCode());
				categoryPriorityList.add(priorityInfo);
			}
		}

		application.setCategoryPriority(categoryPriorityList);
	}

}
