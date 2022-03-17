package hk.gov.housingauthority.nhs.recommendation;

import java.util.List;

import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;

/**
 * Represent the recommendation generated by system
 * 
 * The recommendation is in tree structure, where the parent recommendation
 * result is derived from the child recommendations
 * 
 * @author BRE Revamp POC Team
 *
 */
public class Recommendation {
	/**
	 * The result status for "accept"
	 */
	public static final String RESULT__ACCEPTED = "accept";

	/**
	 * The result status for "reject"
	 */
	public static final String RESULT__REJECTED = "reject";
	public static final String RESULT__FOLLOW_UP = "follow up";

	/**
	 * The result status for "error"
	 * 
	 * This result should be for Debug Use, and should not occur in normal case
	 */
	public static final String RESULT__ERROR = "error";

	/**
	 * Parent recommendation
	 */
	protected Recommendation parent;

	/**
	 * childRecommendationList determines list of child recommendations of current
	 */
	protected List<Recommendation> childRecommendationList;

	/**
	 * Related Housing Benefit of the recommendation
	 */
	protected HousingBenefit housingBenefit;

	/**
	 * Unique key for the recommendation (for database persistance)
	 */
	protected Long recommendationKey;

	/**
	 * Application key and the related application
	 */
	protected Long applicationKey;

	/**
	 * ID type code of the related application member
	 */
	protected String memberIdTypeCode;

	/**
	 * HKID of the related application member
	 */
	protected String memberHkid;

	/**
	 * Certificate Number HKID of the related application member
	 */
	protected String memberCertificateNum;

	/**
	 * Duplicated Report Master Key of the related housing benefit
	 */
	protected String duplicatedReportMasterKey;

	/**
	 * Report Master Reference of the related housing benefit
	 */
	protected String reportMasterReference;

	/**
	 * Master List ID of the related housing benefit
	 */
	protected String masterListId;

	/**
	 * Rule ID for determining this recommendation
	 */
	protected String ruleId;

	/**
	 * Message
	 */
	protected String message;

	/**
	 * Result of the recommendation
	 */
	protected String result;

	/**
	 * @return the {@link #parent}
	 */
	public Recommendation getParent() {
		return parent;
	}

	/**
	 * @param parent the {@link #parent} to set
	 */
	public void setParent(Recommendation parent) {
		this.parent = parent;
	}

	/**
	 * @return the {@link #childRecommendationList}
	 */
	public List<Recommendation> getChildRecommendationList() {
		return childRecommendationList;
	}

	/**
	 * @param childRecommendationList the {@link #childRecommendationList} to set
	 */
	public void setChildRecommendationList(List<Recommendation> childRecommendationList) {
		this.childRecommendationList = childRecommendationList;
	}

	/**
	 * @return the {@link #housingBenefit}
	 */
	public HousingBenefit getHousingBenefit() {
		return housingBenefit;
	}

	/**
	 * @param housingBenefit the {@link #housingBenefit} to set
	 */
	public void setHousingBenefit(HousingBenefit housingBenefit) {
		this.housingBenefit = housingBenefit;
	}

	/**
	 * @return the {@link #recommendationKey}
	 */
	public Long getRecommendationKey() {
		return recommendationKey;
	}

	/**
	 * @param recommendationKey the {@link #recommendationKey} to set
	 */
	public void setRecommendationKey(Long recommendationKey) {
		this.recommendationKey = recommendationKey;
	}

	/**
	 * @return the {@link #applicationKey}
	 */
	public Long getApplicationKey() {
		return applicationKey;
	}

	/**
	 * @param applicationKey the {@link #applicationKey} to set
	 */
	public void setApplicationKey(Long applicationKey) {
		this.applicationKey = applicationKey;
	}

	/**
	 * @return the {@link #memberIdTypeCode}
	 */
	public String getMemberIdTypeCode() {
		return memberIdTypeCode;
	}

	/**
	 * @param memberIdTypeCode the {@link #memberIdTypeCode} to set
	 */
	public void setMemberIdTypeCode(String memberIdTypeCode) {
		this.memberIdTypeCode = memberIdTypeCode;
	}

	/**
	 * @return the {@link #memberHkid}
	 */
	public String getMemberHkid() {
		return memberHkid;
	}

	/**
	 * @param memberHkid the {@link #memberHkid} to set
	 */
	public void setMemberHkid(String memberHkid) {
		this.memberHkid = memberHkid;
	}

	/**
	 * @return the {@link #memberCertificateNum}
	 */
	public String getMemberCertificateNum() {
		return memberCertificateNum;
	}

	/**
	 * @param memberCertificateNum the {@link #memberCertificateNum} to set
	 */
	public void setMemberCertificateNum(String memberCertificateNum) {
		this.memberCertificateNum = memberCertificateNum;
	}

	/**
	 * @return the {@link #duplicatedReportMasterKey}
	 */
	public String getDuplicatedReportMasterKey() {
		return duplicatedReportMasterKey;
	}

	/**
	 * @param duplicatedReportMasterKey the {@link #duplicatedReportMasterKey} to
	 *                                  set
	 */
	public void setDuplicatedReportMasterKey(String duplicatedReportMasterKey) {
		this.duplicatedReportMasterKey = duplicatedReportMasterKey;
	}

	/**
	 * @return the {@link #reportMasterReference}
	 */
	public String getReportMasterReference() {
		return reportMasterReference;
	}

	/**
	 * @param reportMasterReference the {@link #reportMasterReference} to set
	 */
	public void setReportMasterReference(String reportMasterReference) {
		this.reportMasterReference = reportMasterReference;
	}

	/**
	 * @return the {@link #masterListId}
	 */
	public String getMasterListId() {
		return masterListId;
	}

	/**
	 * @param masterListId the {@link #masterListId} to set
	 */
	public void setMasterListId(String masterListId) {
		this.masterListId = masterListId;
	}

	/**
	 * @return the {@link #ruleId}
	 */
	public String getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId the {@link #ruleId} to set
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * @return the {@link #message}
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the {@link #message} to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the {@link #result}
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the {@link #result} to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

}