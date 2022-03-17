package hk.gov.housingauthority.nhs.vettingcheck.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.common.vo.phase.PhaseVo;
import hk.gov.housingauthority.nhs.recommendation.Recommendation;
import hk.gov.housingauthority.nhs.rules.test.utils.EligibilityTestUtils;
import hk.gov.housingauthority.nhs.rules.test.utils.TestUtils;
import hk.gov.housingauthority.nhs.rules.util.RecommendationUtils;
import hk.gov.housingauthority.nhs.vettingcheck.library.EligibilityCheckRuleLibrary;

/**
 * JUnit test for testing the different scenarios for the eligibility check
 * 
 * @author BRE Revamp POC Team
 *
 */
public class EligibilityCheckTest {

	/**
	 * Spring application context
	 */
	static protected ApplicationContext applicationContext;

	/**
	 * Eligibility rule library ({@link EligibilityCheckRuleLibrary}) retrieved from
	 * spring application context
	 */
	static protected EligibilityCheckRuleLibrary eligibilityCheckRuleLibrary;

	/**
	 * Initialise the class before all test scenario
	 * 
	 * 1) Initialise the {@link #applicationContext} and
	 * {@link #EligibilityCheckRuleLibrary}
	 */
	@BeforeClass
	static public void beforeAllTests() {
		applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
		eligibilityCheckRuleLibrary = (EligibilityCheckRuleLibrary) applicationContext
				.getBean("eligibilityCheckRuleLibrary");
	}

	/**
	 * <strong>Test Scenario (1)</strong> <br/>
	 * <span style="text-decoration:underline;">Input: </span><br/>
	 * Application End Date: 20190530<br/>
	 * Current Business Date: 20200301<br/>
	 * Phase Code: 019 (i.e. Rule Version: EGBL_019)<br/>
	 * Application: Form Color: W<br/>
	 * FEP Indicator: N<br/>
	 * Member (1): <br/>
	 * - Relationship Code: P<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 20010529<br/>
	 * <span style="text-decoration:underline;">Expected result: </span><br/>
	 * Eligibility Check Recommendation: accept<br/>
	 * Child Recommendation (1): VET-CMM-0001, accept, All checking passed.<br/>
	 */
	@Test
	public void testScenario1() {
		// Part 1: Construct the test scenario
		PhaseVo phase = new PhaseVo();
		phase.setApplicationEndDate(TestUtils.convertDateFormYYYYMMDDString("20190530"));

		Date currentBusinessDate = TestUtils.convertDateFormYYYYMMDDString("20200301");

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("N");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setDateOfBirth("20010529");

		memberList.add(member1);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the eligibility check
		Recommendation eligibilityCheckRecommendation = EligibilityTestUtils.runEligibilityCheck(phase, application,
				currentBusinessDate, eligibilityCheckRuleLibrary);
		RecommendationUtils.computeResultForRecommendation(eligibilityCheckRecommendation);

		// Part 3: Check the result
		TestUtils.verifyRecommendation("Eligibility Check", eligibilityCheckRecommendation, null, null,
				Recommendation.RESULT__ACCEPTED);
		TestUtils.verifyRecommendation("Child recommendation (1)",
				eligibilityCheckRecommendation.getChildRecommendationList().get(0), "VET-CMM-0001",
				"All checking passed.  ", Recommendation.RESULT__ACCEPTED);
	}

