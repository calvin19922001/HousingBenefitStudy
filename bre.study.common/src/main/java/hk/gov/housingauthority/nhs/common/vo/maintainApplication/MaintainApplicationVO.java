package hk.gov.housingauthority.nhs.common.vo.maintainApplication;

import hk.gov.housingauthority.nhs.common.vo.assignApplication.PriorityInfo;
import hk.gov.housingauthority.nhs.common.vo.vetting.EFASReportsVO;
import hk.gov.housingauthority.nhs.framework.core.model.VersionObject;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MaintainApplicationVO extends VersionObject implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	private String phaseCode;
	private Long applicationKey;
	private Long originalApplicationKey;
	private String applicationNum;
	private Date applicationDate;
	private Long applicationApprovalLogId;
	private List<PriorityInfo> categoryPriority;
	private List<PriorityInfo> categoryPriorityHist;
	private String searchApplicantHkic;
	private String searchApplicantCertificateNum;
	private String searchMemberRelationshipCode;
	private List<ApplicationMemberVO> applicationMemberList;
	private String applicationStatus;
	private String applicationApprovalStatus;
	private String applicationFormColor;
	private String homePhoneNum;
	private String mobilePhoneNum;
	private String officePhoneNum;

	private String correspondenceAddress1;
	private String correspondenceAddress2;
	private String correspondenceAddress3;
	private String correspondenceAddress4;
	private String correspondenceAddress5;

	private String residentialAddress1;
	private String residentialAddress2;
	private String residentialAddress3;
	private String residentialAddress4;
	private String residentialAddress5;
	
	private String applicationRemark;
	private BigDecimal familyIncome;
	private BigDecimal familyAsset;
	private Boolean joinElderlyMemberScheme;
	private Boolean nuclearWithGrandParentAndChild;
	private Boolean ownerOfTPS;
	private String estateType;
	private String estateCode;
	private String blockCode;
	private String flatCode;
	private String suffixCode;
	private String applicantCompositionCode;
	private String chequeOrderNum;
	private String chequeOrderNum2;
	private String chequeOrderNum3;
	private String bankCode;
	private String bankCode2;
	private String bankCode3;
	private String branchCode;
	private String branchCode2;
	private String branchCode3;
	private Boolean joinEFAS;
	private Boolean joinCRP;
	private Boolean joinFEP;
	private Boolean amendInProgress;
	private String preferLanguage;
	private Boolean domesticPropertyOwnership;
	private String numOfGFC;
	private Boolean topPriority;
	private Boolean sraIndicator;
	private String rentLevelCode;
	private Boolean letterOfAss;
	private Boolean splitting;
	private Boolean splittingMultiRentalUnits;
	private Boolean remarkIndicator;
	private Boolean propertyOwnershipUseByHO;
	private String actionCode;
	private String vettingStatus;
	private Date signedDate;
	private String signedDateText;
	private String housingType;
	private String livingDistrict;
	private BigDecimal incomeRangeFrom;
	private BigDecimal incomeRangeTo;
	private BigDecimal assetRangeFrom;
	private BigDecimal assetRangeTo;
	private String approvedUser;
	private Date approvedDate;
	private String actionUserId;
	private Boolean verifiedEFAS;
	private String	assignedHOuserId;
	private String	assignedAHMuserId;
	private Boolean ihIndicator;
	private Boolean aplyCopyGshInd;
	
	private String batchId;
	private String barcode;
	private String esubmissionReference;
	private String fiLinkKey;
	private String esubmissionPaymentMethod;
	private Date esubmissionDate;
	private String emailAddress;
	private Date firstAcknowledgementDate;
	//HOS 2019 HOS-03: change to identify for both e-Submission record and physical form
	private String easMultipleSchemeIndicator;
	private String directImportFromEsubmission;
	
	private String referenceApplicationKey;
	
	private List<EFASReportsVO> efasReports;
	
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public String getApprovedUser() {
		return approvedUser;
	}
	public void setApprovedUser(String approvedUser) {
		this.approvedUser = approvedUser;
	}
	public Date getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}
	private String approvalRemark;	
	
	public String getVettingStatus() {
		return vettingStatus;
	}
	public void setVettingStatus(String vettingStatus) {
		this.vettingStatus = vettingStatus;
	}
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
	
	public Long getOriginalApplicationKey() {
		return originalApplicationKey;
	}
	public void setOriginalApplicationKey(Long originalApplicationKey) {
		this.originalApplicationKey = originalApplicationKey;
	}
	public String getOriginalApplicationKeyByText() {
		return String.valueOf(originalApplicationKey);
	}
	public void setOriginalApplicationKeyByText(String originalApplicationKeyByText) throws NumberFormatException {
		this.originalApplicationKey = Long.parseLong(originalApplicationKeyByText);
	}
	
	public String getApplicationNum() {
		return applicationNum;
	}
	public void setApplicationNum(String applicationNum) {
		this.applicationNum = applicationNum;
	}
	
	public Date getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
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
	
	public String getSearchApplicantHkic() {
		return searchApplicantHkic;
	}
	
	public List<PriorityInfo> getCategoryPriority() {
		return categoryPriority;
	}
	public void setCategoryPriority(List<PriorityInfo> categoryPriority) {
		this.categoryPriority = categoryPriority;
	}
	
	public List<PriorityInfo> getCategoryPriorityHist() {
		return categoryPriorityHist;
	}
	public void setCategoryPriorityHist(List<PriorityInfo> categoryPriorityHist) {
		this.categoryPriorityHist = categoryPriorityHist;
	}
	
	public void setSearchApplicantHkic(String searchApplicantHkic) {
		this.searchApplicantHkic = searchApplicantHkic;
	}
	public String getSearchApplicantCertificateNum() {
		return searchApplicantCertificateNum;
	}
	public void setSearchApplicantCertificateNum(
			String searchApplicantCertificateNum) {
		this.searchApplicantCertificateNum = searchApplicantCertificateNum;
	}
	
	public String getSearchMemberRelationshipCode() {
		return searchMemberRelationshipCode;
	}
	public void setSearchMemberRelationshipCode(String searchMemberRelationshipCode) {
		this.searchMemberRelationshipCode = searchMemberRelationshipCode;
	}
	
	public List<ApplicationMemberVO> getApplicationMemberList() {
		return applicationMemberList;
	}
	public void setApplicationMemberList(
			List<ApplicationMemberVO> applicationMemberList) {
		this.applicationMemberList = applicationMemberList;
	}
	
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	public String getApplicationApprovalStatus() {
		return applicationApprovalStatus;
	}
	public void setApplicationApprovalStatus(String applicationApprovalStatus) {
		this.applicationApprovalStatus = applicationApprovalStatus;
	}
	
	public String getApplicationFormColor() {
		return applicationFormColor;
	}
	public void setApplicationFormColor(String applicationFormColor) {
		this.applicationFormColor = applicationFormColor;
	}
	
	public String getHomePhoneNum() {
		return homePhoneNum;
	}
	public void setHomePhoneNum(String homePhoneNum) {
		this.homePhoneNum = homePhoneNum;
	}
	
	public String getMobilePhoneNum() {
		return mobilePhoneNum;
	}
	public void setMobilePhoneNum(String mobilePhoneNum) {
		this.mobilePhoneNum = mobilePhoneNum;
	}
	
	public String getOfficePhoneNum() {
		return officePhoneNum;
	}
	public void setOfficePhoneNum(String officePhoneNum) {
		this.officePhoneNum = officePhoneNum;
	}
	
	public String getEnCorrespondenceAddress1() {
		return correspondenceAddress1;
	}
	public void setEnCorrespondenceAddress1(String correspondenceAddress1) {
		this.correspondenceAddress1 = correspondenceAddress1;
	}
	
	public String getEnCorrespondenceAddress2() {
		return correspondenceAddress2;
	}
	public void setEnCorrespondenceAddress2(String correspondenceAddress2) {
		this.correspondenceAddress2 = correspondenceAddress2;
	}
	
	public String getEnCorrespondenceAddress3() {
		return correspondenceAddress3;
	}
	public void setEnCorrespondenceAddress3(String correspondenceAddress3) {
		this.correspondenceAddress3 = correspondenceAddress3;
	}
	
	public String getEnCorrespondenceAddress4() {
		return correspondenceAddress4;
	}
	public void setEnCorrespondenceAddress4(String correspondenceAddress4) {
		this.correspondenceAddress4 = correspondenceAddress4;
	}
	
	public String getEnCorrespondenceAddress5() {
		return correspondenceAddress5;
	}
	public void setEnCorrespondenceAddress5(String correspondenceAddress5) {
		this.correspondenceAddress5 = correspondenceAddress5;
	}
	
	public String getChCorrespondenceAddress1() {
		return correspondenceAddress1;
	}
	public void setChCorrespondenceAddress1(String correspondenceAddress1) {
		this.correspondenceAddress1 = correspondenceAddress1;
	}
	
	public String getChCorrespondenceAddress2() {
		return correspondenceAddress2;
	}
	public void setChCorrespondenceAddress2(String correspondenceAddress2) {
		this.correspondenceAddress2 = correspondenceAddress2;
	}
	
	public String getChCorrespondenceAddress3() {
		return correspondenceAddress3;
	}
	public void setChCorrespondenceAddress3(String correspondenceAddress3) {
		this.correspondenceAddress3 = correspondenceAddress3;
	}
	
	public String getChCorrespondenceAddress4() {
		return correspondenceAddress4;
	}
	public void setChCorrespondenceAddress4(String correspondenceAddress4) {
		this.correspondenceAddress4 = correspondenceAddress4;
	}
	
	public String getChCorrespondenceAddress5() {
		return correspondenceAddress5;
	}
	public void setChCorrespondenceAddress5(String correspondenceAddress5) {
		this.correspondenceAddress5 = correspondenceAddress5;
	}
	
	public String getEnResidentialAddress1() {
		return residentialAddress1;
	}
	public void setEnResidentialAddress1(String residentialAddress1) {
		this.residentialAddress1 = residentialAddress1;
	}
	
	public String getEnResidentialAddress2() {
		return residentialAddress2;
	}
	public void setEnResidentialAddress2(String residentialAddress2) {
		this.residentialAddress2 = residentialAddress2;
	}
	
	public String getEnResidentialAddress3() {
		return residentialAddress3;
	}
	public void setEnResidentialAddress3(String residentialAddress3) {
		this.residentialAddress3 = residentialAddress3;
	}
	
	public String getEnResidentialAddress4() {
		return residentialAddress4;
	}
	public void setEnResidentialAddress4(String residentialAddress4) {
		this.residentialAddress4 = residentialAddress4;
	}
	
	public String getEnResidentialAddress5() {
		return residentialAddress5;
	}
	public void setEnResidentialAddress5(String residentialAddress5) {
		this.residentialAddress5 = residentialAddress5;
	}
	
	public String getChResidentialAddress1() {
		return residentialAddress1;
	}
	public void setChResidentialAddress1(String residentialAddress1) {
		this.residentialAddress1 = residentialAddress1;
	}
	
	public String getChResidentialAddress2() {
		return residentialAddress2;
	}
	public void setChResidentialAddress2(String residentialAddress2) {
		this.residentialAddress2 = residentialAddress2;
	}
	
	public String getChResidentialAddress3() {
		return residentialAddress3;
	}
	public void setChResidentialAddress3(String residentialAddress3) {
		this.residentialAddress3 = residentialAddress3;
	}
	
	public String getChResidentialAddress4() {
		return residentialAddress4;
	}
	public void setChResidentialAddress4(String residentialAddress4) {
		this.residentialAddress4 = residentialAddress4;
	}
	
	public String getChResidentialAddress5() {
		return residentialAddress5;
	}
	public void setChResidentialAddress5(String residentialAddress5) {
		this.residentialAddress5 = residentialAddress5;
	}
	
	public String getApplicationRemark() {
		return applicationRemark;
	}
	public void setApplicationRemark(String applicationRemark) {
		this.applicationRemark = applicationRemark;
	}
	
	public BigDecimal getFamilyIncome() {
		return familyIncome;
	}
	public void setFamilyIncome(BigDecimal familyIncome) {
		this.familyIncome = familyIncome;
	}
	
	public BigDecimal getFamilyAsset() {
		return familyAsset;
	}
	public void setFamilyAsset(BigDecimal familyAsset) {
		this.familyAsset = familyAsset;
	}
	
	public String getJoinElderlyMemberScheme() {
		return convertBooleanToString(joinElderlyMemberScheme);
	}
	public void setJoinElderlyMemberScheme(String joinElderlyMemberScheme) {
		this.joinElderlyMemberScheme = convertStringToBoolean(joinElderlyMemberScheme);
	}
	
	public String getNuclearWithGrandParentAndChild() {
		return convertBooleanToString(nuclearWithGrandParentAndChild);
	}
	public void setNuclearWithGrandParentAndChild(
			String nuclearWithGrandParentAndChild) {
		this.nuclearWithGrandParentAndChild = convertStringToBoolean(nuclearWithGrandParentAndChild);
	}
	
	public String getOwnerOfTPS() {
		return convertBooleanToString(ownerOfTPS);
	}
	public void setOwnerOfTPS(String ownerOfTPS) {
		this.ownerOfTPS = convertStringToBoolean(ownerOfTPS);
	}
	
	public String getEstateType() {
		return estateType;
	}
	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}
	
	public String getEstateCode() {
		return estateCode;
	}
	public void setEstateCode(String estateCode) {
		this.estateCode = estateCode;
	}
	
	public String getBlockCode() {
		return blockCode;
	}
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
	
	public String getFlatCode() {
		return flatCode;
	}
	public void setFlatCode(String flatCode) {
		this.flatCode = flatCode;
	}
	
	public String getSuffixCode() {
		return suffixCode;
	}
	public void setSuffixCode(String suffixCode) {
		this.suffixCode = suffixCode;
	}
	
	public String getApplicantCompositionCode() {
		return applicantCompositionCode;
	}
	public void setApplicantCompositionCode(String applicantCompositionCode) {
		this.applicantCompositionCode = applicantCompositionCode;
	}
	
	public String getChequeOrderNum() {
		return chequeOrderNum;
	}
	public void setChequeOrderNum(String chequeOrderNum) {
		this.chequeOrderNum = chequeOrderNum;
	}
	
	public String getChequeOrderNum2() {
		return chequeOrderNum2;
	}
	public void setChequeOrderNum2(String chequeOrderNum2) {
		this.chequeOrderNum2 = chequeOrderNum2;
	}
	
	public String getChequeOrderNum3() {
		return chequeOrderNum3;
	}
	public void setChequeOrderNum3(String chequeOrderNum3) {
		this.chequeOrderNum3 = chequeOrderNum3;
	}
	
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	
	public String getBranchCode2() {
		return branchCode2;
	}

	public void setBranchCode2(String branchCode2) {
		this.branchCode2 = branchCode2;
	}
	
	public String getBranchCode3() {
		return branchCode3;
	}

	public void setBranchCode3(String branchCode3) {
		this.branchCode3 = branchCode3;
	}
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	public String getBankCode2() {
		return bankCode2;
	}

	public void setBankCode2(String bankCode2) {
		this.bankCode2 = bankCode2;
	}
	
	public String getBankCode3() {
		return bankCode3;
	}

	public void setBankCode3(String bankCode3) {
		this.bankCode3 = bankCode3;
	}
	
	public String getJoinEFAS() {
		return convertBooleanToString(joinEFAS);
	}
	public void setJoinEFAS(String joinEFAS) {
		this.joinEFAS = convertStringToBoolean(joinEFAS);
	}
	
	public String getJoinCRP() {
		return convertBooleanToString(joinCRP);
	}
	public void setJoinCRP(String joinCRP) {
		this.joinCRP = convertStringToBoolean(joinCRP);
	}
	
	public String getJoinFEP() {
		return convertBooleanToString(joinFEP);
	}
	public void setJoinFEP(String joinFEP) {
		this.joinFEP = convertStringToBoolean(joinFEP);
	}
	
	public String getAmendInProgress() {
		return convertBooleanToString(amendInProgress);
	}
	public void setAmendInProgress(String amendInProgress) {
		this.amendInProgress = convertStringToBoolean(amendInProgress);
	}
	
	public String getPreferLanguage() {
		return preferLanguage;
	}
	public void setPreferLanguage(String preferLanguage) {
		this.preferLanguage = preferLanguage;
	}
	
	public String getDomesticPropertyOwnership() {
		return convertBooleanToString(domesticPropertyOwnership);
	}
	public void setDomesticPropertyOwnership(String domesticPropertyOwnership) {
		this.domesticPropertyOwnership = convertStringToBoolean(domesticPropertyOwnership);
	}
	
	public String getNumOfGFC() {
		return numOfGFC;
	}
	public void setNumOfGFC(String numOfGFC) {
		this.numOfGFC = numOfGFC;
	}
	
	public String getTopPriority() {
		return convertBooleanToString(topPriority);
	}
	public void setTopPriority(String topPriority) {
		this.topPriority = convertStringToBoolean(topPriority);
	}
	
	public String getSraIndicator() {
		return convertBooleanToString(sraIndicator);
	}
	public void setSraIndicator(String sraIndicator) {
		this.sraIndicator = convertStringToBoolean(sraIndicator);
	}
	
	public String getRentLevelCode() {
		return rentLevelCode;
	}
	public void setRentLevelCode(String rentLevelCode) {
		this.rentLevelCode = rentLevelCode;
	}
	
	public String getLetterOfAss() {
		return convertBooleanToString(letterOfAss);
	}
	public void setLetterOfAss(String letterOfAss) {
		this.letterOfAss = convertStringToBoolean(letterOfAss);
	}
	
	public String getSplitting() {
		return convertBooleanToString(splitting);
	}
	public void setSplitting(String splitting) {
		this.splitting = convertStringToBoolean(splitting);
	}
	
	public String getSplittingMultiRentalUnits() {
		return convertBooleanToString(splittingMultiRentalUnits);
	}
	public void setSplittingMultiRentalUnits(String splittingMultiRentalUnits) {
		this.splittingMultiRentalUnits = convertStringToBoolean(splittingMultiRentalUnits);
	}
	
	public String getRemarkIndicator() {
		return convertBooleanToString(remarkIndicator);
	}
	public void setRemarkIndicator(String remarkIndicator) {
		this.remarkIndicator = convertStringToBoolean(remarkIndicator);
	}
	
	public String getPropertyOwnershipUseByHO() {
		return convertBooleanToString(propertyOwnershipUseByHO);
	}
	public void setPropertyOwnershipUseByHO(String propertyOwnershipUseByHO) {
		this.propertyOwnershipUseByHO = convertStringToBoolean(propertyOwnershipUseByHO);
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
	
	public String getActionCode() {
		return actionCode;
	}
	
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	
	public String getApprovalRemark() {
		return approvalRemark;
	}
	
	public void setApprovalRemark(String approvalRemark) {
		this.approvalRemark = approvalRemark;
	}
	
	public String getActionUserId() {
		return actionUserId;
	}
	
	public void setActionUserId(String actionUserId) {
		this.actionUserId = actionUserId;
	}
	
	public String getVerifiedEFAS() {
		return convertBooleanToString(verifiedEFAS);
	}
	
	public void setVerifiedEFAS(String verifiedEFAS) {
		this.verifiedEFAS = convertStringToBoolean(verifiedEFAS);
	}
	
	public String getAssignedHOuserId() {
		return assignedHOuserId;
	}
	
	public void setAssignedHOuserId(String assignedHOuserId) {
		this.assignedHOuserId = assignedHOuserId;
	}
	
	public String getAssignedAHMuserId() {
		return assignedAHMuserId;
	}
	
	public void setAssignedAHMuserId(String assignedAHMuserId) {
		this.assignedAHMuserId = assignedAHMuserId;
	}
	
	public Date getSignedDate() {
		return signedDate;
	}
	
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}
	
	public String getSignedDateText() {
		return signedDateText;
	}
	
	public void setSignedDateText(String signedDateText) {
		this.signedDateText = signedDateText;
	}
	
	public void setHousingType(String housingType) {
		this.housingType = housingType;
	}
	
	public String getHousingType() {
		return housingType;
	}
	
	public void setLivingDistrict(String livingDistrict) {
		this.livingDistrict = livingDistrict;
	}
	
	public String getLivingDistrict() {
		return livingDistrict;
	}
	
	public void setIncomeRangeFrom(BigDecimal incomeRangeFrom) {
		this.incomeRangeFrom = incomeRangeFrom;
	}
	
	public BigDecimal getIncomeRangeFrom() {
		return incomeRangeFrom;
	}
	
	public void setIncomeRangeTo(BigDecimal incomeRangeTo) {
		this.incomeRangeTo = incomeRangeTo;
	}
	
	public BigDecimal getIncomeRangeTo() {
		return incomeRangeTo;
	}
	
	public void setAssetRangeFrom(BigDecimal assetRangeFrom) {
		this.assetRangeFrom = assetRangeFrom;
	}
	
	public BigDecimal getAssetRangeFrom() {
		return assetRangeFrom;
	}
	
	public void setAssetRangeTo(BigDecimal assetRangeTo) {
		this.assetRangeTo = assetRangeTo;
	}
	
	public BigDecimal getAssetRangeTo() {
		return assetRangeTo;
	}
	
	public String getIhIndicator() {
		return convertBooleanToString(ihIndicator);
	}
	
	public void setIhIndicator(String inIndicator) {
		this.ihIndicator = convertStringToBoolean(inIndicator);
	}
	
	public String getAplyCopyGshInd() {
		return convertBooleanToString(aplyCopyGshInd);
	}
	
	public void setAplyCopyGshInd(String aplyCopyGshInd) {
		this.aplyCopyGshInd = convertStringToBoolean(aplyCopyGshInd);
	}
	
	public String getBatchId() {
		return batchId;
	}
	
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	public void setEsubmissionReference(String esubmissionReference) {
		this.esubmissionReference = esubmissionReference;
	}
	
	public String getEsubmissionReference() {
		return esubmissionReference;
	}
	
	public void setFiLinkKey(String fiLinkKey) {
		this.fiLinkKey = fiLinkKey;
	}
	public String getFiLinkKey() {
		return fiLinkKey;
	}
	public void setEsubmissionPaymentMethod(String esubmissionPaymentMethod) {
		this.esubmissionPaymentMethod = esubmissionPaymentMethod;
	}
	public String getEsubmissionPaymentMethod() {
		return esubmissionPaymentMethod;
	}
	public void setEsubmissionDate(Date esubmissionDate) {
		this.esubmissionDate = esubmissionDate;
	}
	public Date getEsubmissionDate() {
		return esubmissionDate;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setFirstAcknowledgementDate(Date firstAcknowledgementDate) {
		this.firstAcknowledgementDate = firstAcknowledgementDate;
	}
	public Date getFirstAcknowledgementDate() {
		return firstAcknowledgementDate;
	}
	public void setEasMultipleSchemeIndicator(String easMultipleSchemeIndicator) {
		this.easMultipleSchemeIndicator = easMultipleSchemeIndicator;
	}
	public String getEasMultipleSchemeIndicator() {
		return easMultipleSchemeIndicator;
	}
	public String getDirectImportFromEsubmission() {
		return directImportFromEsubmission;
	}
	public void setDirectImportFromEsubmission(String directImportFromEsubmission) {
		this.directImportFromEsubmission = directImportFromEsubmission;
	}
	public void setReferenceApplicationKey(String referenceApplicationKey) {
		this.referenceApplicationKey = referenceApplicationKey;
	}
	public String getReferenceApplicationKey() {
		return referenceApplicationKey;
	}
	public List<EFASReportsVO> getEfasReports() {
		return efasReports;
	}
	
	public void setEfasReports(List<EFASReportsVO> efasReports) {
		this.efasReports = efasReports;
	}
	
	public MaintainApplicationVO clone() throws CloneNotSupportedException {
        return (MaintainApplicationVO) super.clone();    
    }
}
