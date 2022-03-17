package hk.gov.housingauthority.nhs.housingbenefit.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.housingbenefit.check.library.HousingBenefitCheckRuleLibrary;
import hk.gov.housingauthority.nhs.housingbenefit.datadict.library.HousingBenefitDataDictionaryLibrary;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.dao.housingbenefit.HousingBenefitDao;
import hk.gov.housingauthority.nhs.rules.test.utils.DatabaseUtils;
import hk.gov.housingauthority.nhs.rules.test.utils.HousingBenefitTestUtils;

public class HousingBenefitCheckTestForTypeT {

	protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMmdd");

	protected static ApplicationContext applicationContext;
	protected static DriverManagerDataSource dataSource;
	protected static Connection connection;

	protected static HousingBenefitDao housingBenefitDao;
	protected static HousingBenefitDataDictionaryLibrary housingBenefitDataDictionaryLibrary;
	protected static HousingBenefitCheckRuleLibrary housingBenefitCheckRuleLibrary;

	@BeforeClass
	static public void beforeAllTests() throws SQLException, IOException {
		applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
		dataSource = (DriverManagerDataSource) applicationContext.getBean("dataSource");
		connection = dataSource.getConnection();

		housingBenefitDao = (HousingBenefitDao) applicationContext.getBean("housingBenefitDao");
		housingBenefitDataDictionaryLibrary = (HousingBenefitDataDictionaryLibrary) applicationContext
				.getBean("housingBenefitDataDictionaryLibrary");
		housingBenefitCheckRuleLibrary = (HousingBenefitCheckRuleLibrary) applicationContext
				.getBean("housingBenefitCheckRuleLibrary");

		createTablesAndLoadData();
	}

	protected static void createTablesAndLoadData() throws SQLException, IOException {
		// Create Tables
		DatabaseUtils.executeRawSql(connection, new ClassPathResource("sql/create_tables.sql"));

		// Load Data
		DatabaseUtils.loadDataFromExcelFile(connection, "sif_ems_dup_rpt_mstr_inbd",
				new ClassPathResource("testdata/data_sif_ems_dup_rpt_mstr_inbd__typeT.xlsx"));
		DatabaseUtils.loadDataFromExcelFile(connection, "sif_ems_dup_rpt_dtl_inbd",
				new ClassPathResource("testdata/data_sif_ems_dup_rpt_dtl_inbd__typeT.xlsx"));

	}

