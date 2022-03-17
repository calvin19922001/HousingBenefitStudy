package hk.gov.housingauthority.nhs.rules.vo.housingbenefit;

import java.util.Map;

public class HousingBenefitMember {
	protected Map<String, HousingBenefitField> fieldMap;

	public Map<String, HousingBenefitField> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, HousingBenefitField> fieldMap) {
		this.fieldMap = fieldMap;
	}

}
