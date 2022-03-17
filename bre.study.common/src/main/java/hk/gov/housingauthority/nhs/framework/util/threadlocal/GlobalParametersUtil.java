package hk.gov.housingauthority.nhs.framework.util.threadlocal;

import hk.gov.housingauthority.nhs.framework.util.threadlocal.ThreadLocalDataBean;

/**
 * =============================================================<br>
 * Copyright 2006 Suzsoft                                       <br>
 * -------------------------------------------------------------<br>
 * Description:                                                 <br>
 * NA                                                           <br>
 * -------------------------------------------------------------<br>
 * Revision:                                                    <br>
 * Date              Author            Remarks                  <br>
 * Sep 4, 2006     James.Zhang       Created class              <br>
 * =============================================================<br>
 */
public final class GlobalParametersUtil {

    public static final String _LOGIN_USER_ID = "_LOGIN_USER_ID";
	// modified by Chris [Begin, 2008-9-5]
	// public static final String _OFFICE_ID = "_OFFICE_ID";
	public static final String _TERMINAL_ID = "_TERMINAL_ID";
	
	
	// 20150727 Daniel Ho
	// As it is discovered that DBMS.getSession("NHS_CONTEXT") cannot serves
	// as a session varible for each login user but just acts like a global variable
	// for all login users, hence it couldn't be use and need to use a new
	// thread local variable to store the session scheme code
	public static final String _SCHEME_CODE = "_SCHEME_CODE";
	public static final String _PHASE_CODE = "_PHASE_CODE"; 
	
	
	// End Daniel Ho
	
	
	

    public static synchronized void setLoginUserId(String loginUserId) {
        ThreadLocalDataBean.setProperty(_LOGIN_USER_ID, loginUserId);
    }

    public static synchronized String getLoginUserId() {
    	String userId = (String) ThreadLocalDataBean.getProperty(_LOGIN_USER_ID);
    	if (userId == null || "".equals(userId)) {
    		userId = "EST1_HO_1";		    	//TODO: remove the assumption userid
    	}
        return userId;
    }

    public static synchronized String getTerminalId() {
        return (String) ThreadLocalDataBean.getProperty(_TERMINAL_ID);
    }

    public static synchronized void setTerminalId(String terminalId) {
        ThreadLocalDataBean.setProperty(_TERMINAL_ID, terminalId);
    }

    /* Added by Keith for data-level security on 9 Jun 2010 - begin */
    public static final String _FUNCTION_ID = "_FUNCTION_ID";
    
    public static synchronized String getFunctionId() {
        return (String) ThreadLocalDataBean.getProperty(_FUNCTION_ID);
    }

    public static synchronized void setFunctionId(String functionId) {
        ThreadLocalDataBean.setProperty(_FUNCTION_ID, functionId);
    }
    
    
	// 20150727 Daniel Ho
    public static synchronized String getSchemeCode() {
        return (String) ThreadLocalDataBean.getProperty(_SCHEME_CODE);
    }

    public static synchronized void setSchemeCode(String schemeCode) {
        ThreadLocalDataBean.setProperty(_SCHEME_CODE, schemeCode);
    }
    
    public static synchronized String getPhaseCode() {
        return (String) ThreadLocalDataBean.getProperty(_PHASE_CODE);
    }

    public static synchronized void setPhaseCode(String phaseCode) {
        ThreadLocalDataBean.setProperty(_PHASE_CODE, phaseCode);
    }
    
    
    
	// End Daniel Ho
    
    
    /* Added by Keith for data-level security on 9 Jun 2010 - end */
    
    /*
    public static String getOfficeId() {
        return (String) ThreadLocalDataBean.getProperty(_OFFICE_ID);
    }

    public static void setOfficeId(String officeId) {
        ThreadLocalDataBean.setProperty(_OFFICE_ID, officeId);
    }*/
    // modified by Chris [End]

}
