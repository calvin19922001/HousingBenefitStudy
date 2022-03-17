package hk.gov.housingauthority.nhs.categorisation.criteria;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;

/**
 * Categorisation criteria for checking the FEP indicator
 * 
 * @author BRE Revamp POC Team
 *
 */
public class JoinFepCriteria implements Criteria {

	/**
	 * @return true if the application is opt for joining FEP
	 */
	public boolean met(MaintainApplicationVO application) {
		return application.getJoinElderlyMemberScheme().equals("Y");
	}

}
