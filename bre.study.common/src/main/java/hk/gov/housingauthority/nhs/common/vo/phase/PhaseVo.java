package hk.gov.housingauthority.nhs.common.vo.phase;

import hk.gov.housingauthority.nhs.common.NhsConstants;
import hk.gov.housingauthority.nhs.framework.core.model.VersionObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PhaseVo extends VersionObject 
{
	
	private String phaseCode;
	private String phaseDescription;
	private String phaseChiDescription;
	private Date phaseStartDate;
	private Date saleEndDate;
	private Date phaseEndDate;
	private Date applicationEndDate;
	private Date applicationCloseDate;
	//private String currentSalePhaseIndicator;
	private BigDecimal applicationFee;
	private BigDecimal topUpApplicationFee;
	private BigDecimal sellingPriceDiscount;
	private Date applicationStageEndDate;
	//private String lastRecTxnTypeCode;
	//private int version;
	private String schemeCode;
	private String subSchemeCode;
	
	private String housekeepInd;
	private Date housekeepCmpltDate;
	private String housekeepCmpltInd;
	
	private Long applicationCount;
	private Long housekeepCount;
	private Long retainCount;
	
	private BigDecimal cashierOrderGFAmount;
	private BigDecimal cashierOrderWFAmount;
	
	private String cashierOrderGFChiText;
	private String cashierOrderWFChiText;
	
	private BigDecimal gfPageCount;
	private BigDecimal wfPageCount;
	private String chiLetterPrintingSchemeName;
	private String engLetterPrintingSchemeName;
	
	private Date ackLtrGenDate;
	private Date ackEmailGenDate;
	private Date ackLtrIssueDate;
	
	private List<PhaseIncomeAssetLimitVo> phaseIncomeAssetLimitVoList = new ArrayList<PhaseIncomeAssetLimitVo>();
	
	public String getPhaseCode() 
	{
		return phaseCode;
	}
	
	public void setPhaseCode(String phaseCode) 
	{
		this.phaseCode = phaseCode;
	}

	public String getPhaseDescription() 
	{
		return phaseDescription;
	}
	
	public void setPhaseDescription(String phaseDescription) 
	{
		this.phaseDescription = phaseDescription;
	}
		
	public String getPhaseChiDescription() 
	{
		return phaseChiDescription;
	}
	
	public void setPhaseChiDescription(String phaseChiDescription) 
	{
		this.phaseChiDescription = phaseChiDescription;
	}
	
	public Date getPhaseStartDate() 
	{
			return phaseStartDate;
	}
	
	public void setPhaseStartDate(Date phaseStartDate) 
	{
		this.phaseStartDate = phaseStartDate;
	}
	
	public Date getSaleEndDate() 
	{
		return saleEndDate;
	}
	
	public void setSaleEndDate(Date saleEndDate) 
	{
		this.saleEndDate = saleEndDate;
	}
	
	public Date getPhaseEndDate() 
	{
		return phaseEndDate;
	}
	
	public void setPhaseEndDate(Date phaseEndDate) {
		this.phaseEndDate = phaseEndDate;
	}
	
	public Date getApplicationEndDate() 
	{
		return applicationEndDate;
	}
	
	public void setApplicationEndDate(Date applicationEndDate) {
		this.applicationEndDate = applicationEndDate;
	}
	
	public Date getApplicationCloseDate() 
	{
		return applicationCloseDate;
	}
	
	public void setApplicationCloseDate(Date applicationCloseDate) {
		this.applicationCloseDate = applicationCloseDate;
	}
	
	public void setApplicationStageEndDate(
			Date applicationStageEndDate) {
		this.applicationStageEndDate = applicationStageEndDate;
	}
	
	public Date getApplicationStageEndDate() {
		return applicationStageEndDate;
	}
	/* public String getCurrentSalePhaseIndicator() 
		{
			return currentSalePhaseIndicator;
		}
		public void setCurrentSalePhaseIndicator(String currentSalePhaseIndicator) {
			this.currentSalePhaseIndicator = currentSalePhaseIndicator;
		}*/
	public BigDecimal getApplicationFee() 
	{
		return applicationFee;
	}
	public void setApplicationFee(BigDecimal applicationFee) {
		this.applicationFee = applicationFee;
	}
		
	public BigDecimal getTopUpApplicationFee() 
	{
		return topUpApplicationFee;
	}

	public void setTopUpApplicationFee(BigDecimal topUpApplicationFee) {
		this.topUpApplicationFee = topUpApplicationFee;
	}

	public BigDecimal getSellingPriceDiscount() 
	{
		return sellingPriceDiscount;
	}
	
	public void setSellingPriceDiscount(BigDecimal sellingPriceDiscount) {
		this.sellingPriceDiscount = sellingPriceDiscount;
	}
			//	public String getLastRecTxnTypeCode() 
	//{
	//	return lastRecTxnTypeCode;
	//}
	//public void setLastRecTxnTypeCode(String lastRecTxnTypeCode) {
	//	this.lastRecTxnTypeCode = lastRecTxnTypeCode;
	//	}
	//	public int getversion() 
	//{
	//		return version;
	//	}
	//public void setVersion(int version) {
	//	this.version = version;
	//}

	public String getSchemeCode() {
		return schemeCode;
	}
					 
	public void setSchemeCode(String schemeCode) 
	{
		this.schemeCode = schemeCode;
	}
	
	
	//Daniel Ho 20151126
	public void setDefaultNoPhaseVo()
	{
   		setPhaseDescription(NhsConstants.DEFAULT_NULL_PHASE_CODE_DESCRIPTION);
   		setPhaseCode(NhsConstants.DEFAULT_NULL_PHASE_CODE);
   		setSchemeCode(NhsConstants.SCHEME_CODE_HOS);
	}
	//Daniel Ho
	
	
	//HL 20151223
	public String getPhaseCodeAndDesc()
	{
   		return (phaseCode.equals(NhsConstants.DEFAULT_NULL_PHASE_CODE)?"":phaseCode+ " - ") + phaseDescription;
	}
	//HL 20151223

	public String getHousekeepInd() {
		return housekeepInd;
	}

	public void setHousekeepInd(String housekeepInd) {
		this.housekeepInd = housekeepInd;
	}

	public String getHousekeepCmpltInd() {
		return housekeepCmpltInd;
	}

	public void setHousekeepCmpltInd(String housekeepCmpltInd) {
		this.housekeepCmpltInd = housekeepCmpltInd;
	}

	public Long getApplicationCount() {
		return applicationCount;
	}

	public void setApplicationCount(Long applicationCount) {
		this.applicationCount = applicationCount;
	}

	public Long getHousekeepCount() {
		return housekeepCount;
	}

	public void setHousekeepCount(Long housekeepCount) {
		this.housekeepCount = housekeepCount;
	}

	public Long getRetainCount() {
		return retainCount;
	}

	public void setRetainCount(Long retainCount) {
		this.retainCount = retainCount;
	}

	public BigDecimal getCashierOrderGFAmount() {
		return cashierOrderGFAmount;
	}

	public void setCashierOrderGFAmount(BigDecimal cashierOrderGFAmount) {
		this.cashierOrderGFAmount = cashierOrderGFAmount;
	}

	public BigDecimal getCashierOrderWFAmount() {
		return cashierOrderWFAmount;
	}

	public void setCashierOrderWFAmount(BigDecimal cashierOrderWFAmount) {
		this.cashierOrderWFAmount = cashierOrderWFAmount;
	}

	public Date getHousekeepCmpltDate() {
		return housekeepCmpltDate;
	}

	public void setHousekeepCmpltDate(Date housekeepCmpltDate) {
		this.housekeepCmpltDate = housekeepCmpltDate;
	}
	
	public boolean isWSM(){
		return NhsConstants.SCHEME_CODE_SMW.equalsIgnoreCase(schemeCode);
	}

	public String getCashierOrderGFChiText() {
		return cashierOrderGFChiText;
	}

	public void setCashierOrderGFChiText(String cashierOrderGFChiText) {
		this.cashierOrderGFChiText = cashierOrderGFChiText;
	}

	public String getCashierOrderWFChiText() {
		return cashierOrderWFChiText;
	}

	public void setCashierOrderWFChiText(String cashierOrderWFChiText) {
		this.cashierOrderWFChiText = cashierOrderWFChiText;
	}

	public void setSubSchemeCode(String subSchemeCode) {
		this.subSchemeCode = subSchemeCode;
	}

	public String getSubSchemeCode() {
		return subSchemeCode;
	}

	public BigDecimal getGfPageCount() {
		return gfPageCount;
	}

	public void setGfPageCount(BigDecimal gfPageCount) {
		this.gfPageCount = gfPageCount;
	}

	public BigDecimal getWfPageCount() {
		return wfPageCount;
	}

	public void setWfPageCount(BigDecimal wfPageCount) {
		this.wfPageCount = wfPageCount;
	}

	public String getChiLetterPrintingSchemeName() {
		return chiLetterPrintingSchemeName;
	}

	public void setChiLetterPrintingSchemeName(String chiLetterPrintingSchemeName) {
		this.chiLetterPrintingSchemeName = chiLetterPrintingSchemeName;
	}

	public String getEngLetterPrintingSchemeName() {
		return engLetterPrintingSchemeName;
	}

	public void setEngLetterPrintingSchemeName(String engLetterPrintingSchemeName) {
		this.engLetterPrintingSchemeName = engLetterPrintingSchemeName;
	}

	public Date getAckLtrGenDate() {
		return ackLtrGenDate;
	}

	public void setAckLtrGenDate(Date ackLtrGenDate) {
		this.ackLtrGenDate = ackLtrGenDate;
	}

	public Date getAckEmailGenDate() {
		return ackEmailGenDate;
	}

	public void setAckEmailGenDate(Date ackEmailGenDate) {
		this.ackEmailGenDate = ackEmailGenDate;
	}

	public Date getAckLtrIssueDate() {
		return ackLtrIssueDate;
	}

	public void setAckLtrIssueDate(Date ackLtrIssueDate) {
		this.ackLtrIssueDate = ackLtrIssueDate;
	}

	public void setPhaseIncomeAssetLimitVoList(List<PhaseIncomeAssetLimitVo> phaseIncomeAssetLimitVoList) {
		this.phaseIncomeAssetLimitVoList = phaseIncomeAssetLimitVoList;
	}

	public List<PhaseIncomeAssetLimitVo> getPhaseIncomeAssetLimitVoList() {
		return phaseIncomeAssetLimitVoList;
	}
	
}
