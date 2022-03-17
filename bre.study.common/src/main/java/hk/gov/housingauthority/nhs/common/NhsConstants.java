package hk.gov.housingauthority.nhs.common;

import java.util.HashMap;
import java.util.Map;

public class NhsConstants {
	public final static int BALLOT_SEED_ENTRY_MAX_CNT = 2;
	public final static int BALLOT_SEED_ENTRY_TYPE_1 = 1;
	public final static int BALLOT_SEED_ENTRY_TYPE_2 = 2;
	public final static int BALLOT_ONE_DIGIT_SEED = 1;
	public final static int BALLOT_TWO_DIGIT_SEED = 2;
	public final static int BALLOT_ONE_DIGIT_SEED_CNT = 10;
	public final static int BALLOT_TWO_DIGIT_SEED_CNT = 100;
	
	public final static String BALLOT_STATUS_CODE_SBMT = "SBMT"; //Submitted by HO
	public final static String BALLOT_STATUS_CODE_CNCL = "CNCL"; //CANCLLED by HO	
	public final static String BALLOT_STATUS_CODE_RCMD = "RCMD"; // Recommend by AHM
	public final static String BALLOT_STATUS_CODE_1ST_REJ = "1ST_REJ"; //1st Rejected by AHM
	public final static String BALLOT_STATUS_CODE_APRV = "APRV"; //Approved by HM
	public final static String BALLOT_STATUS_CODE_REJ = "REJ"; //Rejected by HM';
	
	public final static String BALLOT_TEAM = "TEAM 1";
	public final static String BALLOT_TEAM_ROLE_HO = "HO";
	public final static String BALLOT_TEAM_ROLE_RCMD = "AHM"; 
	public final static String BALLOT_TEAM_ROLE_APRV = "HM"; 
	
	public final static String BALLOT_RECOMMEND_APPROVE_WORKFLOW_DESC = "Ballot Seeds Recommendation and Approval Workflow";
	public final static String BALLOT_RESUBMIT_WORKFLOW_DESC = "Ballot Seeds Resubmission Workflow";
	
	public final static String OWNERSHIP_TEAM_ROLE_HO = "HO";
	public final static String OWNERSHIP_TEAM_ROLE_APRV = "AHM"; 
	
	public final static String APRV_ACT_CODE_SUBMIT = "SUBMIT";
	public final static String APRV_ACT_CODE_RESUBMIT = "RESUBMIT";
	public final static String APRV_ACT_CODE_APPROVE = "APPROVE";
	public final static String APRV_ACT_CODE_REJECT = "REJECT";
	public final static String APRV_ACT_CODE_WITHDRAW = "WITHDRAW";
	
	public final static String ACT_CODE_SUBMIT = "UPDATE";
	public final static String ACT_CODE_DELETE = "DELETE";
	public final static String ACT_CODE_CREATE = "CREATE";
	
	public final static String OWNERSHIP_APPROVE_WORKFLOW_DESC = "Ownership Modification Approval Workflow";
	
	//batch report
	public static final String RESULT_PARAM	= "results";
	public static final String INSTANCE_INDICATOR = "I";
	public static final String FRAGMENT_INDICATOR = "F";
	public static final String DEFAULT_DFN_ATTRIBUTE = "NIL";

	public static final String UPDATE_ZONE = "U";
	
	//Flat Selection
	public final static int FLAT_SELECTION_SELECT_FLAT_USER_LIMIT=50;
	
	public final static String FLAT_SELECTION_STATUS_APPROVE = "APPROVE";
	public final static String FLAT_SELECTION_STATUS_AWAITAPPROVE = "AWAITAPPROVE";
	public final static String FLAT_SELECTION_STATUS_BYPASS = "BYPASS";
	public final static String FLAT_SELECTION_STATUS_CONFIRM = "CONFIRM";
	public final static String FLAT_SELECTION_STATUS_PENDING = "PENDING";
	public final static String FLAT_SELECTION_STATUS_REJECT = "REJECT";
	public final static String FLAT_SELECTION_STATUS_SELECTING = "SELECTING";
	public final static String FLAT_SELECTION_STATUS_WAITING = "WAITING";
	public final static String FLAT_SELECTION_STATUS_CANCEL = "CANCEL";
	
	public final static String FLAT_SELECTION_STATUS_BYPASS_SELECTING = "BYPASS SELECTING";
	
