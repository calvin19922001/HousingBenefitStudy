package hk.gov.housingauthority.nhs.housingbenefit.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import hk.gov.housingauthority.nhs.housingbenefit.datadict.HousingBenefitDataDictionary;
import hk.gov.housingauthority.nhs.housingbenefit.datadict.library.HousingBenefitDataDictionaryLibrary;
import hk.gov.housingauthority.nhs.rules.dao.housingbenefit.HousingBenefitDao;
import hk.gov.housingauthority.nhs.rules.test.utils.DatabaseUtils;
import hk.gov.housingauthority.nhs.rules.test.utils.HousingBenefitTestUtils;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefit;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefitField;
import hk.gov.housingauthority.nhs.rules.vo.housingbenefit.HousingBenefitMember;

public class HousingBenefitLoadDataTest {

	protected static ApplicationContext applicationContext;
	protected static DriverManagerDataSource dataSource;
	protected static Connection connection;

	protected static HousingBenefitDao housingBenefitDao;
	protected static HousingBenefitDataDictionaryLibrary housingBenefitDataDictionaryLibrary;

	@BeforeClass
	static public void beforeAllTests() throws SQLException, IOException {
		applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
		dataSource = (DriverManagerDataSource) applicationContext.getBean("dataSource");
		connection = dataSource.getConnection();

		housingBenefitDao = (HousingBenefitDao) applicationContext.getBean("housingBenefitDao");
		housingBenefitDataDictionaryLibrary = (HousingBenefitDataDictionaryLibrary) applicationContext
				.getBean("housingBenefitDataDictionaryLibrary");

		createTablesAndLoadData();
	}

	private static void createTablesAndLoadData() throws SQLException, IOException {
		// Create Tables
		DatabaseUtils.executeRawSql(connection, new ClassPathResource("sql/create_tables.sql"));

		// Load Data
		DatabaseUtils.loadDataFromExcelFile(connection, "sif_ems_dup_rpt_mstr_inbd",
				new ClassPathResource("testdata/data_sif_ems_dup_rpt_mstr_inbd__datadict.xlsx"));
		DatabaseUtils.loadDataFromExcelFile(connection, "sif_ems_dup_rpt_dtl_inbd",
				new ClassPathResource("testdata/data_sif_ems_dup_rpt_dtl_inbd__datadict.xlsx"));

	}

