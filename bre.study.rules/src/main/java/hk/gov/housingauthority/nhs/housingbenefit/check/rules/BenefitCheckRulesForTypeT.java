package hk.gov.housingauthority.nhs.housingbenefit.check.rules;

import org.apache.commons.lang3.StringUtils;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;

public class BenefitCheckRulesForTypeT {

	public static class TenancyAbuserNotBlankCheckRule extends HousingBenefitCheckRule {

		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {
			return (application.getApplicationFormColor().equals("G") && 
					StringUtils.isNotBlank(benefit.getFieldMap().get("tenancyAbuser").getValue()));
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__FOLLOW_UP;
		}
	}

	public static class HouseholdScoreCheckRule extends HousingBenefitCheckRule {
		
		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {
			return (application.getApplicationFormColor().equals("G") && 
					Integer.parseInt(benefit.getFieldMap().get("householdScore").getValue().trim()) >= 16);
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__FOLLOW_UP;
		}
		
	}
	
	public static class NTQExpiryDateCheckRule extends HousingBenefitCheckRule {

		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {
			return (application.getApplicationFormColor().equals("G") && 
					StringUtils.isNotBlank(benefit.getFieldMap().get("ntqExpiryDate").getValue()));
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__FOLLOW_UP;
		}
	}
	
	public static class HouseholdDebarNotBlankCheckRule extends HousingBenefitCheckRule {

		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {
			return (application.getApplicationFormColor().equals("G") && 
					StringUtils.isNotBlank(benefit.getFieldMap().get("householdDebar").getValue()));
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__FOLLOW_UP;
		}
	}
	
	public static class HouseholdMatchCheckRule extends HousingBenefitCheckRule {

		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {
			return (application.getApplicationFormColor().equals("G") && 
					application.getApplicationMemberList().size() != benefit.getMemberList().size());
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__FOLLOW_UP;
		}
	}
	
	public static class MembersMaritalStatusCheckRule extends HousingBenefitCheckRule {

		@Override
		protected boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
				HousingBenefit benefit) throws Throwable {
			return (application.getApplicationFormColor().equals("G") && 
					!member.getMaritalStatus().equals(benefit.getMemberList().get(application.getApplicationMemberList().indexOf(member)).getFieldMap().get("maritalSatus").getValue()));
		}

		@Override
		protected String resultStatusIfCriteriaMet() {
			return Recommendation.RESULT__FOLLOW_UP;
		}
	}
		
	
}
