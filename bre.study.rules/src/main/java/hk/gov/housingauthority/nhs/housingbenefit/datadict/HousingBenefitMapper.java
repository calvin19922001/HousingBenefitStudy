package hk.gov.housingauthority.nhs.housingbenefit.datadict;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefitField;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefitMember;

public class HousingBenefitMapper {
	protected List<FieldMapper> mapperListForBenefit;
	protected List<FieldMapper> mapperListForMember;

	protected List<String> mappingModeList;

	public List<FieldMapper> getMapperListForBenefit() {
		return mapperListForBenefit;
	}

	public void setMapperListForBenefit(List<FieldMapper> mapperListForBenefit) {
		this.mapperListForBenefit = mapperListForBenefit;
	}

	public List<FieldMapper> getMapperListForMember() {
		return mapperListForMember;
	}

	public void setMapperListForMember(List<FieldMapper> mapperListForMember) {
		this.mapperListForMember = mapperListForMember;
	}

	public List<String> getMappingModeList() {
		return mappingModeList;
	}

	public void setMappingModeList(List<String> mappingModeList) {
		this.mappingModeList = mappingModeList;
	}

	public void mapFields(HousingBenefit housingBenefit, Map<String, String> rawFieldMapForBenefit,
			List<Map<String, String>> rawFieldMapForMemberList) {
		housingBenefit.setFieldMap(new LinkedHashMap<String, HousingBenefitField>());
		for (FieldMapper mapper : mapperListForBenefit) {
			HousingBenefitField field = mapper.map(rawFieldMapForBenefit);
			housingBenefit.getFieldMap().put(mapper.getFieldKey(), field);
		}

		housingBenefit.setMemberList(new ArrayList<HousingBenefitMember>());

		for (Map<String, String> rawFieldMapForMember : rawFieldMapForMemberList) {
			HousingBenefitMember member = new HousingBenefitMember();
			housingBenefit.getMemberList().add(member);

			member.setFieldMap(new LinkedHashMap<String, HousingBenefitField>());

			// Special handling for HKIC and HKBC field - Part 1
			HousingBenefitField hkicField = new HousingBenefitField();
			hkicField.setDisplayName("HKIC No.");
			hkicField.setValue(StringUtils.EMPTY);
			hkicField.setForDisplay(true);
			member.getFieldMap().put("hkic", hkicField);

			HousingBenefitField hkbcField = new HousingBenefitField();
			hkbcField.setDisplayName("BC No.");
			hkbcField.setValue(StringUtils.EMPTY);
			hkbcField.setForDisplay(true);
			member.getFieldMap().put("hkbc", hkbcField);

			// Handling for remaining fields
			for (FieldMapper mapper : mapperListForMember) {
				HousingBenefitField field = mapper.map(rawFieldMapForMember);
				member.getFieldMap().put(mapper.getFieldKey(), field);
			}

			// Special handling for HKIC and HKBC field - Part 2
			if (member.getFieldMap().get("idTypeCode").getValue().equals("IC")) {
				hkicField.setValue(member.getFieldMap().get("idNumber").getValue());
			} else if (member.getFieldMap().get("idTypeCode").getValue().equals("BC")) {
				hkbcField.setValue(member.getFieldMap().get("idNumber").getValue());
			}
		}
	}
}