	public final static String FLAT_SELECTION_STATUS_APPROVE_DISPLAY = "Approved";
	public final static String FLAT_SELECTION_STATUS_AWAITAPPROVE_DISPLAY = "Awaiting Approval";
	public final static String FLAT_SELECTION_STATUS_BYPASS_DISPLAY = "Bypass";
	public final static String FLAT_SELECTION_STATUS_PENDING_DISPLAY = "Pending";
	public final static String FLAT_SELECTION_STATUS_REJECT_DISPLAY = "Rejected";
	public final static String FLAT_SELECTION_STATUS_SELECTING_DISPLAY = "Selecting";
	public final static String FLAT_SELECTION_STATUS_WAITING_DISPLAY = "Waiting";
 
	
	public final static String FLAT_SELECTION_TEAM = "FLAT SELECTION TEAM";
	public final static String FLAT_SELECTION_TEAM_ROLE_HO = "HO";
	public final static String FLAT_SELECTION_TEAM_ROLE_AHM = "AHM";
	
	public final static String FLAT_SELECTION_FLAT_STATUS_VCNT = "VCNT";
	public final static String FLAT_SELECTION_FLAT_STATUS_HELD = "HELD";	
	
	public final static String FLAT_SELECTION_ASSIGN_MODE_BULK = "B";
	public final static String FLAT_SELECTION_ASSIGN_MODE_SINGLE = "S";

	//system configure
	
	public final static String SYSTEM_CONFIG_TEAM_ROLE_AHM = "AHM"; 
	public final static String SYSTEM_CONFIG_TEAM_ROLE_HO = "HO";
	public final static String SYSTEM_CONFIG_STATUS_APPROVE = "APPROVE";
	public final static String SYSTEM_CONFIG_STATUS_REJECT = "REJECT";
	public final static String SYSTEM_CONFIG_APRV_ACT_CODE_SUBMIT = "SUBMIT";
	public final static String SYSTEM_CONFIG_ACTCODE_CREATE = "CREATE"; 
	public final static String SYSTEM_CONFIG_ACTCODE_UPDATE = "UPDATE"; 
	public final static String SYSTEM_CONFIG_ACTCODE_DELETE = "DELETE"; 
	public final static String SYSTEM_CONFIG_CONDITION_WORKFLOW_Y = "Y";
	public final static String SYSTEM_CONFIG_CONDITION_WORKFLOW_N = "N";
	public final static String SYSTEM_CONFIG_CONDITION_WORKFLOW_REJECT_N = "REJECT_N";
	
	public final static long SYSTEM_CONFIG_PHASE_WORKFLOW = new Long(13);
	public final static long SYSTEM_CONFIG_SOLICITOR_WORKFLOW = new Long(10);
	public final static long SYSTEM_CONFIG_HOLIDAY_WORKFLOW = new Long(15);
	public final static long SYSTEM_CONFIG_INTERESTRATE_WORKFLOW = new Long(12);
	public final static long SYSTEM_CONFIG_COURT_WORKFLOW = new Long(18);
	public final static long SYSTEM_CONFIG_ASIGNSOLICITOR_WORKFLOW = new Long(19);
	public final static long SYSTEM_CONFIG_INTERVIEWSLOT_WORKFLOW = new Long(14);
	public final static long SYSTEM_CONFIG_CANCEL_PAYMENT_SCHEDULE_WORKFLOW = new Long(61);
	
	//signing ASP- cancel ASP
	public final static long SIGNING_ASP_CANCEL_ASP = new Long(21);
	public final static String FLAT_STATUS_SOLD = "SOLD";
	public final static String FLAT_STATUS_VCNT = "VCNT";
	public final static String FLAT_STATUS_SLCTED = "SLCTED";
	public final static String FLAT_STATUS_HELD = "HELD";
	
	public final static String FLAT_RESERVE_STATUS_HELD = "HELD";
	public final static String FLAT_RESERVE_STATUS_RELEASE = "RELEASE";
	//Purchase Settlement
	public final static String SETTLEMENT_TEAM_ROLE_HO = "HO";
	public final static String SETTLEMENT_TEAM_ROLE_AHM = "AHM"; 

	public final static int REMARK_MAX_LENGTH=250;
	//PRM - Sales and Purchase Cancellation;
	public final static String CMSPCNCL_EDIT_PYMT_RANK = "ACO";
	public final static String CMSPCNCL_APRV_RANK = "CO";
	public final static String CMSPCNCL_APRV_TEAM = "FIN PYMT TEAM";
	public final static String CMSPCNCL_EDIT_PYMT_TEAM = "FIN PYMT TEAM";
	
