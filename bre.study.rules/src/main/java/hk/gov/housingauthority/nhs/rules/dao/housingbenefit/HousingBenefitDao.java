package hk.gov.housingauthority.nhs.rules.dao.housingbenefit;

import java.util.List;
import java.util.Map;

import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;

public interface HousingBenefitDao {
	List<HousingBenefit> getHousingBenefitListByMasterReferenceKey(String duplicatedReportMasterKey);

	Map<String, String> selectRawFieldMapForHousingBenefit(Map<String, String> parameters);

	List<Map<String, String>> selectRawFieldMapForHousingBenefitMember(Map<String, String> parameters);
	
	String getDuplicatedReportMasterKeyByMemberId(Map<String, String> parameters);

}
