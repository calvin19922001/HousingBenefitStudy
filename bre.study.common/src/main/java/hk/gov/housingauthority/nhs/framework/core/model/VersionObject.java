package hk.gov.housingauthority.nhs.framework.core.model;

import hk.gov.housingauthority.nhs.framework.util.DateUtil;
import hk.gov.housingauthority.nhs.framework.util.threadlocal.GlobalParametersUtil;

import java.util.Date;

public abstract class VersionObject extends BaseObject {
    private static final long serialVersionUID = 8465940882055132018L;

    public static final String REC_TYPE_CODE_INSERT = "I";
    public static final String REC_TYPE_CODE_UPDATE = "U";
    public static final String REC_TYPE_CODE_DELETE = "D";

    protected Long version;

	protected Date lastRecTxnDate;

	protected String lastRecTxnTypeCode;

	protected String lastRecTxnUserId;

	public VersionObject() {
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	// version control

	public void populateLastRecTxn() {
		setLastRecTxnUserId(GlobalParametersUtil.getLoginUserId());
        setLastRecTxnDate(DateUtil.getSystemTimestamp());
	}

    public void populateLastRecTxn(String lastRecTxnTypeCode){
        setLastRecTxnTypeCode(lastRecTxnTypeCode);
        setLastRecTxnUserId(GlobalParametersUtil.getLoginUserId());
        setLastRecTxnDate(DateUtil.getSystemTimestamp());
    }
    public void populateCreateBean(String userId) {

	}

	public void populateUpdateBean(String userId) {

	}

	/**
	 * @return Returns the lastRecTxnDate.
	 */
	public Date getLastRecTxnDate() {
		return lastRecTxnDate;
	}

	/**
	 * @param lastRecTxnDate
	 *            The lastRecTxnDate to set.
	 */
	public void setLastRecTxnDate(Date lastRecTxnDate) {
		this.lastRecTxnDate = lastRecTxnDate;
	}

	/**
	 * @return Returns the lastRecTxnTypeCode.
	 */
	public String getLastRecTxnTypeCode() {
		return lastRecTxnTypeCode;
	}

	/**
	 * @param lastRecTxnTypeCode
	 *            The lastRecTxnTypeCode to set.
	 */
	public void setLastRecTxnTypeCode(String lastRecTxnTypeCode) {
		this.lastRecTxnTypeCode = lastRecTxnTypeCode;
	}

	/**
	 * @return Returns the lastRecTxnUserId.
	 */
	public String getLastRecTxnUserId() {
		return lastRecTxnUserId;
	}

	/**
	 * @param lastRecTxnUserId
	 *            The lastRecTxnUserId to set.
	 */
	public void setLastRecTxnUserId(String lastRecTxnUserId) {
		this.lastRecTxnUserId = lastRecTxnUserId;
	}

}