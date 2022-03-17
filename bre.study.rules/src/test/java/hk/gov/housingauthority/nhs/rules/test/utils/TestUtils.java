package hk.gov.housingauthority.nhs.rules.test.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

import hk.gov.housingauthority.nhs.recommendation.Recommendation;

/**
 * Utilities function shared by all tests in this BRE Revamp POC project
 * 
 * @author BRE Revamp POC Team
 *
 */
public class TestUtils {
	/**
	 * {@link DateFormat} object for yyyyMMdd
	 */
	protected static final DateFormat YYYYMMDD_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	/**
	 * @param dateString Date string with format yyyyMMdd
	 * 
	 * @return date converted from the dateString
	 */
	public static Date convertDateFormYYYYMMDDString(String dateString) {
		Date date = null;
		try {
			date = YYYYMMDD_DATE_FORMAT.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * Function for verifying the recommendation against the expected result
	 * 
	 * @param recommendationDescription The description of the recommendation object
	 *                                  (to be displayed in the assert message)
	 * @param recommendation            The recommendation object for verifying
	 * @param message                   The expected message of the recommendation
	 * @param result                    The expected result of the recommendation
	 */
	public static void verifyRecommendation(String recommendationDescription, Recommendation recommendation,
			String ruleId, String message, String result) {
		if (StringUtils.isNotBlank(ruleId)) {
			Assert.assertTrue(String.format("The rule ID for recommendation of [%1$s] is '%2$s'",
					recommendationDescription, ruleId), ruleId.equals(recommendation.getRuleId()));
		}
		if (StringUtils.isNoneBlank(message)) {
			Assert.assertTrue(String.format("The message for recommendation of [%1$s] is '%2$s'",
					recommendationDescription, message), message.equals(recommendation.getMessage()));
		}
		Assert.assertTrue(
				String.format("The result for recommendation of [%1$s] is '%2$s'", recommendationDescription, result),
				result.equals(recommendation.getResult()));
	}
}
