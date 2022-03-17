package hk.gov.housingauthority.nhs.categorisation.rules;

import java.util.List;

import hk.gov.housingauthority.nhs.categorisation.criteria.Criteria;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;

/**
 * The categorisation rule determines the categories of an application by
 * looking at the preset list of criteria
 * 
 * @author BRE Revamp POC Team
 *
 */
public class CategorisationRule {

	/**
	 * List of criteria for determining if the categorisation rule should be applied
	 * to the application
	 */
	protected List<Criteria> criteriaList;
	/**
	 * The category code to be assigned to the application if the list of criteria
	 * is met
	 */
	protected String categoryCode;

	/**
	 * @return the {@link #criteriaList}
	 */
	protected List<Criteria> getCriteriaList() {
		return criteriaList;
	}

	/**
	 * @param criteriaList the {@link #criteriaList} to set
	 */
	public void setCriteriaList(List<Criteria> criteriaList) {
		this.criteriaList = criteriaList;
	}

	/**
	 * @return the {@link #categoryCode}
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @param categoryCode the {@link #categoryCode} to set
	 */
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	/**
	 * @param application
	 * @return true if all {@link Criteria} in the list is met
	 */
	public boolean allCriteriaMet(MaintainApplicationVO application) {
		boolean flag = true;
		for (Criteria criteria : criteriaList) {
			flag = flag && criteria.met(application);
		}
		return flag;
	}

}
