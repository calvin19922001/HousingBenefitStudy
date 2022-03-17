package hk.gov.housingauthority.nhs.vettingcheck.rules;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.util.RuleUtils;

/**
 * PrincipalAgeCheckRule recommends "reject" if the principal member aged below
 * 18 on the phase's application closing date
 * 
 * @author BRE Revamp POC Team
 *
 */
public class PrincipalAgeCheckRule extends EligibilityCheckRule {

	/**
	 * @param phase               Phase of the application
	 * @param application         Application for checking
	 * @param currentBusinessDate Current business date of the system
	 * @return true if the principal member aged below 18 on the phase's application
	 *         closing date
	 */
	@Override
	protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, Date currentBusinessDate) {

		ApplicationMemberVO principalMember = RuleUtils.getPrincipalMemberOfApplication(application);
		Date principalDOB = RuleUtils.convertDOBStringToDate(principalMember.getDateOfBirth());
		Date applicationEndDate = phase.getApplicationEndDate();

		// Return true if DOB of Principal + 18 years is later than the application
		// closing date
		return DateUtils.addYears(principalDOB, 18).after(applicationEndDate);
	}

	/**
	 * @return result for the recommendation if {@link criteriaMet} returns true
	 */
	@Override
	protected String getResultStatusIfCriteriaMet() {
		return Recommendation.RESULT__REJECTED;
	}
}
