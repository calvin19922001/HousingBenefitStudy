package hk.gov.housingauthority.nhs.categorisation.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import hk.gov.housingauthority.nhs.categorisation.library.CategorisationRuleLibrary;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;
import hk.gov.housingauthority.nhs.rules.test.utils.CategorisationTestUtils;

/**
 * Test for checking the categorisation performance on batch of application
 * 
 * @author BRE Revamp POC Team
 *
 */
public class CategorisationLoadTest {
	/**
	 * String pattern for the test input
	 */
	private static final String TEST_INPUT_KEY_PATTERN = "Test_%d_Trial_%2d";
	/**
	 * Upper limit (in second) for processing each application
	 */
	private static final double EXPECTED_NUM_OF_SECOND_PER_CASE = 0.01;

	/**
	 * List of batch sizes for the test
	 */
	private static final int[] BATCH_SIZE = { 100, 1000, 10000, 100000 };
	/**
	 * No. of trial for each batch size
	 */
	private static final int NUMBER_OF_TRIAL_PER_BATCH = 3;

	/**
	 * Generator of dummy application cases
	 * 
	 * @author BRE Revamp POC Team
	 *
	 */
	protected static interface ApplicationGenerator {
		/**
		 * @return a dummy application object
		 */
		MaintainApplicationVO generateApplication();

		/**
		 * @return the description of the application type
		 */
		String getApplicationTypeDescription();

		/**
		 * @return ration repective to the batch size for the application type
		 */
		double getRatio();
	}

	/**
	 * Spring application context
	 */
	protected static ApplicationContext applicationContext;

	/**
	 * Categorisation rule library ({@link CategorisationRuleLibrary}) retrieved
	 * from spring application context
	 */
	protected static CategorisationRuleLibrary categorisationRuleLibrary;

	/**
	 * List of application generator ({@link ApplicationGenerator}}) for generating
	 * the dummy applications according to the batch size
	 */
	protected static List<ApplicationGenerator> applicationGeneratorList;

	/**
	 * Map contains test input description as key and list of application as value
	 */
	protected static Map<String, List<MaintainApplicationVO>> testInputMap;

	/**
	 * Initialise the class before all test scenario
	 * 
	 * 1) Initialise the {@link #applicationContext} and
	 * {@link #categorisationRuleLibrary}
	 * 
	 * 2) Initilise all test input before running the test
	 */
	@BeforeClass
	static public void beforeAllTests() {
		applicationContext = new ClassPathXmlApplicationContext("classpath*:spring/applicationContext-*.xml");
		categorisationRuleLibrary = (CategorisationRuleLibrary) applicationContext.getBean("categorisationRuleLibrary");

		initialiseApplicationGeneratorList();
		initialiseTestInput();

		// It is observed the first application categorisation run especially slow
		// This statement is added to avoid any impact by the first application to the
		// run time of the test
		CategorisationTestUtils.runCategorisation(categorisationRuleLibrary,
				applicationGeneratorList.get(0).generateApplication());
	}

	/**
	 * @param numberOfApplications no. of application (i.e. batch size)
	 * @return the list of dummy application initalise according to the batch size
	 */
	protected static List<MaintainApplicationVO> initaliseApplicationList(int numberOfApplications) {
		List<MaintainApplicationVO> applicationList = new ArrayList<MaintainApplicationVO>();

		for (ApplicationGenerator applicationGenerator : applicationGeneratorList) {
			int numOfApplicationByType = (int) (numberOfApplications * applicationGenerator.getRatio());
			for (int i = 0; i < numOfApplicationByType; ++i) {
				applicationList.add(applicationGenerator.generateApplication());
			}
		}
		Collections.shuffle(applicationList);
		return applicationList;
	}

