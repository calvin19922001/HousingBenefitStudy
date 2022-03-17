package hk.gov.housingauthority.nhs.vettingcheck.rules;

import java.util.Date;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.util.RuleUtils;

/**
 * GreenFormFEPFamilyCheckRule recommends "rejected" if form colour = "G" and
 * FEP indicator = "Y" and family type != "N" or "F"
 * 
 * @author BRE Revamp POC Team
 *
 */
public class GreenFormFEPFamilyCheckRule extends EligibilityCheckRule {

	/**
	 * @param phase               Phase of the application
	 * @param application         Application for checking
	 * @param currentBusinessDate Current business date of the system
	 * @return true if form colour = "G" and FEP indicator = "Y" and family type !=
	 *         "N" or "F"
	 */
	@Override
	protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, Date currentBusinessDate) {
		String familyType = RuleUtils.determineFamilyTypeOfApplication(application);
		return (application.getApplicationFormColor().equals("G")
				&& application.getJoinElderlyMemberScheme().equals("Y")
				&& !(familyType.equals("N") || familyType.equals("F")));
	}

	/**
	 * @return result for the recommendation if {@link criteriaMet} returns true
	 */
	@Override
	protected String getResultStatusIfCriteriaMet() {
		return Recommendation.RESULT__REJECTED;
	}
}
