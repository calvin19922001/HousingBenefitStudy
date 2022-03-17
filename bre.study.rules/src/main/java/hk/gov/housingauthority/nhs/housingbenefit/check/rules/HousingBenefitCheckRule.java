package hk.gov.housingauthority.nhs.housingbenefit.check.rules;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.util.RecommendationUtils;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;

public abstract class HousingBenefitCheckRule {

	protected String ruleId;

	protected abstract boolean criteriaMet(PhaseVo phase, MaintainApplicationVO application, ApplicationMemberVO member,
			HousingBenefit benefit) throws Throwable;

	protected String getMessage() {
		return RecommendationUtils.getMessage(getRuleId());
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	protected abstract String resultStatusIfCriteriaMet();

	public Recommendation getRecommendation(PhaseVo phase, MaintainApplicationVO application,
			ApplicationMemberVO member, HousingBenefit benefit) {
		Recommendation recommendation = null;

		try {
			if (criteriaMet(phase, application, member, benefit)) {
				recommendation = new Recommendation();
				recommendation.setApplicationKey(application.getApplicationKey());
				recommendation.setMemberIdTypeCode(member.getIdTypeCode());
				recommendation.setMemberHkid(member.getHkid());
				recommendation.setMemberCertificateNum(member.getCertificateNum());
				recommendation.setHousingBenefit(benefit);
				recommendation.setDuplicatedReportMasterKey(benefit.getDuplicatedReportMasterKey());
				recommendation.setReportMasterReference(benefit.getReportMasterReference());
				recommendation.setMasterListId(benefit.getMasterListId());
				recommendation.setRuleId(getRuleId());
				recommendation.setResult(resultStatusIfCriteriaMet());
				recommendation.setMessage(getMessage());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			recommendation = new Recommendation();
			recommendation.setApplicationKey(application.getApplicationKey());
			recommendation.setMemberIdTypeCode(member.getIdTypeCode());
			recommendation.setMemberHkid(member.getHkid());
			recommendation.setMemberCertificateNum(member.getCertificateNum());
			recommendation.setHousingBenefit(benefit);
			recommendation.setDuplicatedReportMasterKey(benefit.getDuplicatedReportMasterKey());
			recommendation.setReportMasterReference(benefit.getReportMasterReference());
			recommendation.setMasterListId(benefit.getMasterListId());
			recommendation.setRuleId(getRuleId());
			recommendation.setResult(Recommendation.RESULT__ERROR);
			recommendation.setMessage("Error: " + e.getMessage());
		}

		return recommendation;
	}
}
