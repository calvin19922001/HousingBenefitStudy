package hk.gov.housingauthority.nhs.rules.vo.housingbenefit;

import java.util.List;
import java.util.Map;

public class HousingBenefit {
	protected String duplicatedReportMasterKey;
	protected String reportMasterReference;
	protected String masterListId;
	protected String benefitTypeCode;
	protected String benefitReferenceNumber;
	protected String benefitMemberIdTypeCode;
	protected String benefitMemberIdNumber;
	protected Map<String, HousingBenefitField> fieldMap;
	protected List<HousingBenefitMember> memberList;

	public String getDuplicatedReportMasterKey() {
		return duplicatedReportMasterKey;
	}

	public void setDuplicatedReportMasterKey(String duplicatedReportMasterKey) {
		this.duplicatedReportMasterKey = duplicatedReportMasterKey;
	}

	public String getReportMasterReference() {
		return reportMasterReference;
	}

	public void setReportMasterReference(String reportMasterReference) {
		this.reportMasterReference = reportMasterReference;
	}

	public String getMasterListId() {
		return masterListId;
	}

	public void setMasterListId(String masterListId) {
		this.masterListId = masterListId;
	}

	public String getBenefitTypeCode() {
		return benefitTypeCode;
	}

	public void setBenefitTypeCode(String benefitTypeCode) {
		this.benefitTypeCode = benefitTypeCode;
	}

	public String getBenefitReferenceNumber() {
		return benefitReferenceNumber;
	}

	public void setBenefitReferenceNumber(String benefitReferenceNumber) {
		this.benefitReferenceNumber = benefitReferenceNumber;
	}

	public String getBenefitMemberIdTypeCode() {
		return benefitMemberIdTypeCode;
	}

	public void setBenefitMemberIdTypeCode(String benefitMemberIdTypeCode) {
		this.benefitMemberIdTypeCode = benefitMemberIdTypeCode;
	}

	public String getBenefitMemberIdNumber() {
		return benefitMemberIdNumber;
	}

	public void setBenefitMemberIdNumber(String benefitMemberIdNumber) {
		this.benefitMemberIdNumber = benefitMemberIdNumber;
	}

	public Map<String, HousingBenefitField> getFieldMap() {
		return fieldMap;
	}

	public void setFieldMap(Map<String, HousingBenefitField> fieldMap) {
		this.fieldMap = fieldMap;
	}

	public List<HousingBenefitMember> getMemberList() {
		return memberList;
	}

	public void setMemberList(List<HousingBenefitMember> memberList) {
		this.memberList = memberList;
	}
}
