package hk.gov.housingauthority.nhs.rules.test.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.housingbenefit.check.library.HousingBenefitCheckRuleLibrary;
import hk.gov.housingauthority.nhs.housingbenefit.check.library.HousingBenefitCheckRuleListForType;
import hk.gov.housingauthority.nhs.housingbenefit.check.library.HousingBenefitCheckRuleMap;
import hk.gov.housingauthority.nhs.housingbenefit.check.rules.HousingBenefitCheckRule;
import hk.gov.housingauthority.nhs.housingbenefit.datadict.HousingBenefitDataDictionary;
import hk.gov.housingauthority.nhs.housingbenefit.datadict.library.HousingBenefitDataDictionaryLibrary;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.dao.housingbenefit.HousingBenefitDao;
import hk.gov.housingauthority.nhs.rules.util.RecommendationUtils;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefitField;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefitMember;

public class HousingBenefitTestUtils {
	public static String getHousingBenefitDataDictionaryVersion(String phaseCode) {
		String dictionaryVersion = "";

		if (phaseCode.equals("019")) {
			dictionaryVersion = "BNFT_DICT_019";
		}

		return dictionaryVersion;
	}

	public static String getHousingBenefitCheckVersion(String phaseCode) {
		String ruleVersion = "";

		if (phaseCode.equals("019")) {
			ruleVersion = "BNFT_CHECK_019";
		}

		return ruleVersion;
	}

	/**
	 * This function is for simulating the web-service call for EMMS
	 */
	public static String getDuplicatedReportMasterKeyForMember(ApplicationMemberVO member,
			HousingBenefitDao housingBenefitDao) {

		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("memberIdType", member.getIdTypeCode());
		parameters.put("memberIdNumber", StringUtils.defaultIfBlank(member.getHkid(), member.getCertificateNum()));

		String duplicatedReportMasterKey = housingBenefitDao.getDuplicatedReportMasterKeyByMemberId(parameters);
		return duplicatedReportMasterKey;
	}

	public static List<HousingBenefit> loadHousingBenefitListWithMappedFields(String duplicatedReportMasterKey,
			String mapperMode, HousingBenefitDataDictionary dictionary, HousingBenefitDao housingBenefitDao) {

		System.out.println("========================================================");
		System.out.println("duplicatedReportMasterKey = " + duplicatedReportMasterKey);

		List<HousingBenefit> housingBenefitList = housingBenefitDao
				.getHousingBenefitListByMasterReferenceKey(duplicatedReportMasterKey);
		housingBenefitList = dictionary.filterHousingBenefitListForMappingMode(housingBenefitList, mapperMode);

		for (HousingBenefit housingBenefit : housingBenefitList) {
			// Step 1: Load required data from database
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("duplicatedReportMasterKey", housingBenefit.getDuplicatedReportMasterKey());
			parameters.put("reportMasterReference", housingBenefit.getReportMasterReference());
			parameters.put("masterListId", housingBenefit.getMasterListId());
			Map<String, String> rawFieldMapForBenefit = housingBenefitDao
					.selectRawFieldMapForHousingBenefit(parameters);
			List<Map<String, String>> rawFieldMapForMemberList = housingBenefitDao
					.selectRawFieldMapForHousingBenefitMember(parameters);

			// Step 2: Perform field mapping
			dictionary.mapFields(housingBenefit, rawFieldMapForBenefit, rawFieldMapForMemberList);

			// Step 3: Print the details of benefit for debug purpose
			System.out.println("--------------------------------------------------------");
			System.out.println("duplicatedReportMasterKey = " + housingBenefit.getDuplicatedReportMasterKey());
			System.out.println("reportMasterReference = " + housingBenefit.getReportMasterReference());
			System.out.println("masterListId = " + housingBenefit.getMasterListId());
			System.out.println("benefitTypeCode = " + housingBenefit.getBenefitTypeCode());
			System.out.println("benefitReferenceNumber = " + housingBenefit.getBenefitReferenceNumber());
			System.out.println("benefitMemberIdTypeCode = " + housingBenefit.getBenefitMemberIdTypeCode());
			System.out.println("benefitMemberIdNumber = " + housingBenefit.getBenefitMemberIdNumber());
			System.out.println("fieldMap: ");
			if (housingBenefit.getFieldMap() != null) {
				for (Map.Entry<String, HousingBenefitField> entry : housingBenefit.getFieldMap().entrySet()) {
					System.out.println(
							String.format("... %1$s (key: %2$s): %3$s (for display: %4$s, for discrepancy check: %5$s)",
									entry.getValue().getDisplayName(), entry.getKey(), entry.getValue().getValue(),
									(entry.getValue().isForDisplay() ? "Y" : "N"),
									(entry.getValue().isForDiscrepancyCheck() ? "Y" : "N")));
				}
			}

			// Step 3: Print the details of benefit member(s) for debug purpose
			System.out.println("memberList: ");
			if (housingBenefit.getMemberList() != null) {
				for (HousingBenefitMember member : housingBenefit.getMemberList()) {
					System.out.println("... member: " + member);
					for (Map.Entry<String, HousingBenefitField> entry : member.getFieldMap().entrySet()) {
						System.out.println(String.format(
								"... ... %1$s (key: %2$s): %3$s (for display: %4$s, for discrepancy check: %5$s)",
								entry.getValue().getDisplayName(), entry.getKey(), entry.getValue().getValue(),
								(entry.getValue().isForDisplay() ? "Y" : "N"),
								(entry.getValue().isForDiscrepancyCheck() ? "Y" : "N")));
					}
				}
			}
		}

		return housingBenefitList;
	}