	/**
	 * Initialise the list of application generator with reference to the
	 * application composition in HOS 2019 and WSM 2019 (estimated according to
	 * priority list):<br/>
	 * HOS 2019:<br/>
	 * .. Total number of application: 306,025<br/>
	 * .. .. Total number of Green Form application: 46,092<br/>
	 * .. .. .. Total number of GE, G: 19,692 (4.48%)<br/>
	 * .. .. .. Total number of G: 36,413 (3.80%)<br/>
	 * .. .. .. Total number of G-1P: 9,557 (2.17%)<br/>
	 * .. .. .. Total number of Clearance case: 102 (to be ignored in this
	 * test)<br/>
	 * .. .. .. Total number of EFAS case: 20 (to be ignored in this test)<br/>
	 * .. .. Total number of White Form application: 260,209<br/>
	 * .. .. .. Total number of WNE, WOE: 9,902 (2.25%)<br/>
	 * .. .. .. Total number of WOE or W: 88,022 (20.01%)<br/>
	 * .. .. .. Total number of W-1P: 162,029 (36.84%)<br/>
	 * WSM 2019:<br/>
	 * .. Total number of application: 133,734<br/>
	 * .. .. Total number of Family: 54,301 (12.35%)<br/>
	 * .. .. Total number of Singleton: 79,433 (18.06%)<br/>
	 * Total number of application for both exercise: 439,759<br/>
	 */
	protected static void initialiseApplicationGeneratorList() {
		applicationGeneratorList = new ArrayList<ApplicationGenerator>();
		applicationGeneratorList.add(new ApplicationGenerator() {
			@Override
			public MaintainApplicationVO generateApplication() {
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
				return application;
			}

			@Override
			public String getApplicationTypeDescription() {
				return "HOS Application - GE, G";
			}

			@Override
			public double getRatio() {
				return 0.05;
			}
		});

		applicationGeneratorList.add(new ApplicationGenerator() {
			@Override
			public MaintainApplicationVO generateApplication() {
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
				return application;
			}

			@Override
			public String getApplicationTypeDescription() {
				return "HOS Application - G";
			}

			@Override
			public double getRatio() {
				return 0.04;
			}
		});

		applicationGeneratorList.add(new ApplicationGenerator() {
			@Override
			public MaintainApplicationVO generateApplication() {
				MaintainApplicationVO application = new MaintainApplicationVO();
				application.setPhaseCode("019");
				application.setApplicationFormColor("G");
				application.setJoinElderlyMemberScheme("N");

				List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

				ApplicationMemberVO member1 = new ApplicationMemberVO();
				member1.setRelationshipCode("P");
				memberList.add(member1);

				application.setApplicationMemberList(memberList);
				return application;
			}

			@Override
			public String getApplicationTypeDescription() {
				return "HOS Application - G-1P";
			}

			@Override
			public double getRatio() {
				return 0.02;
			}
		});

		applicationGeneratorList.add(new ApplicationGenerator() {
			@Override
			public MaintainApplicationVO generateApplication() {
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
				return application;
			}

			@Override
			public String getApplicationTypeDescription() {
				return "HOS Application - WNE, W";
			}

			@Override
			public double getRatio() {
				return 0.02;
			}
		});

		applicationGeneratorList.add(new ApplicationGenerator() {
			@Override
			public MaintainApplicationVO generateApplication() {
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
				return application;
			}

			@Override
			public String getApplicationTypeDescription() {
				return "HOS Application - WNE or W";
			}

			@Override
			public double getRatio() {
				return 0.20;
			}
		});

		applicationGeneratorList.add(new ApplicationGenerator() {
			@Override
			public MaintainApplicationVO generateApplication() {
				MaintainApplicationVO application = new MaintainApplicationVO();
				application.setPhaseCode("019");
				application.setApplicationFormColor("W");
				application.setJoinElderlyMemberScheme("N");

				List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

				ApplicationMemberVO member1 = new ApplicationMemberVO();
				member1.setRelationshipCode("P");
				memberList.add(member1);

				application.setApplicationMemberList(memberList);
				return application;
			}

			@Override
			public String getApplicationTypeDescription() {
				return "HOS Application - W-1P";
			}

			@Override
			public double getRatio() {
				return 0.37;
			}
		});

		applicationGeneratorList.add(new ApplicationGenerator() {
			@Override
			public MaintainApplicationVO generateApplication() {
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
				return application;
			}

			@Override
			public String getApplicationTypeDescription() {
				return "WSM Application - Family";
			}

			@Override
			public double getRatio() {
				return 0.12;
			}
		});

		applicationGeneratorList.add(new ApplicationGenerator() {
			@Override
			public MaintainApplicationVO generateApplication() {
				MaintainApplicationVO application = new MaintainApplicationVO();
				application.setPhaseCode("WF19");
				application.setApplicationFormColor("W");

				List<ApplicationMemberVO> memberList = new ArrayList<ApplicationMemberVO>();

				ApplicationMemberVO member1 = new ApplicationMemberVO();
				member1.setRelationshipCode("P");
				memberList.add(member1);

				application.setApplicationMemberList(memberList);
				return application;
			}

			@Override
			public String getApplicationTypeDescription() {
				return "WSM Application - Singleton";
			}

			@Override
			public double getRatio() {
				return 0.18;
			}
		});
	}

