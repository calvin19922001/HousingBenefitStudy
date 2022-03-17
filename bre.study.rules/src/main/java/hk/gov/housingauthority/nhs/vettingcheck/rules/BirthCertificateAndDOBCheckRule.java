package hk.gov.housingauthority.nhs.vettingcheck.rules;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.util.RecommendationUtils;
import hk.gov.housingauthority.nhs.rules.util.RuleUtils;

/**
 * BirthCertificateAndDOBCheckRule recommend the application for "follow up" if
 * exists member with age over 11 on the current business date and ID Type =
 * "BC".
 * 
 * @author BRE Revamp POC Team
 *
 */
public class BirthCertificateAndDOBCheckRule extends EligibilityCheckRule {

	/**
	 * @param phase               Phase of the application
	 * @param application         Application for checking
	 * @param currentBusinessDate Current business date of the system
	 * @return if exists member with age over 11 on the current business date and ID
	 *         Type = "BC".
	 */
	@Override
	protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, Date currentBusinessDate) {
		Date applicationEndDate = phase.getApplicationEndDate();
		List<ApplicationMemberVO> memberList = application.getApplicationMemberList();
		for (ApplicationMemberVO member : memberList) {
			Date memberDOB = RuleUtils.convertDOBStringToDate(member.getDateOfBirth());

			// Return true if DOB of Member + 11 years with Birth Certificate is before than
			// the application
			// closing date
			if (DateUtils.addYears(memberDOB, 11).before(applicationEndDate) && member.getIdTypeCode().equals("BC")) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param phase               Phase of the application
	 * @param application         Application for checking
	 * @param currentBusinessDate Current business date of the system
	 * @return message for the recommendation if {@link criteriaMet} returns true
	 */
	@Override
	protected String getMessage(PhaseVo phase, MaintainApplicationVO application, Date currentBusinessDate) {
		Date applicationEndDate = phase.getApplicationEndDate();
		List<ApplicationMemberVO> memberList = application.getApplicationMemberList();
		List<String> birthCertList = new ArrayList<String>();

		for (ApplicationMemberVO member : memberList) {
			Date memberDOB = RuleUtils.convertDOBStringToDate(member.getDateOfBirth());
			if (DateUtils.addYears(memberDOB, 11).before(applicationEndDate) && member.getIdTypeCode().equals("BC")) {
				birthCertList.add(member.getIdTypeCode() + " " + member.getCertificateNum());
			}
		}
		return RecommendationUtils.getMessage(getRuleId(), StringUtils.join(birthCertList, ", "));

	}

	/**
	 * @return result for the recommendation if {@link criteriaMet} returns true
	 */
	@Override
	protected String getResultStatusIfCriteriaMet() {
		return Recommendation.RESULT__FOLLOW_UP;
	}
}