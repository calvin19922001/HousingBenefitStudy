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

public class HousingBenefitCheckTestForTypeY {
	
	protected static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

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
				new ClassPathResource("testdata/data_sif_ems_dup_rpt_mstr_inbd__typeY.xlsx"));
		DatabaseUtils.loadDataFromExcelFile(connection, "sif_ems_dup_rpt_dtl_inbd",
				new ClassPathResource("testdata/data_sif_ems_dup_rpt_dtl_inbd__typeY.xlsx"));

	}
	
	public PhaseVo initialisePhaseForTestScenario() {
		PhaseVo phase = new PhaseVo();
		phase.setPhaseCode("019");
		try {
			phase.setApplicationEndDate(DATE_FORMAT.parse("30/05/2019 00:00:00"));
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
		member1.setHkid("B0010001");
		member1.setMaritalStatus("M");
		
		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("H");
		member2.setIdTypeCode("IC");
		member2.setHkid("B001001A");
		member2.setMaritalStatus("M");

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
				"Housing Benefit Check", Recommendation.RESULT__REJECTED);
		{
			// Step 3.1: Verify the recommendation for Member(1)
			Recommendation memberRecommendation = housingBenefitRecommendation.getChildRecommendationList().get(0);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(1)", memberRecommendation,
					null, "Housing Benefit Check for Member IC B0010001", Recommendation.RESULT__REJECTED);

			{
				// Step 3.1.1: Verify the recommendation for Y/000000001355725
				Recommendation benefitRecommendation = memberRecommendation.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001355725", benefitRecommendation,
						null, "Housing Benefit Y/000000001355725", Recommendation.RESULT__REJECTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001355725 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-BNFT-Y-0001",
						"The member is the owner of the benefit, which is ineligible for Green Form application.",
						Recommendation.RESULT__REJECTED);
				
			}
			
			// Step 3.2: Verify the recommendation for Member(2)
			Recommendation memberRecommendation1 = housingBenefitRecommendation.getChildRecommendationList().get(1);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(2)", memberRecommendation1,
					null, "Housing Benefit Check for Member IC B001001A", Recommendation.RESULT__REJECTED);
			
			{
				// Step 3.2.1: Verify the recommendation for Y/000000001355725
				Recommendation benefitRecommendation = memberRecommendation1.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001355725", benefitRecommendation,
						null, "Housing Benefit Y/000000001355725", Recommendation.RESULT__REJECTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001355725 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-BNFT-Y-0001",
						"The member is the owner of the benefit, which is ineligible for Green Form application.",
						Recommendation.RESULT__REJECTED);
				
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001355725 - Child(2)",
						benefitRecommendation.getChildRecommendationList().get(1), "VET-BNFT-Y-0002",
						"The member is the spouse of owner of the benefit, which is ineligible for Green Form application.",
						Recommendation.RESULT__REJECTED);
				
			}
		}

	}
	
	@Test
	public void testScenario2() {
		// Step 1: Define the test data
		PhaseVo phase = initialisePhaseForTestScenario();

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode(phase.getPhaseCode());
		application.setApplicationFormColor("W");
		application.setApplicationMemberList(new ArrayList<ApplicationMemberVO>());

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setHkid("B0010001");
		member1.setMaritalStatus("M");
		
		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("H");
		member2.setIdTypeCode("IC");
		member2.setHkid("B001001A");
		member2.setMaritalStatus("M");

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
					null, "Housing Benefit Check for Member IC B0010001", Recommendation.RESULT__ACCEPTED);

			{
				// Step 3.1.1: Verify the recommendation for Y/000000001355725
				Recommendation benefitRecommendation = memberRecommendation.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001355725", benefitRecommendation,
						null, "Housing Benefit Y/000000001355725", Recommendation.RESULT__ACCEPTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001355725 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-CMM-0001",
						"All checking passed.  ",
						Recommendation.RESULT__ACCEPTED);
				
			}
			
			// Step 3.2: Verify the recommendation for Member(2)
			Recommendation memberRecommendation1 = housingBenefitRecommendation.getChildRecommendationList().get(1);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(2)", memberRecommendation1,
					null, "Housing Benefit Check for Member IC B001001A", Recommendation.RESULT__ACCEPTED);
			
			{
				// Step 3.2.1: Verify the recommendation for Y/000000001355725
				Recommendation benefitRecommendation = memberRecommendation1.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001355725", benefitRecommendation,
						null, "Housing Benefit Y/000000001355725", Recommendation.RESULT__ACCEPTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001355725 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-CMM-0001",
						"All checking passed.  ",
						Recommendation.RESULT__ACCEPTED);
				
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
		member1.setHkid("B0010028");
		member1.setMaritalStatus("M");
		
		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("W");
		member2.setIdTypeCode("IC");
		member2.setHkid("B0010036");
		member2.setMaritalStatus("M");
		
		ApplicationMemberVO member3 = new ApplicationMemberVO();
		member3.setRelationshipCode("D");
		member3.setIdTypeCode("IC");
		member3.setHkid("B0010044");
		member3.setMaritalStatus("M");

		application.getApplicationMemberList().add(member1);
		application.getApplicationMemberList().add(member2);
		application.getApplicationMemberList().add(member3);

		// Step 2: Run the housing benefit check to get the recommendation
		Recommendation housingBenefitRecommendation = HousingBenefitTestUtils.runHousingBenefitCheckForApplication(
				phase, application, housingBenefitDao, housingBenefitDataDictionaryLibrary,
				housingBenefitCheckRuleLibrary);

		System.out.println("--------------------------------------------------------");
		System.out.println("housingBenefitRecommendation: ");
		HousingBenefitTestUtils.printRecommendation(housingBenefitRecommendation, StringUtils.EMPTY);

		// Step 3: Verify the recommendation
		HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check", housingBenefitRecommendation, null,
				"Housing Benefit Check", Recommendation.RESULT__REJECTED);
		{
			// Step 3.1: Verify the recommendation for Member(1)
			Recommendation memberRecommendation = housingBenefitRecommendation.getChildRecommendationList().get(0);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(1)", memberRecommendation,
					null, "Housing Benefit Check for Member IC B0010028", Recommendation.RESULT__REJECTED);

			{
				// Step 3.1.1: Verify the recommendation for Y/000000001353976
				Recommendation benefitRecommendation = memberRecommendation.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001353976", benefitRecommendation,
						null, "Housing Benefit Y/000000001353976", Recommendation.RESULT__REJECTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001353976 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-BNFT-Y-0003",
						"The member is the owner of the TPS flat assigned 10 years prior the application closing date.",
						Recommendation.RESULT__REJECTED);
				
			}
			
			// Step 3.2: Verify the recommendation for Member(2)
			Recommendation memberRecommendation1 = housingBenefitRecommendation.getChildRecommendationList().get(1);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(2)", memberRecommendation1,
					null, "Housing Benefit Check for Member IC B0010036", Recommendation.RESULT__REJECTED);
			
			{
				// Step 3.2.1: Verify the recommendation for Y/000000001353976
				Recommendation benefitRecommendation = memberRecommendation1.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001353976", benefitRecommendation,
						null, "Housing Benefit Y/000000001353976", Recommendation.RESULT__REJECTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001353976 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-BNFT-Y-0003",
						"The member is the owner of the TPS flat assigned 10 years prior the application closing date.",
						Recommendation.RESULT__REJECTED);
				
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001353976 - Child(2)",
						benefitRecommendation.getChildRecommendationList().get(1), "VET-BNFT-Y-0004",
						"The member is the spouse of owner of the TPS flat assigned 10 years prior the application closing date.",
						Recommendation.RESULT__REJECTED);
				
			}
			
			// Step 3.3: Verify the recommendation for Member(3)
			Recommendation memberRecommendation2 = housingBenefitRecommendation.getChildRecommendationList().get(2);
			HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Check of Member(3)", memberRecommendation2,
					null, "Housing Benefit Check for Member IC B0010044", Recommendation.RESULT__ACCEPTED);
			
			{
				// Step 3.3.1: Verify the recommendation for Y/000000001353976
				Recommendation benefitRecommendation = memberRecommendation2.getChildRecommendationList().get(0);
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001353976", benefitRecommendation,
						null, "Housing Benefit Y/000000001353976", Recommendation.RESULT__ACCEPTED);

				// Verify each child recommendation of the benefit recommendation
				HousingBenefitTestUtils.verifyRecommendation("Housing Benefit Y/000000001353976 - Child(1)",
						benefitRecommendation.getChildRecommendationList().get(0), "VET-CMM-0001",
						"All checking passed.  ",
						Recommendation.RESULT__ACCEPTED);
				
			}
		}

	}

	
}
