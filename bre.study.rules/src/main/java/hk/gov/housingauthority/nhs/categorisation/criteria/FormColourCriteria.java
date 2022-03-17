package hk.gov.housingauthority.nhs.categorisation.criteria;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;

/**
 * Categorisation criteria for checking the form color
 * 
 * @author BRE Revamp POC Team
 *
 */
public class FormColourCriteria implements Criteria {
	/**
	 * Accepted form color by this criteria
	 */
	protected String formColour;

	/**
	 * @return the {@link #formColour}
	 */
	public String getFormColour() {
		return formColour;
	}

	/**
	 * @param formColour the {@link #formColour} to set
	 */
	public void setFormColour(String formColour) {
		this.formColour = formColour;
	}

	/**
	 * @return true if if the application’s form colour matches the accepted form
	 *         color
	 */
	public boolean met(MaintainApplicationVO application) {
		return application.getApplicationFormColor().equals(formColour);

	}

}
