package hk.gov.housingauthority.nhs.categorisation.criteria;

import java.util.List;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.rules.util.RuleUtils;

/**
 * Categorisation criteria for checking the family type
 * 
 * @author BRE Revamp POC Team
 *
 */
public class FamilyTypeCriteria implements Criteria {
	/**
	 * List of accepted family type by this criteria
	 */
	protected List<String> familyTypeList;

	 /**
	 * @return the {@link #familyTypeList}
	 */
	public List<String> getFamilyTypeList() {
		return familyTypeList;
	}

	 /**
	  * @param familyTypeList the {@link #familyTypeList} to set
	  */
	public void setFamilyTypeList(List<String> familyTypeList) {
		this.familyTypeList = familyTypeList;
	}

	/**
	 * @return true if the application’s family type is included in
	 *         {@link #familyTypeList}
	 */
	public boolean met(MaintainApplicationVO application) {
		String familyType = RuleUtils.determineFamilyTypeOfApplication(application);
		return familyTypeList.contains(familyType);

	}

}