	public static Recommendation runHousingBenefitCheckForApplication(PhaseVo phase, MaintainApplicationVO application,
			HousingBenefitDao housingBenefitDao,
			HousingBenefitDataDictionaryLibrary housingBenefitDataDictionaryLibrary,
			HousingBenefitCheckRuleLibrary housingBenefitCheckRuleLibrary) {

		String dictionaryVersion = HousingBenefitTestUtils.getHousingBenefitDataDictionaryVersion(phase.getPhaseCode());
		HousingBenefitDataDictionary housingBenefitDataDictionary = housingBenefitDataDictionaryLibrary
				.getDictionaryByDictionaryVersion(dictionaryVersion);

		String ruleVersion = HousingBenefitTestUtils.getHousingBenefitCheckVersion(phase.getPhaseCode());
		HousingBenefitCheckRuleMap housingBenefitCheckRuleMap = housingBenefitCheckRuleLibrary
				.getRuleMapByRuleVersion(ruleVersion);

		Recommendation housingBenefitCheckRecommendation = new Recommendation();
		housingBenefitCheckRecommendation.setApplicationKey(application.getApplicationKey());
		housingBenefitCheckRecommendation.setChildRecommendationList(new ArrayList<Recommendation>());
		housingBenefitCheckRecommendation.setMessage(RecommendationUtils.getMessage("VET-BNFT-HDR-0001"));

		for (ApplicationMemberVO member : application.getApplicationMemberList()) {
			Recommendation memberRecommendation = runHousingBenefitCheckForMember(phase, application, member,
					housingBenefitDao, housingBenefitDataDictionary, housingBenefitCheckRuleMap);
			housingBenefitCheckRecommendation.getChildRecommendationList().add(memberRecommendation);
			memberRecommendation.setParent(housingBenefitCheckRecommendation);
		}

		RecommendationUtils.computeResultForRecommendation(housingBenefitCheckRecommendation);

		return housingBenefitCheckRecommendation;

	}

	protected static Recommendation runHousingBenefitCheckForMember(PhaseVo phase, MaintainApplicationVO application,
			ApplicationMemberVO member, HousingBenefitDao housingBenefitDao,
			HousingBenefitDataDictionary housingBenefitDataDictionary,
			HousingBenefitCheckRuleMap housingBenefitCheckRuleMap) {
		Recommendation memberRecommendation = new Recommendation();
		memberRecommendation.setChildRecommendationList(new ArrayList<Recommendation>());
		memberRecommendation.setApplicationKey(application.getApplicationKey());
		memberRecommendation.setMemberIdTypeCode(member.getIdTypeCode());
		memberRecommendation.setMemberHkid(member.getHkid());
		memberRecommendation.setMemberCertificateNum(member.getCertificateNum());
		memberRecommendation.setMessage(RecommendationUtils.getMessage("VET-BNFT-HDR-0002",
				StringUtils.defaultIfBlank(member.getIdTypeCode(), "IC not provided"),
				StringUtils.defaultIfBlank(member.getHkid(), StringUtils.EMPTY),
				StringUtils.defaultIfBlank(member.getCertificateNum(), StringUtils.EMPTY)));

		String duplicatedReportMasterKey = HousingBenefitTestUtils.getDuplicatedReportMasterKeyForMember(member,
				housingBenefitDao);
		List<HousingBenefit> housingBenefitList = HousingBenefitTestUtils.loadHousingBenefitListWithMappedFields(
				duplicatedReportMasterKey, HousingBenefitDataDictionary.MAPPER_MODE__VETTING,
				housingBenefitDataDictionary, housingBenefitDao);

		if (housingBenefitList.size() == 0) {
			memberRecommendation.getChildRecommendationList()
					.add(RecommendationUtils.createNoHousingBenefitFoundRecommendation(application, member));
		} else {
			for (HousingBenefit housingBenefit : housingBenefitList) {
				Recommendation benefitRecommendation = runHousingBenefitCheckForHousingBenefit(phase, application,
						member, housingBenefit, housingBenefitCheckRuleMap);
				memberRecommendation.getChildRecommendationList().add(benefitRecommendation);
			}
		}

		return memberRecommendation;

	}

