package hk.gov.housingauthority.nhs.common.vo.vetting;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EFASReportsVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private SearchParmVO searchParm;
	private Date ef_date_commencement;
	private Date ef_date_expired;
	private String ef_text_codeAddress;
	private EFASMemberVO[] EFASMember;
	private List<EFASMemberVO> EFASMemberList;
	
	
	public SearchParmVO getSearchParm() {
		return searchParm;
	}
	
	public void setSearchParm(SearchParmVO searchParm) {
		this.searchParm = searchParm;
	}
	
	public Date getEf_date_commencement() {
		return ef_date_commencement;
	}
	
	public void setEf_date_commencement(Date ef_date_commencement) {
		this.ef_date_commencement = ef_date_commencement;
	}
	
	public Date getEf_date_expired() {
		return ef_date_expired;
	}
	
	public void setEf_date_expired(Date ef_date_expired) {
		this.ef_date_expired = ef_date_expired;
	}
	
	public String getEf_text_codeAddress() {
		return ef_text_codeAddress;
	}
	
	public void setEf_text_codeAddress(String ef_text_codeAddress) {
		this.ef_text_codeAddress = ef_text_codeAddress;
	}

	public EFASMemberVO[] getEFASMember() {
		return EFASMember;
	}
	
	public void setEFASMember(EFASMemberVO[] member) {
		EFASMember = member;
	}

	public List<EFASMemberVO> getEFASMemberList() {
		return EFASMemberList;
	}

	public void setEFASMemberList(List<EFASMemberVO> memberList) {
		EFASMemberList = memberList;
	}
	
	

}