	//PRM - Sales Cancellation
	public static final String CMSPCNCL_TXN_TYPE = "PRM_CMSPCNCL_TXN_TYPE";
	public static final String CMSPCNCL_JRNL_TYPE_CODE = "10";
	public static final String BUYBACK_TFR_IEC_JRNL_TYPE_CODE = "10";
	
	public static final String CMSPCNCL_DFLT_TXN_TYPE ="PRM_CMSPCNCL_DFLT_TXN_TYPE";
	public static final String CMSPCNCL_DFLT_PYMT_METHOD ="PRM_CMSPCNCL_DFLT_PYMT_METHOD";
	public static final String CMSPCNCL_DFLT_PYMT_NTR ="PRM_CMSPCNCL_DFLT_PYMT_NTR";
	public static final String CMSPCNCL_DFLT_VDR ="PRM_CMSPCNCL_DFLT_VDR";
	
	public final static String CREDIT_INVC_TXN_TYPES = "PRM_CREDIT_INVC_TXN_TYPE";
	
	//PRM - Flat Buyback
	//public final static String BUYBACK_EDIT_PYMT_RANK = "ACO";
	public final static String BUYBACK_APRV_RANK = "CO";
	public final static String BUYBACK_APRV_TEAM = "FIN PYMT TEAM";
	public final static String BUYBACK_TXN_TYPE_CODE = "PRM_BYBK_TXN_TYPE";
	public final static String BUYBACK_DFLT_MRTG_GURT_VDR_TYPE_CODE = "PRM_DFLT_MRTG_VNDR_TYPE";
	//public final static String BUYBACK_EDIT_PYMT_TEAM = "FIN TEAM";
	
	//PRM -Buyback O/S Charges/Expense Payment
	public final static String BUYBACK_OS_CHARGES_PYMT_APRV_RANK = "CO";
	public final static String BUYBACK_OS_CHARGES_PYMT_APRV_TEAM = "FIN PYMT TEAM";
	public final static String BUYBACK_OS_CHARGES_PYMT_RIC_TXN_TYPE_CODE = "PRM_BYBK_OS_PYMT_RIC_TXN_TYPE";
	public final static String BUYBACK_OS_CHARGES_PYMT_IEC_TXN_TYPE_CODE = "PRM_BYBK_OS_PYMT_IEC_TXN_TYPE";
	public final static String BUYBACK_OS_CHARGES_PYMT_IEC_PLP_TXN_TYPE_CODE = "PRM_BYBK_OS_PYMT_IEC_PLP_TYPE";
	
	// PRM - Payment From Default Mortgage Guarantee Payment (Buyback)
	public final static String BUYBACK_PYMT_DFLT_MRTG_GURTE_TXN_TYPE_CODE = "PRM_BYBK_DFLT_PYMT_GURTE_TYPE";
	
	// PRM - Recovery Mortgage Transaction Type (Buyback)
	public final static String BUYBACK_RVCR_MRTG_RIC_TXN_TYPE_CODE = "PRM_BYBK_RCVR_MRTG_RIC_TYPE";
	public final static String BUYBACK_RCVR_MRTG_TXN_TYPE_CODE = "PRM_BYBK_RCVR_MRTG_TYPE";
	
	// PRM - Payment From Default Mortgage Guarantee (Buyback)
	public final static String BUYBACK_PYMT_DFLT_MRTG_GURTE_APRV_RANK = "CO";
	public final static String BUYBACK_PYMT_DFLT_MRTG_GURTE_APRV_TEAM = "FIN PYMT TEAM";
	
	//PRM - Transfer Unclaimed Deposit
	public final static String BUYBACK_TRNF_UNCLM_DPST_APRV_RANK = "CO";
	public final static String BUYBACK_TRNF_UNCLM_DPST_APRV_TEAM = "FIN PYMT TEAM";
	public final static String BUYBACK_TRNF_UNCLM_DPST_JRNL_TXN_TYPES = "PRM_INTF_TFR_DPST_TXN_TYPE";
	public static final String BUYBACK_TRNF_UNCLM_DPST_JRNL_TYPE_CODE = "07";
	
