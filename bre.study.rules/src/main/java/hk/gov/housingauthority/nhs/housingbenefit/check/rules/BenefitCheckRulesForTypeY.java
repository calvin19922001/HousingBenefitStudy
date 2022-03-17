package hk.gov.housingauthority.nhs.housingbenefit.check.rules;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;

public class BenefitCheckRulesForTypeY {
	
	public static class GreenFormTPSOwnerCheckRule extends HousingBenefitCheckRule {

		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {
			return (application.getApplicationFormColor().equals("G") && 
					(benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("ownerInd").getValue().equals("O") || 
					 benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("ownerInd").getValue().equals("E") || 
					 benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("ownerInd").getValue().equals("J")));
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__REJECTED;
		}
		
	}
	
	public static class GreenFormSpouseOfTPSOwnerCheckRule extends HousingBenefitCheckRule {

		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {			
			return (application.getApplicationFormColor().equals("G") && 
					(benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("relationship").getValue().equals("H") || 
					 benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("relationship").getValue().equals("W")));
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__REJECTED;
		}
		
	}
	
	public static class WhiteFormTPSOwnerCheckRule extends HousingBenefitCheckRule {

		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {
			Date applicationEndDate = phase.getApplicationEndDate();
			return (application.getApplicationFormColor().equals("W") && 
					(benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("ownerInd").getValue().equals("O") || 
					 benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("ownerInd").getValue().equals("E") || 
					 benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("ownerInd").getValue().equals("J")) && 
					(DateUtils.addYears(DateUtils.parseDate(benefit.getFieldMap().get("dateOfAssignment").getValue(), "dd/MM/yyyy HH:mm:ss"), 10).before(applicationEndDate) || 
					 DateUtils.addYears(DateUtils.parseDate(benefit.getFieldMap().get("dateOfAssignment").getValue(), "dd/MM/yyyy HH:mm:ss"), 10).equals(applicationEndDate)));
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__REJECTED;
		}
		
	}
	
	public static class WhiteFormSpouseOfTPSOwnerCheckRule extends HousingBenefitCheckRule {

		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {
			Date applicationEndDate = phase.getApplicationEndDate();
			System.out.println("Relationship : " + benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("relationship").getValue());
			return (application.getApplicationFormColor().equals("W") && 
					(benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("relationship").getValue().equals("H") || 
					 benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("relationship").getValue().equals("W")) && 
					 (DateUtils.addYears(DateUtils.parseDate(benefit.getFieldMap().get("dateOfAssignment").getValue(), "dd/MM/yyyy HH:mm:ss"), 10).before(applicationEndDate) || 
					  DateUtils.addYears(DateUtils.parseDate(benefit.getFieldMap().get("dateOfAssignment").getValue(), "dd/MM/yyyy HH:mm:ss"), 10).equals(applicationEndDate)));
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__REJECTED;
		}
		
	}

}
