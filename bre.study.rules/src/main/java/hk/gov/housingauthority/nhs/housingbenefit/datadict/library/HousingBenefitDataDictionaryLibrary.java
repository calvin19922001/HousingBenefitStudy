package hk.gov.housingauthority.nhs.housingbenefit.datadict.library;

import java.util.Map;

import hk.gov.housingauthority.nhs.housingbenefit.datadict.HousingBenefitDataDictionary;

public class HousingBenefitDataDictionaryLibrary {

	protected Map<String, HousingBenefitDataDictionary> dictionaryByVersionMap;

	public Map<String, HousingBenefitDataDictionary> getDictionaryByVersionMap() {
		return dictionaryByVersionMap;
	}

	public void setDictionaryByVersionMap(Map<String, HousingBenefitDataDictionary> dictionaryByVersionMap) {
		this.dictionaryByVersionMap = dictionaryByVersionMap;
	}

	public HousingBenefitDataDictionary getDictionaryByDictionaryVersion(String dictionaryVersion) {
		return dictionaryByVersionMap.get(dictionaryVersion);
	}
}
