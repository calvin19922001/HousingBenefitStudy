package hk.gov.housingauthority.nhs.vettingcheck.rules;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.util.RuleUtils;

/**
 * FEPFamilyAgeCheckRule recommends "reject" if FEP indicator = "Y" and no
 * member aged 60 or over on the phase’s application closing date
 * 
 * @author BRE Revamp POC Team
 *
 */
public class FEPFamilyAgeCheckRule extends EligibilityCheckRule {

	/**
	 * @param phase               Phase of the application
	 * @param application         Application for checking
	 * @param currentBusinessDate Current business date of the system
	 * @return true if FEP indicator = "Y" and no member aged 60 or over on the
	 *         phase’s application closing date
	 * 
	 */
	@Override
	protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, Date currentBusinessDate) {

		Date applicationEndDate = phase.getApplicationEndDate();
		List<ApplicationMemberVO> memberList = application.getApplicationMemberList();
		boolean checkFlag = false;

		if (application.getJoinElderlyMemberScheme().equals("Y")) {
			for (ApplicationMemberVO member : memberList) {
				Date memberDOB = RuleUtils.convertDOBStringToDate(member.getDateOfBirth());
				// Return true if DOB of no member + 60 years is later than the application
				// closing date
				if (DateUtils.addYears(memberDOB, 60).after(applicationEndDate)) {
					checkFlag = true;
				} else { // case for equal and over 60 years
					checkFlag = false;
					break;
				}
			}
		} else {
			checkFlag = false;
		}
		return checkFlag;
	}

	/**
	 * @return result for the recommendation if {@link criteriaMet} returns true
	 */
	@Override
	protected String getResultStatusIfCriteriaMet() {
		return Recommendation.RESULT__REJECTED;
	}
}