	public PhaseVo initialisePhaseForTestScenario() {
		PhaseVo phase = new PhaseVo();
		phase.setPhaseCode("019");
		try {
			phase.setApplicationEndDate(DATE_FORMAT.parse("20190530"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return phase;
	}

	@Test
	public void testScenario1() {
		// Step 1: Define the test data
		PhaseVo phase = initialisePhaseForTestScenario();

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode(phase.getPhaseCode());
		application.setApplicationFormColor("G");
		application.setApplicationMemberList(new ArrayList<ApplicationMemberVO>());

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setHkid("B0000006");
		member1.setMaritalStatus("D");

		application.getApplicationMemberList().add(member1);

		// Step 2: Run the housing benefit check to get the recommendation
		Recommendation housingBenefitRecommendation = HousingBenefitTestUtils.runHousingBenefitCheckForApplication(
				phase, application, housingBenefitDao, housingBenefitDataDictionaryLibrary,
				housingBenefitCheckRuleLibrary);

		System.out.println("--------------------------------------------------------");
		System.out.println("housingBenefitRecommendation: ");
		HousingBenefitTestUtils.printRecommendation(housingBenefitRecommendation, StringUtils.EMPTY);

		// Step 3: Verify the recommendation
		HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check", housingBenefitRecommendation, null,
				"Housing Benefit Check", Recommendation.RESULT__FOLLOW_UP);
		{
			// Step 3.1: Verify the recommendation for Member(1)
			Recommendation memberRecommendation = housingBenefitRecommendation.getChildRecommendationList().get(0);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(1)", memberRecommendation,
					null, "Housing Benefit Check for Member IC B0000006", Recommendation.RESULT__FOLLOW_UP);

			{
				// Step 3.1.1: Verify the recommendation for T/000000003090951
				Recommendation benefitRecommendation = memberRecommendation.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003090951", benefitRecommendation,
						null, "Housing Benefit T/000000003090951", Recommendation.RESULT__FOLLOW_UP);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003090951 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-BNFT-T-0001",
						"The tenancy abuser is not blank.  Please send a memo to estate office for clarification.",
						Recommendation.RESULT__FOLLOW_UP);

				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003090951 - Child(2)",
						benefitRecommendation.getChildRecommendationList().get(1), "VET-BNFT-T-0003",
						"The NTQ expiry date is not blank.  Please send a memo to estate office for clarification.",
						Recommendation.RESULT__FOLLOW_UP);
				
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003090951 - Child(3)",
						benefitRecommendation.getChildRecommendationList().get(2), "VET-BNFT-T-0004",
						"The Household Debar is not blank.  Please send a memo to estate office for clarification.",
						Recommendation.RESULT__FOLLOW_UP);
				
			}
		}

	}
	
	@Test
	public void testScenario2() {
		// Step 1: Define the test data
		PhaseVo phase = initialisePhaseForTestScenario();

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode(phase.getPhaseCode());
		application.setApplicationFormColor("G");
		application.setApplicationMemberList(new ArrayList<ApplicationMemberVO>());

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setHkid("B0000014");
		member1.setMaritalStatus("D");

		application.getApplicationMemberList().add(member1);

		// Step 2: Run the housing benefit check to get the recommendation
		Recommendation housingBenefitRecommendation = HousingBenefitTestUtils.runHousingBenefitCheckForApplication(
				phase, application, housingBenefitDao, housingBenefitDataDictionaryLibrary,
				housingBenefitCheckRuleLibrary);

		System.out.println("--------------------------------------------------------");
		System.out.println("housingBenefitRecommendation: ");
		HousingBenefitTestUtils.printRecommendation(housingBenefitRecommendation, StringUtils.EMPTY);

		// Step 3: Verify the recommendation
		HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check", housingBenefitRecommendation, null,
				"Housing Benefit Check", Recommendation.RESULT__FOLLOW_UP);
		{
			// Step 3.1: Verify the recommendation for Member(1)
			Recommendation memberRecommendation = housingBenefitRecommendation.getChildRecommendationList().get(0);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(1)", memberRecommendation,
					null, "Housing Benefit Check for Member IC B0000014", Recommendation.RESULT__FOLLOW_UP);

			{
				// Step 3.1.1: Verify the recommendation for T/000000003087058
				Recommendation benefitRecommendation = memberRecommendation.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003087058", benefitRecommendation,
						null, "Housing Benefit T/000000003087058", Recommendation.RESULT__FOLLOW_UP);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003087058 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-BNFT-T-0002",
						"The household score is not under 16.  Please send a memo to estate office for clarification.",
						Recommendation.RESULT__FOLLOW_UP);

				
			}
		}

	}
	
	@Test
	public void testScenario3() {
		// Step 1: Define the test data
		PhaseVo phase = initialisePhaseForTestScenario();

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode(phase.getPhaseCode());
		application.setApplicationFormColor("W");
		application.setApplicationMemberList(new ArrayList<ApplicationMemberVO>());

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setHkid("B0000022");
		member1.setMaritalStatus("W");
		
		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("S");
		member2.setIdTypeCode("IC");
		member2.setHkid("B0000030");
		member2.setMaritalStatus("S");

		application.getApplicationMemberList().add(member1);
		application.getApplicationMemberList().add(member2);

		// Step 2: Run the housing benefit check to get the recommendation
		Recommendation housingBenefitRecommendation = HousingBenefitTestUtils.runHousingBenefitCheckForApplication(
				phase, application, housingBenefitDao, housingBenefitDataDictionaryLibrary,
				housingBenefitCheckRuleLibrary);

		System.out.println("--------------------------------------------------------");
		System.out.println("housingBenefitRecommendation: ");
		HousingBenefitTestUtils.printRecommendation(housingBenefitRecommendation, StringUtils.EMPTY);

		// Step 3: Verify the recommendation
		HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check", housingBenefitRecommendation, null,
				"Housing Benefit Check", Recommendation.RESULT__ACCEPTED);
		{
			// Step 3.1: Verify the recommendation for Member(1)
			Recommendation memberRecommendation = housingBenefitRecommendation.getChildRecommendationList().get(0);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(1)", memberRecommendation,
					null, "Housing Benefit Check for Member IC B0000022", Recommendation.RESULT__ACCEPTED);

			{
				// Step 3.1.1: Verify the recommendation for T/000000003176112
				Recommendation benefitRecommendation = memberRecommendation.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003176112", benefitRecommendation,
						null, "Housing Benefit T/000000003176112", Recommendation.RESULT__ACCEPTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003176112 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-CMM-0001",
						"All checking passed.  ",
						Recommendation.RESULT__ACCEPTED);
				
			}
			
			// Step 3.2: Verify the recommendation for Member(2)
			Recommendation memberRecommendation1 = housingBenefitRecommendation.getChildRecommendationList().get(1);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(2)", memberRecommendation1,
					null, "Housing Benefit Check for Member IC B0000030", Recommendation.RESULT__ACCEPTED);
			
			{
				// Step 3.2.1: Verify the recommendation for T/000000003176112
				Recommendation benefitRecommendation = memberRecommendation1.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003176112", benefitRecommendation,
						null, "Housing Benefit T/000000003176112", Recommendation.RESULT__ACCEPTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000003176112 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-CMM-0001",
						"All checking passed.  ",
						Recommendation.RESULT__ACCEPTED);
				
			}
		}

	}
	
	@Test
	public void testScenario4() {
		// Step 1: Define the test data
		PhaseVo phase = initialisePhaseForTestScenario();

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode(phase.getPhaseCode());
		application.setApplicationFormColor("G");
		application.setApplicationMemberList(new ArrayList<ApplicationMemberVO>());

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setHkid("B0000057");
		member1.setMaritalStatus("M");

		application.getApplicationMemberList().add(member1);

		// Step 2: Run the housing benefit check to get the recommendation
		Recommendation housingBenefitRecommendation = HousingBenefitTestUtils.runHousingBenefitCheckForApplication(
				phase, application, housingBenefitDao, housingBenefitDataDictionaryLibrary,
				housingBenefitCheckRuleLibrary);

		System.out.println("--------------------------------------------------------");
		System.out.println("housingBenefitRecommendation: ");
		HousingBenefitTestUtils.printRecommendation(housingBenefitRecommendation, StringUtils.EMPTY);

		// Step 3: Verify the recommendation
		HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check", housingBenefitRecommendation, null,
				"Housing Benefit Check", Recommendation.RESULT__FOLLOW_UP);
		{
			// Step 3.1: Verify the recommendation for Member(1)
			Recommendation memberRecommendation = housingBenefitRecommendation.getChildRecommendationList().get(0);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(1)", memberRecommendation,
					null, "Housing Benefit Check for Member IC B0000057", Recommendation.RESULT__FOLLOW_UP);

			{
				// Step 3.1.1: Verify the recommendation for T/000000000602782
				Recommendation benefitRecommendation = memberRecommendation.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000000602782", benefitRecommendation,
						null, "Housing Benefit T/000000000602782", Recommendation.RESULT__FOLLOW_UP);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000000602782 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-BNFT-T-0005",
						"The household particulars on application does not match with the benefit record.  Please send a memo to estate office for clarification.",
						Recommendation.RESULT__FOLLOW_UP);

				
			}
		}

	}
	
	@Test
	public void testScenario5() {
		// Step 1: Define the test data
		PhaseVo phase = initialisePhaseForTestScenario();

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode(phase.getPhaseCode());
		application.setApplicationFormColor("G");
		application.setApplicationMemberList(new ArrayList<ApplicationMemberVO>());

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setHkid("B0000073");
		member1.setMaritalStatus("M");
		
		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("GS");
		member2.setIdTypeCode("IC");
		member2.setHkid("B0000065");
		member2.setMaritalStatus("S");

		application.getApplicationMemberList().add(member1);
		application.getApplicationMemberList().add(member2);

		// Step 2: Run the housing benefit check to get the recommendation
		Recommendation housingBenefitRecommendation = HousingBenefitTestUtils.runHousingBenefitCheckForApplication(
				phase, application, housingBenefitDao, housingBenefitDataDictionaryLibrary,
				housingBenefitCheckRuleLibrary);

		System.out.println("--------------------------------------------------------");
		System.out.println("housingBenefitRecommendation: ");
		HousingBenefitTestUtils.printRecommendation(housingBenefitRecommendation, StringUtils.EMPTY);

		// Step 3: Verify the recommendation
		HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check", housingBenefitRecommendation, null,
				"Housing Benefit Check", Recommendation.RESULT__FOLLOW_UP);
		{
			// Step 3.1: Verify the recommendation for Member(1)
			Recommendation memberRecommendation = housingBenefitRecommendation.getChildRecommendationList().get(0);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(1)", memberRecommendation,
					null, "Housing Benefit Check for Member IC B0000073", Recommendation.RESULT__FOLLOW_UP);

			{
				// Step 3.1.1: Verify the recommendation for T/000000002492122
				Recommendation benefitRecommendation = memberRecommendation.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000002492122", benefitRecommendation,
						null, "Housing Benefit T/000000002492122", Recommendation.RESULT__FOLLOW_UP);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000002492122 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-BNFT-T-0006",
						"The marital status of the member on application does not match with the benefit record.  Please send a memo to estate office for clarification.",
						Recommendation.RESULT__FOLLOW_UP);
				
			}
			
			// Step 3.2: Verify the recommendation for Member(2)
			Recommendation memberRecommendation1 = housingBenefitRecommendation.getChildRecommendationList().get(1);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(2)", memberRecommendation1,
					null, "Housing Benefit Check for Member IC B0000065", Recommendation.RESULT__ACCEPTED);
			
			{
				// Step 3.2.1: Verify the recommendation for T/000000002492122
				Recommendation benefitRecommendation = memberRecommendation1.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000002492122", benefitRecommendation,
						null, "Housing Benefit T/000000002492122", Recommendation.RESULT__ACCEPTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit T/000000002492122 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-CMM-0001",
						"All checking passed.  ",
						Recommendation.RESULT__ACCEPTED);
				
			}
		}

	}
	
}
