package hk.gov.housingauthority.nhs.rules.vo.housingbenefit;

public class HousingBenefitField {
	protected String displayName;
	protected String value;
	protected boolean forDisplay;
	protected boolean forDiscrepancyCheck;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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

}
