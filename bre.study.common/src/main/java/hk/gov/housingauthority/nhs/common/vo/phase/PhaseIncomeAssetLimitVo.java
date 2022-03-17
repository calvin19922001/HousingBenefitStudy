package hk.gov.housingauthority.nhs.common.vo.phase;

import hk.gov.housingauthority.nhs.framework.core.model.VersionObject;

import java.math.BigDecimal;

public class PhaseIncomeAssetLimitVo extends VersionObject 
{
	private Long prcsInstId;
	private String aprvActCode;
	private String actCode;
	private String phaseCode;
	private BigDecimal householdSize;
	private BigDecimal incomeLimit;
	private BigDecimal assetLimit;
	
	public Long getPrcsInstId() {
		return prcsInstId;
	}
	public void setPrcsInstId(Long prcsInstId) {
		this.prcsInstId = prcsInstId;
	}
	public String getAprvActCode() {
		return aprvActCode;
	}
	public void setAprvActCode(String aprvActCode) {
		this.aprvActCode = aprvActCode;
	}
	public String getActCode() {
		return actCode;
	}
	public void setActCode(String actCode) {
		this.actCode = actCode;
	}
	public String getPhaseCode() {
		return phaseCode;
	}
	public void setPhaseCode(String phaseCode) {
		this.phaseCode = phaseCode;
	}
	public BigDecimal getHouseholdSize() {
		return householdSize;
	}
	public void setHouseholdSize(BigDecimal householdSize) {
		this.householdSize = householdSize;
	}
	public BigDecimal getIncomeLimit() {
		return incomeLimit;
	}
	public void setIncomeLimit(BigDecimal incomeLimit) {
		this.incomeLimit = incomeLimit;
	}
	public BigDecimal getAssetLimit() {
		return assetLimit;
	}
	public void setAssetLimit(BigDecimal assetLimit) {
		this.assetLimit = assetLimit;
	}
}