	@Test
	public void testScenario1() {
		String phaseCode = "019";
		String duplicatedReportMasterKey = "000000000702312";
		String reportMasterReference = "T/000000003087058";
		String masterListId = "00001";

		HousingBenefitDataDictionary dictionary = housingBenefitDataDictionaryLibrary.getDictionaryByDictionaryVersion(
				HousingBenefitTestUtils.getHousingBenefitDataDictionaryVersion(phaseCode));
		List<HousingBenefit> housingBenefitList = HousingBenefitTestUtils.loadHousingBenefitListWithMappedFields(
				duplicatedReportMasterKey, HousingBenefitDataDictionary.MAPPER_MODE__VETTING, dictionary,
				housingBenefitDao);

		HousingBenefit housingBenefit = retriveBenefitRecord(housingBenefitList, duplicatedReportMasterKey,
				reportMasterReference, masterListId);

		checkBenefitField(housingBenefit, "tenancyAbuser", "Tenancy Abuser", true, true, "");
		checkBenefitField(housingBenefit, "codeAddress", "Code Address", true, false, "E/YW  / YS/  511/ / ");
		checkBenefitField(housingBenefit, "dateOfCommencement", "Date of Commencement", true, false, "2015-10-01");
		checkBenefitField(housingBenefit, "rehousingCategory", "Rehousing Category", true, false, "UNQGNT");
		checkBenefitField(housingBenefit, "householdScore", "Household Score", true, true, "0");
		checkBenefitField(housingBenefit, "currentRentInformation:RentFactor",
				"Current Rent Information:   Rent Factor", true, false, "1");
		checkBenefitField(housingBenefit, "currentRentInformation:RentEffectiveDate",
				"Current Rent Information:   Rent Effective Date", true, true, "2015-10-01");
		checkBenefitField(housingBenefit, "currentRentInformation:RentReviewCategory",
				"Current Rent Information:   Rent Review Category", true, true, "GNT");
		checkBenefitField(housingBenefit, "currentRentInformation:RentExpiryDate",
				"Current Rent Information:   Rent Expiry Date", true, false, "2020-04-01");
		checkBenefitField(housingBenefit, "dateOfTermination", "Date of Termination ", true, false, "");
		checkBenefitField(housingBenefit, "ntqExpiryDate", "NTQ Expiry Date", true, true, "");
		checkBenefitField(housingBenefit, "licenceExpiryDate", "Licence Expiry Date", true, false, "");
		checkBenefitField(housingBenefit, "householdDebar", "Household Debar", true, true, "");

		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "englishName", "English Name", true,
				false, "YEXXG XXI XXXXG");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "chineseName", "Chinese Name", true,
				false, "\u694aXXXX");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "idTypeCode",
				"ID Type Code (not for display)", false, false, "IC");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "idNumber",
				"ID Number (not for display)", false, true, "A0000003");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "sex", "Sex", true, false, "M");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "dateOfBirth", "Date of Birth", true,
				false, "10/09/1973");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "relationship", "Relationship", true,
				false, "P");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "maritalSatus", "Marital Status", true,
				false, "D");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "relatedIdTypeAndNumber",
				"Related ID Type / No.", true, false, "IC A000000(3)");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "householdIndicator",
				"Household Indicator", true, false, "P");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "upIn", "UP IN", true, false, "N");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "ocSt", "OC ST", true, false, "A");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "tmpStayStartDate",
				"Tmp Stay Start Date", false, false, "");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "tmpStayEndDate", "Tmp Stay End Date",
				false, false, "");
	}

	@Test
	public void testScenario2() {
		String phaseCode = "019";
		String duplicatedReportMasterKey = "000000000703286";
		String reportMasterReference = "Y/000000001353976";
		String masterListId = "00001";

		HousingBenefitDataDictionary dictionary = housingBenefitDataDictionaryLibrary.getDictionaryByDictionaryVersion(
				HousingBenefitTestUtils.getHousingBenefitDataDictionaryVersion(phaseCode));
		List<HousingBenefit> housingBenefitList = HousingBenefitTestUtils.loadHousingBenefitListWithMappedFields(
				duplicatedReportMasterKey, HousingBenefitDataDictionary.MAPPER_MODE__VETTING, dictionary,
				housingBenefitDao);

		HousingBenefit housingBenefit = retriveBenefitRecord(housingBenefitList, duplicatedReportMasterKey,
				reportMasterReference, masterListId);
		
		checkBenefitField(housingBenefit, "codeAddress", "Code Address", true, false, "E/CON / OY/  604/ / ");
		checkBenefitField(housingBenefit, "dateOfAssignment", "Date of Assignment", true, true, "2003-10-28");
		checkBenefitField(housingBenefit, "exOwnerCode", "Ex-Owner Code", true, true, "");
		checkBenefitField(housingBenefit, "originallyPurchaseFrom", "Originally Purchase from", true, false, "SM");
		checkBenefitField(housingBenefit, "dateOfTermination", "Date of Termination", true, true, "");
		
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "englishName", "Name", true,
				true, "LIXX YXXFUXXXX");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "chineseName", "Chinese Name", true,
				true, "XXXX");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "idTypeCode", "ID Type Code (not for display)", false,
				true, "IC");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "idNumber", "ID Number (not for display)", false,
				true, "A0000011");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "sex", "Sex", true,
				true, "M");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "dateOfBirth", "Date of Birth (not for display)", true,
				false, "1943-01-01");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "ownerInd", "Relationship", true,
				true, "O");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "relationship", "Marital Status", true,
				true, "P");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "31", "Elderly Indicator", true,
				true, "");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(0), "16", "RR Indicator", true,
				true, "");
		
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "englishName", "Name", true,
				true, "SOXXAIXXINXXXX");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "chineseName", "Chinese Name", true,
				true, "XXXX");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "idTypeCode", "ID Type Code (not for display)", false,
				true, "IC");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "idNumber", "ID Number (not for display)", false,
				true, "A000002A");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "sex", "Sex", true,
				true, "F");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "dateOfBirth", "Date of Birth (not for display)", true,
				false, "1952-08-05");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "ownerInd", "Relationship", true,
				true, "J");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "relationship", "Marital Status", true,
				true, "W");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "31", "Elderly Indicator", true,
				true, "");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(1), "16", "RR Indicator", true,
				true, "");
		
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "englishName", "Name", true,
				true, "LIXX MXXXXXX");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "chineseName", "Chinese Name", true,
				true, "XXXX");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "idTypeCode", "ID Type Code (not for display)", false,
				true, "IC");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "idNumber", "ID Number (not for display)", false,
				true, "A0000038");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "sex", "Sex", true,
				true, "F");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "dateOfBirth", "Date of Birth (not for display)", true,
				false, "1982-04-07");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "ownerInd", "Relationship", true,
				true, "N");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "relationship", "Marital Status", true,
				true, "D");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "31", "Elderly Indicator", true,
				true, "");
		checkMemberField(housingBenefit, housingBenefit.getMemberList().get(2), "16", "RR Indicator", true,
				true, "");
	}

	public HousingBenefit retriveBenefitRecord(List<HousingBenefit> housingBenefitList,
			String duplicatedReportMasterKey, String reportMasterReference, String masterListId) {

		HousingBenefit result = IterableUtils.find(housingBenefitList, new Predicate<HousingBenefit>() {
			@Override
			public boolean evaluate(HousingBenefit housingBenefit) {
				return duplicatedReportMasterKey.equals(housingBenefit.getDuplicatedReportMasterKey())
						&& reportMasterReference.equals(housingBenefit.getReportMasterReference())
						&& masterListId.equals(housingBenefit.getMasterListId());
			}
		});
		return result;
	}

	protected void checkBenefitField(HousingBenefit housingBenefit, String fieldKey, String displayName,
			boolean forDisplay, boolean forDiscrepancyCheck, String value) {

		Assert.assertTrue(String.format("Key [%1$s] exists for benefit [%2$s, %3$s, %4$s]", fieldKey,
				housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
				housingBenefit.getMasterListId()), housingBenefit.getFieldMap().containsKey(fieldKey));

		HousingBenefitField field = housingBenefit.getFieldMap().get(fieldKey);
		Assert.assertTrue(String.format("Display name for field [%1$s] of benefit [%2$s, %3$s, %4$s] is \"%5$s\"",
				fieldKey, housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
				housingBenefit.getMasterListId(), displayName), displayName.equals(field.getDisplayName()));
		Assert.assertTrue(
				String.format("Field [%1$s] of benefit [%2$s, %3$s, %4$s] %5$s for display", fieldKey,
						housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
						housingBenefit.getMasterListId(), (forDisplay ? "is" : "is not")),
				field.isForDisplay() == forDisplay);
		Assert.assertTrue(
				String.format("Field [%1$s] of benefit [%2$s, %3$s, %4$s] %5$s for discrepancy check", fieldKey,
						housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
						housingBenefit.getMasterListId(), (forDiscrepancyCheck ? "is" : "is not")),
				field.isForDiscrepancyCheck() == forDiscrepancyCheck);
		Assert.assertTrue(String.format("Value for field [%1$s] of benefit [%2$s, %3$s, %4$s] is \"%5$s\"", fieldKey,
				housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
				housingBenefit.getMasterListId(), value), value.equals(field.getValue()));
	}

	protected void checkMemberField(HousingBenefit housingBenefit, HousingBenefitMember member, String fieldKey,
			String displayName, boolean isForDisplay, boolean forDiscrepancyCheck, String value) {

		String memberIdTypeCode = member.getFieldMap().get("idTypeCode").getValue();
		String memberIdNumber = member.getFieldMap().get("idNumber").getValue();
		String memberEnglishName = member.getFieldMap().get("englishName").getValue();

		Assert.assertTrue(String.format("Key [%1$s] exists for member[%2$s %3$s, %4$s] of benefit [%5$s, %6$s, %7$s]",
				fieldKey, memberIdTypeCode, memberIdNumber, memberEnglishName,
				housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
				housingBenefit.getMasterListId()), member.getFieldMap().containsKey(fieldKey));

		HousingBenefitField field = member.getFieldMap().get(fieldKey);
		Assert.assertTrue(String.format(
				"Display name for field [%1$s] of member[%2$s %3$s, %4$s] of benefit [%5$s, %6$s, %7$s] is \"%8$s\"",
				fieldKey, memberIdTypeCode, memberIdNumber, memberEnglishName,
				housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
				housingBenefit.getMasterListId(), displayName), displayName.equals(field.getDisplayName()));
		Assert.assertTrue(
				String.format("Field [%1$s] of member[%2$s %3$s, %4$s] of benefit [%5$s, %6$s, %7$s] %8$s for display",
						fieldKey, memberIdTypeCode, memberIdNumber, memberEnglishName,
						housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
						housingBenefit.getMasterListId(), (isForDisplay ? "is" : "is not")),
				field.isForDisplay() == isForDisplay);
		Assert.assertTrue(String.format(
				"Field [%1$s] of member[%2$s %3$s, %4$s] of benefit [%5$s, %6$s, %7$s] %8$s for discrepancy check",
				fieldKey, memberIdTypeCode, memberIdNumber, memberEnglishName,
				housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
				housingBenefit.getMasterListId(), (forDiscrepancyCheck ? "is" : "is not")),
				field.isForDiscrepancyCheck() == forDiscrepancyCheck);
		Assert.assertTrue(String.format("Value for field [%1$s] of benefit [%2$s, %3$s, %4$s] is \"%5$s\"", fieldKey,
				housingBenefit.getDuplicatedReportMasterKey(), housingBenefit.getReportMasterReference(),
				housingBenefit.getMasterListId(), value), value.equals(field.getValue()));
	}
}