	//PRM - Writeoff Unclaimed Deposit
	public final static String BUYBACK_WRTF_UNCLM_DPST_APRV_RANK = "CO";
	public final static String BUYBACK_WRTF_UNCLM_DPST_APRV_TEAM = "FIN PYMT TEAM";
	public final static String BUYBACK_WRTF_UNCLM_DPST_JRNL_TXN_TYPES = "PRM_INTF_WRTF_DPST_TXN_TYPE";
	public static final String BUYBACK_WRTF_UNCLM_DPST_IEC_JRNL_TYPE_CODE = "08";
	
	//PRM - Refund of Premium Overpaid
	public final static String REFUND_PREMIUM_APRV_RANK = "CO";
	public final static String REFUND_PREMIUM_APRV_TEAM = "FIN PYMT TEAM";
	
	//PRM - Receipt Reversal
	public final static String RECEIPT_REVERSAL_APRV_RANK = "CO";
	public final static String RECEIPT_REVERSAL_APRV_TEAM = "REVENUE TEAM";
	
	//PRM - Forfeit Admin Fee
	public final static String ADM_FEE_ACTION_FORFEIT_ADMIN_FEE = "F";
	public static final String ADM_FEE_FORFEIT_JRNL_TYPE_CODE = "12";
	
	public final static String ADM_FEE_ACTION_REVERSE_FORFEITURE = "R";
	public static final String ADM_FEE_FORFEIT_RVRS_JRNL_TYPE_CODE = "13";
	
	
	//PRM - System Parameter
	public final static String CMSPCNCL_PARAM_CNVNT_MRTG_CNCL_PCT = "CNVNT_MRTG_CNCL_PCT";
	public final static String CMSPCNCL_PARAM_CNCL_PYE_ADDR = "DFLT_CNCL_PYE_ADDR";
	
	//PRM - References
	public final static String PRM_INVC_PYMT_METHOD_NON_OT_TYPES = "PRM_PAY_METHOD_NON_OT_TYPES";
	public final static String PRM_INVC_PYMT_METHOD_OT_TYPES = "PRM_PAY_METHOD_OT_TYPES";
	public final static String[] PRM_VENDOR_CODE_OT = new String [] {"OT", "OW"};
	
	//housing region types
	public final static String HOUSING_REGION_TYPE_DISTRICT_BROAD = "DIS";
	public final static String HOUSING_REGION_TYPE_REGIONAL_MANAGEMENT = "RM";
	public final static String HOUSING_REGION_TYPE_GEOGRAPHICAL_DISTRICT = "GEOG";
	public final static String HOUSING_REGION_TYPE_ADDRESS_DISTRICT = "ADDR";
	
	//Security
	public final static String SECURITY_NULL_STRING_VALUE_REPLACE = "-1";
	public final static int SECURITY_NULL_NUM_VALUE_REPLACE = -1;
	
	//Deposit percentage according to the applicant's form color code
	public final static double DEPOSIT_PERCENTAGE_WHITE_FORM = 10.0;
	public final static double DEPOSIT_PERCENTAGE_GREEN_FORM = 5.0;
	public final static double DEPOSIT_PERCENTAGE_HS_COURT = 5.0;
	
	//Flat Display System output filename
	public final static String FLAT_DISPLAY_DELTA_FILENAME="NHSO_SIDS_STCK_DLT";
	public final static String FLAT_DISPLAY_FULL_FILENAME="NHSO_SIDS_STCK_FULL";
	
	//scheme code
	public final static String SCHEME_CODE_HOS = "HOS";
	public final static String SCHEME_CODE_SMW = "SMW";
	public final static String SCHEME_CODE_HOS_ALTNATIVE_NAME = "NHS";
	public final static String SBSCHEME_CODE_GSH = "GSH";
	
	//phase
	public final static String DEFAULT_NULL_PHASE_CODE = "N/A";
	public final static String DEFAULT_NULL_PHASE_CODE_DESCRIPTION = "N/A - Common Module";
	
	//e-Submission
	public final static String PHASE_CODE_EAS = "EAS";
	
	public final static String FORM_COLOR_GREEN = "G";
	public final static String FORM_COLOR_WHITE = "W";
	//application
	public final static String APLY_STS_ASP_SIGNED = "ASP_SIGNED";
	public final static String APLY_STS_ACCEPTED = "ACCEPTED";
	public final static String APLY_FORM_COLOR_WHITE = "W";
	public final static String APLY_FORM_COLOR_GREEN = "G";
	
	//Court Type
	public final static String COURT_TYPE_CODE_HA = "H";
	public final static String COURT_TYPE_CODE_HS = "S";
	
