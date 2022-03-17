package hk.gov.housingauthority.nhs.common.vo.maintainApplication;

import hk.gov.housingauthority.nhs.framework.core.model.VersionObject;
import hk.gov.housingauthority.nhs.framework.util.DateUtil;

import java.io.Serializable;

public class ApplicationMemberVO extends VersionObject implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long applicationKey;
	private Long sequenceNum;
	private Long applicationApprovalLogId;
	private String idTypeCode;
	private String hkid;
	private String certificateNum;
	private String englishSurname;
	private String englishFirstName;
	private String chineseName;
	private String dateOfBirth;
	private String gender;
	private String relationshipCode;
	private String relationshipDescription;
	private String maritalStatus;
	private Boolean permanentIc;
	private Boolean rightToLand;
	private Boolean pregnant;
	private String ownerCode;
	private Boolean ignoreCategorize;
	
	//For additional information
	private String attributeType;
	private String attributeName;
	private String attributeValue;
	
	private String actionCode;

	public Long getApplicationKey() {
		return applicationKey;
	}
	public void setApplicationKey(Long applicationKey) {
		this.applicationKey = applicationKey;
	}
	public String getApplicationKeyByText() {
		return String.valueOf(applicationKey);
	}
	public void setApplicationKeyByText(String applicationKeyByText) throws NumberFormatException {
		this.applicationKey = Long.parseLong(applicationKeyByText);
	}

	public Long getApplicationApprovalLogId() {
		return applicationApprovalLogId;
	}
	public void setApplicationApprovalLogId(Long applicationApprovalLogId) {
		this.applicationApprovalLogId = applicationApprovalLogId;
	}
	public String getApplicationApprovalLogIdByText() {
		return String.valueOf(applicationApprovalLogId);
	}
	public void setApplicationApprovalLogIdByText(String applicationApprovalLogIdByText) throws NumberFormatException {
		this.applicationApprovalLogId = Long.parseLong(applicationApprovalLogIdByText);
	}
	
	public Long getSequenceNum() {
		return sequenceNum;
	}
	public void setSequenceNum(Long sequenceNum) {
		this.sequenceNum = sequenceNum;
	}
	
	public String getIdTypeCode() {
		return idTypeCode;
	}
	public void setIdTypeCode(String idTypeCode) {
		this.idTypeCode = idTypeCode;
	}
	
	public String getHkid() {
		return hkid;
	}
	public void setHkid(String hkid) {
		this.hkid = hkid;
	}
	
	public String getCertificateNum() {
		return certificateNum;
	}
	public void setCertificateNum(String certificateNum) {
		this.certificateNum = certificateNum;
	}
	
	public String getEnglishSurname() {
		return englishSurname;
	}
	public void setEnglishSurname(String englishSurname) {
		this.englishSurname = englishSurname;
	}
	
	public String getEnglishFirstName() {
		return englishFirstName;
	}
	public void setEnglishFirstName(String englishFirstName) {
		this.englishFirstName = englishFirstName;
	}
	
	public String getChineseName() {
		return chineseName;
	}
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}
	
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getDDMMYYYYDateOfBirth() {
		return DateUtil.convertStrDateFormat(dateOfBirth, DateUtil.DATE_FORMAT_YYYYMMdd, DateUtil.DATE_FORMAT_ddMMyyyy);
	}
	
	public void setYYYYMMDDDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = DateUtil.convertStrDateFormat(dateOfBirth, DateUtil.DATE_FORMAT_ddMMyyyy, DateUtil.DATE_FORMAT_YYYYMMdd);
	}
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getRelationshipCode() {
		return relationshipCode;
	}
	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
	}
	
	public String getRelationshipDescription() {
		return relationshipDescription;
	}
	public void setRelationshipDescription(String relationshipDescription) {
		this.relationshipDescription = relationshipDescription;
	}
	
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	public String getRightToLand() {
		return convertBooleanToString(rightToLand);
	}
	public void setRightToLand(String rightToLand) {
		this.rightToLand = convertStringToBoolean(rightToLand);
	}
	
	public String getPregnant() {
		return convertBooleanToString(pregnant);
	}
	public void setPregnant(String pregnant) {
		this.pregnant = convertStringToBoolean(pregnant);
	}
	
	public String getPermanentIc() {
		return convertBooleanToString(permanentIc);
	}
	public void setPermanentIc(String permanentIc) {
		this.permanentIc = convertStringToBoolean(permanentIc);
	}
	
	public String getOwnerCode() {
		return ownerCode;
	}
	public void setOwnerCode(String ownerCode) {
		this.ownerCode = ownerCode;
	}

	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	
	public String getIgnoreCategorize() {
		return convertBooleanToString(ignoreCategorize);
	}
	public void setIgnoreCategorize(String ignoreCategorize) {
		this.ignoreCategorize = convertStringToBoolean(ignoreCategorize);
	}
	
	public String getAttributeType() {
		return attributeType;
	}
	public void setAttributeType(String attributeType) {
		this.attributeType = attributeType;
	}
	
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	public String getAttributeValue() {
		return attributeValue;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	
	private String convertBooleanToString(Boolean bool) {
		if (bool == null)
			return null;
		return bool?"Y":"N";
	}
	
	private Boolean convertStringToBoolean(String str) {
		if (str == null)
			return null;
		return str.toUpperCase().equals("Y")?Boolean.TRUE:Boolean.FALSE;
	}		
	
    public ApplicationMemberVO clone() throws CloneNotSupportedException {
        return (ApplicationMemberVO) super.clone();    
    }
}