	/**
	 * <strong>Test Scenario (2)</strong> <br/>
	 * <span style="text-decoration:underline;">Input: </span><br/>
	 * Application End Date: 20190530<br/>
	 * Current Business Date: 20200301<br/>
	 * Phase Code: 019 (i.e. Rule Version: EGBL_019)<br/>
	 * Application: Form Color: W<br/>
	 * FEP Indicator: N<br/>
	 * Member (1): <br/>
	 * - Relationship Code: P<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 20010530<br/>
	 * <span style="text-decoration:underline;">Expected result: </span><br/>
	 * Eligibility Check Recommendation: accept<br/>
	 * Child Recommendation (1): VET-CMM-0001, accept, All checking passed.<br/>
	 */
	@Test
	public void testScenario2() {
		// Part 1: Construct the test scenario
		PhaseVo phase = new PhaseVo();
		phase.setApplicationEndDate(TestUtils.convertDateFormYYYYMMDDString("20190530"));

		Date currentBusinessDate = TestUtils.convertDateFormYYYYMMDDString("20200301");

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("N");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setDateOfBirth("20010530");

		memberList.add(member1);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the eligibility check
		Recommendation eligibilityCheckRecommendation = EligibilityTestUtils.runEligibilityCheck(phase, application,
				currentBusinessDate, eligibilityCheckRuleLibrary);
		RecommendationUtils.computeResultForRecommendation(eligibilityCheckRecommendation);

		// Part 3: Check the result
		TestUtils.verifyRecommendation("Eligibility Check", eligibilityCheckRecommendation, null, null,
				Recommendation.RESULT__ACCEPTED);
		TestUtils.verifyRecommendation("Child recommendation (1)",
				eligibilityCheckRecommendation.getChildRecommendationList().get(0), "VET-CMM-0001",
				"All checking passed.  ", Recommendation.RESULT__ACCEPTED);
	}

	/**
	 * <strong>Test Scenario (3)</strong> <br/>
	 * <span style="text-decoration:underline;">Input: </span><br/>
	 * Application End Date: 20190530<br/>
	 * Current Business Date: 20200301<br/>
	 * Phase Code: 019 (i.e. Rule Version: EGBL_019)<br/>
	 * Application: Form Color: W<br/>
	 * FEP Indicator: N<br/>
	 * Member (1): <br/>
	 * - Relationship Code: P<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 20010531<br/>
	 * <span style="text-decoration:underline;">Expected result: </span><br/>
	 * Eligibility Check Recommendation: reject<br/>
	 * Child Recommendation (1): VET-EGBL-0001, reject, Principal is under 18 years
	 * old on the application closing date.<br/>
	 */
	@Test
	public void testScenario3() {
		// Part 1: Construct the test scenario
		PhaseVo phase = new PhaseVo();
		phase.setApplicationEndDate(TestUtils.convertDateFormYYYYMMDDString("20190530"));

		Date currentBusinessDate = TestUtils.convertDateFormYYYYMMDDString("20200301");

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("N");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setDateOfBirth("20010531");

		memberList.add(member1);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the eligibility check
		Recommendation eligibilityCheckRecommendation = EligibilityTestUtils.runEligibilityCheck(phase, application,
				currentBusinessDate, eligibilityCheckRuleLibrary);
		RecommendationUtils.computeResultForRecommendation(eligibilityCheckRecommendation);

		// Part 3: Check the result
		TestUtils.verifyRecommendation("Eligibility Check", eligibilityCheckRecommendation, null, null,
				Recommendation.RESULT__REJECTED);
		TestUtils.verifyRecommendation("Child recommendation (1)",
				eligibilityCheckRecommendation.getChildRecommendationList().get(0), "VET-EGBL-0001",
				"Principal is under 18 years old on the application closing date.  ", Recommendation.RESULT__REJECTED);
	}