	//Print Application From template filename
	public final static String PRINT_ESUB_APPL_FORM_TMPL_FILE_NAME_CHI = "_gf_chi.pdf";
	public final static String PRINT_ESUB_APPL_FORM_TMPL_FILE_NAME_ENG = "_gf_eng.pdf";
	
	public final static String PRINT_HOS_VET_FORM_TMPL_FILE_NAME_CHI = "_nhs_vet_0014_chi.pdf";
	public final static String PRINT_HOS_VET_FORM_TMPL_FILE_NAME_ENG = "_nhs_vet_0014_eng.pdf";
	public final static String PRINT_HOS_APPL_FORM_TMPL_FILE_NAME_CHI = "_nhs_vet_0014_form_chi.pdf";
	public final static String PRINT_HOS_APPL_FORM_TMPL_FILE_NAME_ENG = "_nhs_vet_0014_form_eng.pdf";
	
	public final static String PRINT_SMW_VET_FORM_TMPL_FILE_NAME_CHI = "_smw_vet_0014_chi.pdf";
	public final static String PRINT_SMW_VET_FORM_TMPL_FILE_NAME_ENG = "_smw_vet_0014_eng.pdf";
	public final static String PRINT_SMW_APPL_FORM_TMPL_FILE_NAME_CHI = "_smw_vet_0014_form_chi.pdf";
	public final static String PRINT_SMW_APPL_FORM_TMPL_FILE_NAME_ENG = "_smw_vet_0014_form_eng.pdf";
	
	public final static String BLANKPAGE = "blankpage.pdf";

	//Mapping Gender and Relationship with Applicant
	public final static Map<String, String> MAP_GENDER_IN_RELATIONSHIP = createMapGenderInRltn();
	public final static String MALE = "M";
	public final static String FEMALE = "F";
	
	private static Map<String, String> createMapGenderInRltn() {
	    Map<String, String> myMap = new HashMap<String, String>();
	    myMap.put("AD", FEMALE);
	    myMap.put("AS", MALE);
		myMap.put("AU", FEMALE);
		myMap.put("B", MALE);
		myMap.put("BL", MALE);
		myMap.put("C", FEMALE);
		myMap.put("CO", "");
		myMap.put("D", FEMALE);
		myMap.put("DL", FEMALE);
		myMap.put("F", MALE);
		myMap.put("FF", MALE);
		myMap.put("FL", MALE);
		myMap.put("FM", FEMALE);
		myMap.put("GD", FEMALE);
		myMap.put("GF", MALE);
		myMap.put("GM", FEMALE);
		myMap.put("GS", MALE);
		myMap.put("H", MALE);
		myMap.put("M", FEMALE);
		myMap.put("ML", FEMALE);
		myMap.put("NE", MALE);
		myMap.put("NI", FEMALE);
		myMap.put("OR", "");
		myMap.put("P", "");
		myMap.put("S", MALE);
		myMap.put("SB", MALE);
		myMap.put("SD", FEMALE);
		myMap.put("SF", MALE);
		myMap.put("SL", MALE);
		myMap.put("SM", FEMALE);
		myMap.put("SS", MALE);
		myMap.put("SZ", FEMALE);
		myMap.put("UN", MALE);
		myMap.put("W", FEMALE);
		myMap.put("XL", FEMALE);
		myMap.put("YL", MALE);
		myMap.put("Z", FEMALE);
		myMap.put("ZL", FEMALE);
	    return myMap;
	}
	
	//Document Print Log Merge Status
	public static final String DOC_GENERATED = "GEN";
	public static final String DOC_MERGED = "MERGE";
	public static final Map<String, String> rejectLetterList = new HashMap<String, String>();
	static{
		rejectLetterList.put("NHS-VET-0001", "Pre-Reject Letter (Eng)");
		rejectLetterList.put("NHS-VET-0002", "Pre-Reject Letter (Chin)");
		rejectLetterList.put("NHS-VET-0003", "Reject Letter (Eng)");
		rejectLetterList.put("NHS-VET-0013", "Reject Letter (Chin)");
		rejectLetterList.put("SMW-VET-0001", "Pre-Reject Letter (Eng)");
		rejectLetterList.put("SMW-VET-0002", "Pre-Reject Letter (Chin)");
		rejectLetterList.put("SMW-VET-0003", "Reject Letter (Eng)");
		rejectLetterList.put("SMW-VET-0013", "Reject Letter (Chin)");
	};
	
}
