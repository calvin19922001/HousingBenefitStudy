package hk.gov.housingauthority.nhs.housingbenefit.datadict;

import java.util.Map;

import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefitField;

public class FieldMapper {
	protected String fieldKey;
	protected String displayName;
	protected String dbFieldName;
	protected boolean forDisplay;
	protected boolean forDiscrepancyCheck;

	public String getFieldKey() {
		return fieldKey;
	}

	public void setFieldKey(String fieldKey) {
		this.fieldKey = fieldKey;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDbFieldName() {
		return dbFieldName;
	}

	public void setDbFieldName(String dbFieldName) {
		this.dbFieldName = dbFieldName;
	}

	public boolean isForDisplay() {
		return forDisplay;
	}

	public void setForDisplay(boolean forDisplay) {
		this.forDisplay = forDisplay;
	}

	public boolean isForDiscrepancyCheck() {
		return forDiscrepancyCheck;
	}

	public void setForDiscrepancyCheck(boolean forDiscrepancyCheck) {
		this.forDiscrepancyCheck = forDiscrepancyCheck;
	}

	public HousingBenefitField map(Map<String, String> rawFieldMap) {
		HousingBenefitField field = new HousingBenefitField();
		field.setDisplayName(displayName);
		field.setForDisplay(forDisplay);
		field.setForDiscrepancyCheck(forDiscrepancyCheck);
		field.setValue(rawFieldMap.get(dbFieldName));
		return field;
	}
}