	/**
	 * <strong>Test Scenario (4)</strong> <br/>
	 * <span style="text-decoration:underline;">Input: </span><br/>
	 * Application End Date: 20190530<br/>
	 * Current Business Date: 20200301<br/>
	 * Phase Code: 019 (i.e. Rule Version: EGBL_019)<br/>
	 * Application: Form Color: W<br/>
	 * FEP Indicator: Y<br/>
	 * Member (1): <br/>
	 * - Relationship Code: P<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 20010530<br/>
	 * Member (2): <br/>
	 * - Relationship Code: B<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 19590530<br/>
	 * <span style="text-decoration:underline;">Expected result: </span><br/>
	 * Eligibility Check Recommendation: reject<br/>
	 * Child Recommendation (1): VET-EGBL-0002, reject, This is not a nuclear
	 * family, ineligible for White Form FEP.<br/>
	 */
	@Test
	public void testScenario4() {
		// Part 1: Construct the test scenario
		PhaseVo phase = new PhaseVo();
		phase.setApplicationEndDate(TestUtils.convertDateFormYYYYMMDDString("20190530"));

		Date currentBusinessDate = TestUtils.convertDateFormYYYYMMDDString("20200301");

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("Y");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setDateOfBirth("20010530");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("B");
		member2.setIdTypeCode("IC");
		member2.setDateOfBirth("19590530");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the eligibility check
		Recommendation eligibilityCheckRecommendation = EligibilityTestUtils.runEligibilityCheck(phase, application,
				currentBusinessDate, eligibilityCheckRuleLibrary);
		RecommendationUtils.computeResultForRecommendation(eligibilityCheckRecommendation);

		// Part 3: Check the result
		TestUtils.verifyRecommendation("Eligibility Check", eligibilityCheckRecommendation, null, null,
				Recommendation.RESULT__REJECTED);
		TestUtils.verifyRecommendation("Child recommendation (1)",
				eligibilityCheckRecommendation.getChildRecommendationList().get(0), "VET-EGBL-0002",
				"This is not a nuclear family, ineligible for White Form FEP.", Recommendation.RESULT__REJECTED);
	}

	/**
	 * <strong>Test Scenario (5)</strong> <br/>
	 * <span style="text-decoration:underline;">Input: </span><br/>
	 * Application End Date: 20190530<br/>
	 * Current Business Date: 20200301<br/>
	 * Phase Code: 019 (i.e. Rule Version: EGBL_019)<br/>
	 * Application: Form Color: G<br/>
	 * FEP Indicator: Y<br/>
	 * Member (1): <br/>
	 * - Relationship Code: P<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 20010530<br/>
	 * Member (2): <br/>
	 * - Relationship Code: B<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 19590530<br/>
	 * <span style="text-decoration:underline;">Expected result: </span><br/>
	 * Eligibility Check Recommendation: accept<br/>
	 * Child Recommendation (1): VET-CMM-0001, accept, All checking passed.<br/>
	 */
	@Test
	public void testScenario5() {
		// Part 1: Construct the test scenario
		PhaseVo phase = new PhaseVo();
		phase.setApplicationEndDate(TestUtils.convertDateFormYYYYMMDDString("20190530"));

		Date currentBusinessDate = TestUtils.convertDateFormYYYYMMDDString("20200301");

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("G");
		application.setJoinElderlyMemberScheme("Y");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setDateOfBirth("20010530");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("B");
		member2.setIdTypeCode("IC");
		member2.setDateOfBirth("19590530");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the eligibility check
		Recommendation eligibilityCheckRecommendation = EligibilityTestUtils.runEligibilityCheck(phase, application,
				currentBusinessDate, eligibilityCheckRuleLibrary);
		RecommendationUtils.computeResultForRecommendation(eligibilityCheckRecommendation);

		// Part 3: Check the result
		TestUtils.verifyRecommendation("Eligibility Check", eligibilityCheckRecommendation, null, null,
				Recommendation.RESULT__ACCEPTED);
		TestUtils.verifyRecommendation("Child recommendation (1)",
				eligibilityCheckRecommendation.getChildRecommendationList().get(0), "VET-CMM-0001",
				"All checking passed.  ", Recommendation.RESULT__ACCEPTED);
	}

