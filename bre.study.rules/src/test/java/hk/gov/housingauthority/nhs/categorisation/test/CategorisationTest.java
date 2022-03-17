package hk.gov.housingauthority.nhs.categorisation.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import hk.gov.housingauthority.nhs.categorisation.library.CategorisationRuleLibrary;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.rules.test.utils.CategorisationTestUtils;

/**
 * JUnit test for testing the different scenarios for the categorisation
 * 
 * @author BRE Revamp POC Team
 *
 */
public class CategorisationTest {

	/**
	 * Spring application context
	 */
	static protected ApplicationContext applicationContext;
	/**
	 * Categorisation rule library ({@link CategorisationRuleLibrary}) retrieved
	 * from spring application context
	 */
	static protected CategorisationRuleLibrary categorisationRuleLibrary;

	/**
	 * Initialise the class before all test scenario
	 * 
	 * 1) Initialise the {@link #applicationContext} and
	 * {@link #categorisationRuleLibrary}
	 */
	@BeforeClass
	static public void beforeAllTests() {
		applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
		categorisationRuleLibrary = (CategorisationRuleLibrary) applicationContext.getBean("categorisationRuleLibrary");
	}

	/**
	 * <strong>Test Scenario (1)</strong> <br/>
	 * Phase Code: 019 (i.e. Rule Version: CATG_019)<br/>
	 * Application: Form Colour: G<br/>
	 * FEP Indicator: Y<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Member (2): Relationship Code: H<br/>
	 * Expected Categories: GE, G<br/>
	 */
	@Test
	public void testScenario1() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("G");
		application.setJoinElderlyMemberScheme("Y");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("H");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to GE",
				CategorisationTestUtils.containCategory(application, "GE"));
		Assert.assertTrue("The application is assigned to G",
				CategorisationTestUtils.containCategory(application, "G"));
	}

	/**
	 * <strong>Test Scenario (2)</strong> <br/>
	 * Phase Code: 019 (i.e. Rule Version: CATG_019)<br/>
	 * Application: Form Colour: G<br/>
	 * FEP Indicator: N<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Member (2): Relationship Code: W<br/>
	 * Expected Categories: G<br/>
	 */
	@Test
	public void testScenario2() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("G");
		application.setJoinElderlyMemberScheme("N");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("W");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to G",
				CategorisationTestUtils.containCategory(application, "G"));
	}

	/**
	 * <strong>Test Scenario (3)</strong> <br/>
	 * Phase Code: 019 (i.e. Rule Version: CATG_019)<br/>
	 * Application: Form Colour: G<br/>
	 * FEP Indicator: N<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Member (2): Relationship Code: B<br/>
	 * Expected Categories: G<br/>
	 */
	@Test
	public void testScenario3() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("G");
		application.setJoinElderlyMemberScheme("N");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("B");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to G",
				CategorisationTestUtils.containCategory(application, "G"));
	}

	/**
	 * <strong>Test Scenario (4)</strong> <br/>
	 * Phase Code: 019 (i.e. Rule Version: CATG_019)<br/>
	 * Application: Form Colour: G<br/>
	 * FEP Indicator: N<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Expected Categories: G-1P<br/>
	 */
	@Test
	public void testScenario4() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("G");
		application.setJoinElderlyMemberScheme("N");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		memberList.add(member1);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to G-1P",
				CategorisationTestUtils.containCategory(application, "G-1P"));
	}

	/**
	 * <strong>Test Scenario (5)</strong> <br/>
	 * Phase Code: 019 (i.e. Rule Version: CATG_019)<br/>
	 * Application: Form Colour: W<br/>
	 * FEP Indicator: Y<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Member (2): Relationship Code: M<br/>
	 * Expected Categories: WNE, WOE<br/>
	 */
	@Test
	public void testScenario5() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("Y");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("M");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to WNE",
				CategorisationTestUtils.containCategory(application, "WNE"));
		Assert.assertTrue("The application is assigned to WOE",
				CategorisationTestUtils.containCategory(application, "WOE"));
	}

	/**
	 * <strong>Test Scenario (6)</strong> <br/>
	 * Phase Code: 019 (i.e. Rule Version: CATG_019)<br/>
	 * Phase Code: 019 (i.e. Rule Version: CATG_019)<br/>
	 * Application: Form Colour: W<br/>
	 * FEP Indicator: N<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Member (2): Relationship Code: S<br/>
	 * Expected Categories: WOE<br/>
	 */
	@Test
	public void testScenario6() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("N");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("S");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to WOE",
				CategorisationTestUtils.containCategory(application, "WOE"));
	}

	/**
	 * <strong>Test Scenario (7)</strong> <br/>
	 * Phase Code: 019 (i.e. Rule Version: CATG_019)<br/>
	 * Application: Form Colour: W<br/>
	 * FEP Indicator: N<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Member (2): Relationship Code: B<br/>
	 * Expected Categories: W<br/>
	 */
	@Test
	public void testScenario7() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("N");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("B");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to W",
				CategorisationTestUtils.containCategory(application, "W"));
	}

	/**
	 * <strong>Test Scenario (8)</strong> <br/>
	 * Phase Code: 019 (i.e. Rule Version: CATG_019)<br/>
	 * Application: Form Colour: W<br/>
	 * FEP Indicator: N<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Expected Categories: W-1P<br/>
	 */
	@Test
	public void testScenario8() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("019");
		application.setApplicationFormColor("W");
		application.setJoinElderlyMemberScheme("N");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		memberList.add(member1);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to W-1P",
				CategorisationTestUtils.containCategory(application, "W-1P"));
	}

	/**
	 * <strong>Test Scenario (9)</strong> <br/>
	 * Phase Code: WF19 (i.e. Rule Version: CATG_WF19)<br/>
	 * Application: Form Colour: W<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Member (2): Relationship Code: B<br/>
	 * Expected Categories: F<br/>
	 */
	@Test
	public void testScenario9() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("WF19");
		application.setApplicationFormColor("W");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");

		ApplicationMemberVO member2 = new ApplicationMemberVO();
		member2.setRelationshipCode("B");

		memberList.add(member1);
		memberList.add(member2);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to F",
				CategorisationTestUtils.containCategory(application, "F"));
	}

	/**
	 * <strong>Test Scenario (10)</strong> <br/>
	 * Test Scenario:<br/>
	 * Phase Code: WF19 (i.e. Rule Version: CATG_WF19)<br/>
	 * Application: Form Colour: W<br/>
	 * Member (1): Relationship Code: P<br/>
	 * Expected Categories: S<br/>
	 */
	@Test
	public void testScenario10() {
		// Part 1: Construct the test scenario
		MaintainApplicationVO application = new MaintainApplicationVO();
		application.setPhaseCode("WF19");
		application.setApplicationFormColor("W");

		List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

		ApplicationMemberVO member1 = new ApplicationMemberVO();
		member1.setRelationshipCode("P");
		memberList.add(member1);

		application.setApplicationMemberList(memberList);

		// Part 2: Run the categorisation
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);

		// Part 3: Check the result
		Assert.assertTrue("The application is assigned to S",
				CategorisationTestUtils.containCategory(application, "S"));
	}

}