	/**
	 * Initialise the test input (i.e. list of application).
	 * 
	 * The test input are stored in the {@link #testInputMap}
	 */
	protected static void initialiseTestInput() {
		System.out.println("Initialise application list : Start");

		testInputMap = new HashMap<String, List<MaintainApplicationVO>>();

		for (int numberOfApplication : BATCH_SIZE) {
			for (int trialNumber = 1; trialNumber <= NUMBER_OF_TRIAL_PER_BATCH; ++trialNumber) {
				System.out.println(
						String.format("... For batch size = %d, trial number = %d", numberOfApplication, trialNumber));
				testInputMap.put(String.format(TEST_INPUT_KEY_PATTERN, numberOfApplication, trialNumber),
						initaliseApplicationList(numberOfApplication));
			}
		}
		System.out.println("Initialise application list : End");
	}

	/**
	 * Running all test input in {@link #testInputMap}
	 */
	@Test
	public void testRunningCategoirsationByBatch() {

		for (int i = 0; i < BATCH_SIZE.length; ++i) {
			System.out.println("=================================");
			System.out.println(String.format("Batch <%d>", i + 1));
			int numberOfApplication = BATCH_SIZE[i];
			long averageRunTime = getAverageRunTimeForCategoirsation(numberOfApplication, NUMBER_OF_TRIAL_PER_BATCH);
			assertTrue(
					String.format("Average runtime of %d applications is below %.2f seconds", numberOfApplication,
							numberOfApplication * EXPECTED_NUM_OF_SECOND_PER_CASE),
					averageRunTime <= numberOfApplication * EXPECTED_NUM_OF_SECOND_PER_CASE * 1000 * 1000000);
		}
	}

	/**
	 * @param numberOfApplication no. of application (i.e. batch size)
	 * @param numberOfTrial       no. of trials
	 * @return get the average run time for the batch size
	 */
	protected long getAverageRunTimeForCategoirsation(int numberOfApplication, int numberOfTrial) {
		System.out.println(String.format("Start running test cases for %d applications", numberOfApplication));
		long[] runTimes = new long[numberOfTrial];

		for (int trialNumber = 1; trialNumber <= numberOfTrial; ++trialNumber) {
			System.out.println(String.format("Trial number %d: ", trialNumber));
			List<MaintainApplicationVO> applicationList = testInputMap
					.get(String.format(TEST_INPUT_KEY_PATTERN, numberOfApplication, trialNumber));
			assertTrue(String.format("Size of application list is %d", numberOfApplication),
					applicationList.size() == numberOfApplication);

			long startTime = System.nanoTime();
			for (MaintainApplicationVO application : applicationList) {
				CategorisationTestUtils.runCategorisation(categorisationRuleLibrary, application);
			}
			long endTime = System.nanoTime();

			assertTrue(String.format("Category for all application is not empty"),
					!IterableUtils.matchesAny(applicationList, new Predicate<MaintainApplicationVO>() {

						@Override
						public boolean evaluate(MaintainApplicationVO application) {
							return application.getCategoryPriority() == null
									|| application.getCategoryPriority().size() == 0;
						}
					}));

			long runTimeInNanoSeconds = endTime - startTime;
			runTimes[trialNumber - 1] = runTimeInNanoSeconds;
			System.out.println(String.format("... Time elapsed (in ms): %.2f", runTimeInNanoSeconds * 1.0 / 1000000));
		}

		long averageRunTime = 0;
		for (long runTimeInNanoSeconds : runTimes) {
			averageRunTime += runTimeInNanoSeconds;
		}
		averageRunTime = averageRunTime / numberOfTrial;
		System.out.println(String.format("Average time elapsed (in ms): %.2f", averageRunTime * 1.0 / 1000000));

		return averageRunTime;
	}

}
