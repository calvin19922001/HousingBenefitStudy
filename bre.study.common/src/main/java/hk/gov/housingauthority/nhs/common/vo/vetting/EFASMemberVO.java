package hk.gov.housingauthority.nhs.common.vo.vetting;

import java.io.Serializable;

public class EFASMemberVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String efm_text_IDtype;
	private String efm_text_IDnum;
	
	public String getEfm_text_IDtype() {
		return efm_text_IDtype;
	}
	
	public void setEfm_text_IDtype(String efm_text_IDtype) {
		this.efm_text_IDtype = efm_text_IDtype;
	}
	
	public String getEfm_text_IDnum() {
		return efm_text_IDnum;
	}
	
	public void setEfm_text_IDnum(String efm_text_IDnum) {
		this.efm_text_IDnum = efm_text_IDnum;
	}
}
