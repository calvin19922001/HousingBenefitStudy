package hk.gov.housingauthority.nhs.housingbenefit.check.library;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.housingbenefit.check.rules.HousingBenefitCheckRule;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.util.RecommendationUtils;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;

public class HousingBenefitCheckRuleListForType {

	protected List<HousingBenefitCheckRule> housingBenefitCheckRuleList;

	public List<HousingBenefitCheckRule> getHousingBenefitCheckRuleList() {
		return housingBenefitCheckRuleList;
	}

	public void setHousingBenefitCheckRuleList(List<HousingBenefitCheckRule> housingBenefitCheckRuleList) {
		this.housingBenefitCheckRuleList = housingBenefitCheckRuleList;
	}

	public Recommendation getRecommendationForHousingBenefit(PhaseVo phase, MaintainApplicationVO application,
			ApplicationMemberVO member, HousingBenefit benefit) {

		Recommendation benefitRecommendation = new Recommendation();
		benefitRecommendation.setApplicationKey(application.getApplicationKey());
		benefitRecommendation.setMemberIdTypeCode(member.getIdTypeCode());
		benefitRecommendation.setMemberHkid(member.getHkid());
		benefitRecommendation.setMemberCertificateNum(member.getCertificateNum());
		benefitRecommendation.setHousingBenefit(benefit);
		benefitRecommendation.setDuplicatedReportMasterKey(benefit.getDuplicatedReportMasterKey());
		benefitRecommendation.setReportMasterReference(benefit.getReportMasterReference());
		benefitRecommendation.setMasterListId(benefit.getMasterListId());
		benefitRecommendation.setMessage(String.format("Recommendation for %1$s (Member: %2$s %3$s)",
				benefit.getReportMasterReference(), StringUtils.defaultIfBlank(member.getIdTypeCode(), "[ID Type and Number not provided]"),
				StringUtils.isNotBlank(member.getHkid()) ? member.getHkid()
						: StringUtils.defaultIfBlank(member.getCertificateNum(), "")));

		for (HousingBenefitCheckRule rule : housingBenefitCheckRuleList) {
			Recommendation recommendation = rule.getRecommendation(phase, application, member, benefit);
			if (recommendation != null) {
				benefitRecommendation.getChildRecommendationList().add(recommendation);
			}
		}

		if (benefitRecommendation.getChildRecommendationList().isEmpty()) {
			benefitRecommendation.getChildRecommendationList()
					.add(RecommendationUtils.createAllAcceptRecommendation(application, member, benefit));
		}

		return benefitRecommendation;
	}
}