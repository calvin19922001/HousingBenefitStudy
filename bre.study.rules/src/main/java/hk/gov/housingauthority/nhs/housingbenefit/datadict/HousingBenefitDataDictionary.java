package hk.gov.housingauthority.nhs.housingbenefit.datadict;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;

public class HousingBenefitDataDictionary {

	public final static String MAPPER_MODE__VETTING = "VETTING";
	public final static String MAPPER_MODE__ASP = "ASP";

	protected Map<String, HousingBenefitMapper> mapperMap;
	
	public Map<String, HousingBenefitMapper> getMapperMap() {
		return mapperMap;
	}

	public void setMapperMap(Map<String, HousingBenefitMapper> mapperMap) {
		this.mapperMap = mapperMap;
	}

	public List<HousingBenefit> filterHousingBenefitListForMappingMode(List<HousingBenefit> housingBenefitList,
			String mappingMode) {
		List<HousingBenefit> resultList = new ArrayList<HousingBenefit>();
		for (HousingBenefit housingBenefit: housingBenefitList) {
			HousingBenefitMapper mapper = mapperMap.get(housingBenefit.getBenefitTypeCode());
			if (mapper != null && mapper.mappingModeList.contains(mappingMode)) {
				resultList.add(housingBenefit);
			}
		}
		return resultList;
	}

	public void mapFields(HousingBenefit housingBenefit, Map<String, String> rawFieldMapForBenefit,
			List<Map<String, String>> rawFieldMapForMemberList) {
		HousingBenefitMapper mapper = mapperMap.get(housingBenefit.getBenefitTypeCode());
		if (mapper != null) {
			mapper.mapFields(housingBenefit, rawFieldMapForBenefit, rawFieldMapForMemberList);
		}
	}
}