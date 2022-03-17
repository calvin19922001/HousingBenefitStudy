package hk.gov.housingauthority.nhs.common.vo.assignApplication;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import hk.gov.housingauthority.nhs.framework.core.model.VersionObject;

public class PriorityInfo extends VersionObject implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String userId;
	private Long applicationKey;
	private Long applicationApprovalLogId;
	private String applicationNumber;	
	private String applicationStatus;
	private String vettingStatus;
	private BigDecimal priorityNumber;
	private String displayPriorityNumber;
	private String categoryCode;
	private String categoryGroupCode;
	private String isUnassigned;
	private String isNew;
	private String isInProgress;
	private String isCompleted;
	private String phaseCode;
	
	private String applicantSurname;
	private String applicantGivenName;
	
	private String actionCode;
	private String applicationApprovalRemark;
	
	private String referenceApplicationKey;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getApplicationKey() {
		return applicationKey;
	}
	public void setApplicationKey(Long applicationKey) {
		this.applicationKey = applicationKey;
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
	public String getApplicationNumber() {
		return applicationNumber;
	}
	public void setApplicationNumber(String applicationNumber) {
		this.applicationNumber = applicationNumber;
	}
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	public String getVettingStatus() {
		return vettingStatus;
	}
	public void setVettingStatus(String vettingStatus) {
		this.vettingStatus = vettingStatus;
	}
	public BigDecimal getPriorityNumber() {
		return priorityNumber;
	}
	public void setPriorityNumber(BigDecimal priorityNumber) {
		this.priorityNumber = priorityNumber;
	}
	public String getDisplayPriorityNumber() {
		return displayPriorityNumber;
	}
	public void setDisplayPriorityNumber(String displayPriorityNumber) {
		this.displayPriorityNumber = displayPriorityNumber;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryGroupCode() {
		return categoryGroupCode;
	}
	public void setCategoryGroupCode(String categoryGroupCode) {
		this.categoryGroupCode = categoryGroupCode;
	}
	public String getIsUnassigned() {
		return isUnassigned;
	}
	public void setIsUnassigned(String isUnassigned) {
		this.isUnassigned = isUnassigned;
	}
	public String getIsNew() {
		return isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getIsInProgress() {
		return isInProgress;
	}
	public void setIsInProgress(String isInProgress) {
		this.isInProgress = isInProgress;
	}
	public String getIsCompleted() {
		return isCompleted;
	}
	public void setIsCompleted(String isCompleted) {
		this.isCompleted = isCompleted;
	}
	public String getApplicantSurname() {
		return applicantSurname;
	}
	public void setApplicantSurname(String applicantSurname) {
		this.applicantSurname = applicantSurname;
	}
	public String getApplicantGivenName() {
		return applicantGivenName;
	}
	public void setApplicantGivenName(String applicantGivenName) {
		this.applicantGivenName = applicantGivenName;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
	public String getApplicationApprovalRemark() {
		return applicationApprovalRemark;
	}
	public void setApplicationApprovalRemark(String applicationApprovalRemark) {
		this.applicationApprovalRemark = applicationApprovalRemark;
	}

	private static final Map<String, String> displayMap;
    static {
        Map<String, String> aMap = new HashMap<String, String>();
        aMap.put("0", "A");
        aMap.put("1", "B");
        aMap.put("2", "C");
        aMap.put("3", "D");
        aMap.put("4", "E");
        aMap.put("5", "F");
        aMap.put("6", "G");
        aMap.put("7", "H");
        aMap.put("8", "J");
        aMap.put("9", "K");
        displayMap = Collections.unmodifiableMap(aMap);
    }
    private static final Map<String, String> valueMap;
    static {
        Map<String, String> aMap = new HashMap<String, String>();
        aMap.put("A", "0");
        aMap.put("B", "1");
        aMap.put("C", "2");
        aMap.put("D", "3");
        aMap.put("E", "4");
        aMap.put("F", "5");
        aMap.put("G", "6");
        aMap.put("H", "7");
        aMap.put("J", "8");
        aMap.put("K", "9");
        valueMap = Collections.unmodifiableMap(aMap);
    }
    
    public static String convertPriorityNumToString(BigDecimal priorityNum) {
		String integerNum = "";
		if (priorityNum != null && StringUtils.isNotBlank(priorityNum.toString())) {
			String[] priorityNumArray = priorityNum.toString().split("\\.");
			integerNum = priorityNumArray[0];
			while (integerNum.length()<6) {
				integerNum = "0"+integerNum;
			}
			if (priorityNumArray.length >= 2) {
				String decimalNum = "";
				char[] decimalNumArray = priorityNumArray[1].toCharArray();
				for (char decimal : decimalNumArray) {
					String key = String.valueOf(decimal);
					decimalNum += displayMap.get(key);
				}
				if (decimalNum.length() > 2) {
					integerNum = integerNum+decimalNum.substring(0,2);
				} else {
					while (decimalNum.length()<2) {
						decimalNum = decimalNum+"A";
					}
					integerNum = integerNum+decimalNum;
				}
			}
		}
		return integerNum;
	}
    
    public static BigDecimal convertPriorityNumToNumber(String priorityNum) {
    	BigDecimal newPriorityNum = new BigDecimal("0");
    	try {
    		if (priorityNum != null && StringUtils.isNotBlank(priorityNum)) {
        		if (StringUtils.isNumeric(priorityNum)) {
        			newPriorityNum = new BigDecimal(priorityNum);
        		} else {
        			String lastTwoStr = priorityNum.substring(priorityNum.length()-2, priorityNum.length());
            		if (StringUtils.isNumeric(lastTwoStr)) {
            			while (priorityNum.length()<6) {
            				priorityNum = "0"+priorityNum;
            			}
            		} else {
            			while (priorityNum.length()<8) {
            				priorityNum = "0"+priorityNum;
            			}
            		}
            		String integerNum = priorityNum.substring(0, priorityNum.length()-2);
            		if (priorityNum.length() >= 8) {
        				String originalDecimalNum = priorityNum.substring(priorityNum.length()-2, priorityNum.length());
            			String decimalNum = "";
        				char[] decimalNumArray = originalDecimalNum.toCharArray();
        				for (char decimal : decimalNumArray) {
        					String key = String.valueOf(decimal).toUpperCase();
        					decimalNum += valueMap.get(key);
        				}
        				if (decimalNum.length() > 2) {
        					integerNum = integerNum+"."+decimalNum.substring(0,2);
        				} else {
        					integerNum = integerNum+"."+decimalNum;
        				}
            		}
            		newPriorityNum = new BigDecimal(integerNum);
        		}
        	}
    	} catch (Exception e) {
    		newPriorityNum = new BigDecimal("0");
    	}
    	return newPriorityNum;
    }
    
    public static String formatFindApplicaton(String findApplication) {
	    String formatedFindApplication = findApplication;
    	Pattern pattern = Pattern.compile("^[A-Za-z]{1,2}");
    	Matcher matcher = pattern.matcher(findApplication);
    	if (matcher.find())
    	{
    		String priorCatgCode = matcher.group(0);
    		if (priorCatgCode.length() == 2 && "P".equals(priorCatgCode.substring(0, 1))) {
    			priorCatgCode = priorCatgCode.substring(1);
    		}
       	    BigDecimal newPriorityNum;
    	    try {
    	    	newPriorityNum = new BigDecimal(findApplication.substring(matcher.group(0).length()));
    	    	formatedFindApplication = priorCatgCode + convertPriorityNumToString(newPriorityNum);
    	    } catch (Exception e) {
    	    	return formatedFindApplication;
    	    }
    	}
    	return formatedFindApplication;
    }
    
    public static Map<String, String> dividePriorityGroupAndPriorityNum(String priorityNumInput) {
    	Map<String, String> prefixPriorityMap = new HashMap<String, String>();
    	String priorityGroup = "";
		String priorityNum = "";
		Pattern pat = Pattern.compile("([A-Z]*)(.*)");
		Matcher mat = pat.matcher(priorityNumInput);
		if (mat.matches()) {
			priorityGroup = mat.group(1);
			priorityNum = convertPriorityNumToNumber(mat.group(2)).toString();
		}
		prefixPriorityMap.put("priorityGroup", priorityGroup);
		prefixPriorityMap.put("priorityNum", priorityNum);
		return prefixPriorityMap;
    }
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setReferenceApplicationKey(String referenceApplicationKey) {
		this.referenceApplicationKey = referenceApplicationKey;
	}
	public String getReferenceApplicationKey() {
		return referenceApplicationKey;
	}
}