	protected static Recommendation runHousingBenefitCheckForHousingBenefit(PhaseVo phase,
			MaintainApplicationVO application, ApplicationMemberVO member, HousingBenefit benefit,
			HousingBenefitCheckRuleMap housingBenefitCheckRuleMap) {
		Recommendation benefitRecommendation = new Recommendation();
		benefitRecommendation.setChildRecommendationList(new ArrayList<Recommendation>());
		benefitRecommendation.setApplicationKey(application.getApplicationKey());
		benefitRecommendation.setMemberIdTypeCode(member.getIdTypeCode());
		benefitRecommendation.setMemberHkid(member.getHkid());
		benefitRecommendation.setMemberCertificateNum(member.getCertificateNum());
		benefitRecommendation
				.setMessage(RecommendationUtils.getMessage("VET-BNFT-HDR-0003", benefit.getReportMasterReference()));
		benefitRecommendation.setHousingBenefit(benefit);
		benefitRecommendation.setDuplicatedReportMasterKey(benefit.getDuplicatedReportMasterKey());
		benefitRecommendation.setReportMasterReference(benefit.getReportMasterReference());
		benefitRecommendation.setMasterListId(benefit.getMasterListId());

		HousingBenefitCheckRuleListForType housingBenefitCheckRuleListForType = housingBenefitCheckRuleMap
				.getHousingBenefitCheckRuleListForType(benefit.getBenefitTypeCode());

		for (HousingBenefitCheckRule rule : housingBenefitCheckRuleListForType.getHousingBenefitCheckRuleList()) {
			Recommendation recommendation = rule.getRecommendation(phase, application, member, benefit);
			if (recommendation != null) {
				benefitRecommendation.getChildRecommendationList().add(recommendation);
			}
		}

		if (benefitRecommendation.getChildRecommendationList().size() == 0) {
			benefitRecommendation.getChildRecommendationList()
					.add(RecommendationUtils.createAllAcceptRecommendation(application, member, benefit));
		}

		return benefitRecommendation;
	}

	public static void printRecommendation(Recommendation recommendation, String indent) {
		System.out.println(String.format("%1$s[%2$s] %3$s [%4$s]", indent,
				StringUtils.defaultIfBlank(recommendation.getRuleId(), "-"), recommendation.getMessage(),
				recommendation.getResult()));

		if (recommendation.getChildRecommendationList() != null) {
			for (Recommendation childRecommendation : recommendation.getChildRecommendationList()) {
				printRecommendation(childRecommendation, indent + "... ");
			}
		}
	}

	public static void verifyRecommendation(String recommendationDescription, Recommendation recommendation,
			String ruleId, String message, String result) {
		if (StringUtils.isNotBlank(ruleId)) {
			Assert.assertTrue(String.format("The rule ID for recommendation of [%1$s] is '%2$s'", recommendationDescription, ruleId),
					ruleId.equals(recommendation.getRuleId()));
		}
		Assert.assertTrue(String.format("The message for recommendation of [%1$s] is '%2$s'", recommendationDescription, message),
				message.equals(recommendation.getMessage()));
		Assert.assertTrue(String.format("The result for recommendation of [%1$s] is '%2$s'", recommendationDescription, result),
				result.equals(recommendation.getResult()));

	}
}
