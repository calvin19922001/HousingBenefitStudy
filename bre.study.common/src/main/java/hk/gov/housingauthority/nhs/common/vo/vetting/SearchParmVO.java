package hk.gov.housingauthority.nhs.common.vo.vetting;

import java.io.Serializable;

public class SearchParmVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String global_text_appId;
	private String p_text_typeID;
	private String p_text_numID;

	
	public String getGlobal_text_appId() {
		return global_text_appId;
	}

	public void setGlobal_text_appId(String global_text_appId) {
		this.global_text_appId = global_text_appId;
	}

	public String getP_text_typeID() {
		return p_text_typeID;
	}
	
	public void setP_text_typeID(String p_text_typeID) {
		this.p_text_typeID = p_text_typeID;
	}
	
	public String getP_text_numID() {
		return p_text_numID;
	}
	
	public void setP_text_numID(String p_text_numID) {
		this.p_text_numID = p_text_numID;
	}
}
