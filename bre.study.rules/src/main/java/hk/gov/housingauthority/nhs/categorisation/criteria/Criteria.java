package hk.gov.housingauthority.nhs.categorisation.criteria;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;

/**
 * The interface class for criteria for determine the categories of an
 * application
 * 
 * @author BRE Revamp POC Team
 *
 */
public interface Criteria {
	/**
	 * @param application
	 * @return true if the application meets the defined criteria
	 */
	boolean met(MaintainApplicationVO application);
}