	/**
	 * <strong>Test Scenario (6)</strong> <br/>
	 * <span style="text-decoration:underline;">Input: </span><br/>
	 * Application End Date: 20190530<br/>
	 * Current Business Date: 20200301<br/>
	 * Phase Code: 019 (i.e. Rule Version: EGBL_019)<br/>
	 * Application: Form Color: W<br/>
	 * FEP Indicator: Y<br/>
	 * Member (1): <br/>
	 * - Relationship Code: P<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 20010531<br/>
	 * Member (2): <br/>
	 * - Relationship Code: F<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 19590530<br/>
	 * <span style="text-decoration:underline;">Expected result: </span><br/>
	 * Eligibility Check Recommendation: reject<br/>
	 * Child Recommendation (1): VET-EGBL-0001, reject, Principal is under 18 years
	 * old on the application closing date.<br/>
	 */
	@Test
	public void testScenario6() {
		// Part 1: Construct the test scenario
		PhaseVo phase = new PhaseVo();
		phase.setApplicationEndDate(TestUtils.convertDateFormYYYYMMDDString("20190530"));

		Date currentBusinessDate = TestUtils.convertDateFormYYYYMMDDString("20200301");

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("Y");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setDateOfBirth("20010531");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("F");
		member2.setIdTypeCode("IC");
		member2.setDateOfBirth("19590530");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the eligibility check
		Recommendation eligibilityCheckRecommendation = EligibilityTestUtils.runEligibilityCheck(phase, application,
				currentBusinessDate, eligibilityCheckRuleLibrary);
		RecommendationUtils.computeResultForRecommendation(eligibilityCheckRecommendation);

		// Part 3: Check the result
		TestUtils.verifyRecommendation("Eligibility Check", eligibilityCheckRecommendation, null, null,
				Recommendation.RESULT__REJECTED);
		TestUtils.verifyRecommendation("Child recommendation (1)",
				eligibilityCheckRecommendation.getChildRecommendationList().get(0), "VET-EGBL-0001",
				"Principal is under 18 years old on the application closing date.  ", Recommendation.RESULT__REJECTED);
	}

	/**
	 * <strong>Test Scenario (7)</strong> <br/>
	 * <span style="text-decoration:underline;">Input: </span><br/>
	 * Application End Date: 20190530<br/>
	 * Current Business Date: 20200301<br/>
	 * Phase Code: 019 (i.e. Rule Version: EGBL_019)<br/>
	 * Application: Form Color: W<br/>
	 * FEP Indicator: Y<br/>
	 * Member (1): <br/>
	 * Relationship Code: P<br/>
	 * - Relationship Code: P<br/>
	 * - ID Type Code: IC<br/>
	 * - Date of Birth: 20010531<br/>
	 * Member (2): <br/>
	 * - Relationship Code: F<br/>
	 * - ID Type Code: BC<br/>
	 * - ID Number: A0000011<br/>
	 * - Date of Birth: 19590530<br/>
	 * <span style="text-decoration:underline;">Expected result: </span><br/>
	 * Eligibility Check Recommendation: reject<br/>
	 * Child Recommendation (1): VET-EGBL-0001, reject, Principal is under 18 years
	 * old on the application closing date.<br/>
	 * Child Recommendation (2): VET-EGBL-0005, follow up, Member with age over 11
	 * years old holding birth certificate: BC A0000011.<br/>
	 */
	@Test
	public void testScenario7() {
		// Part 1: Construct the test scenario
		PhaseVo phase = new PhaseVo();
		phase.setApplicationEndDate(TestUtils.convertDateFormYYYYMMDDString("20190530"));

		Date currentBusinessDate = TestUtils.convertDateFormYYYYMMDDString("20200301");

		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("Y");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		member1.setIdTypeCode("IC");
		member1.setDateOfBirth("20010531");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("F");
		member2.setIdTypeCode("BC");
		member2.setCertificateNum("A0000011");
		member2.setDateOfBirth("19590530");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the eligibility check
		Recommendation eligibilityCheckRecommendation = EligibilityTestUtils.runEligibilityCheck(phase, application,
				currentBusinessDate, eligibilityCheckRuleLibrary);
		RecommendationUtils.computeResultForRecommendation(eligibilityCheckRecommendation);

		// Part 3: Check the result
		TestUtils.verifyRecommendation("Eligibility Check", eligibilityCheckRecommendation, null, null,
				Recommendation.RESULT__REJECTED);
		TestUtils.verifyRecommendation("Child recommendation (1)",
				eligibilityCheckRecommendation.getChildRecommendationList().get(0), "VET-EGBL-0001",
				"Principal is under 18 years old on the application closing date.  ", Recommendation.RESULT__REJECTED);
		TestUtils.verifyRecommendation("Child recommendation (2)",
				eligibilityCheckRecommendation.getChildRecommendationList().get(1), "VET-EGBL-0005",
				"Member with age over 11 years old holding birth certificate: BC A0000011.",
				Recommendation.RESULT__FOLLOW_UP);
	}
}
