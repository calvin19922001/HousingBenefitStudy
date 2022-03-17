package hk.gov.housingauthority.nhs.rules.util;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.lang3.time.DateUtils;

import hk.gov.housingauthority.nhs.common.vo.maintainApplication.ApplicationMemberVO;
import hk.gov.housingauthority.nhs.common.vo.maintainApplication.MaintainApplicationVO;

/**
 * Utility functions for the various rules
 * 
 * @author BRE Revamp POC Team
 *
 */
public class RuleUtils {

	/**
	 * @param application
	 * @return the family type determine by the logic as descripted below:
	 * 
	 *         <table border="1">
	 *         <tr>
	 *         <th>#</th>
	 *         <th>No of Members</th>
	 *         <th>Pregnant Indicator <br/>
	 *         of the Principal Member</th>
	 *         <th>Any member is <br/>
	 *         of the relationship code <br/>
	 *         "H", "W", "F", "M", S", "D</th>
	 *         <th>Family Type<br/>
	 *         N = Nuclear Family<br/>
	 *         F = Non-nuclear Family<br/>
	 *         S = Singleton<br/>
	 *         </th>
	 *         </tr>
	 *         <tr>
	 *         <td>1</td>
	 *         <td>1</td>
	 *         <td>Y</td>
	 *         <td></td>
	 *         <td>N</td>
	 *         </tr>
	 *         <tr>
	 *         <td>2</td>
	 *         <td>1</td>
	 *         <td>N or Empty</td>
	 *         <td></td>
	 *         <td>S</td>
	 *         </tr>
	 *         <tr>
	 *         <td>3</td>
	 *         <td>&gt; 1 <br>
	 *         </td>
	 *         <td></td>
	 *         <td>True</td>
	 *         <td>N<br>
	 *         </td>
	 *         </tr>
	 *         <tr>
	 *         <td>4</td>
	 *         <td>&gt; 1<br>
	 *         </td>
	 *         <td></td>
	 *         <td>False</td>
	 *         <td>F</td>
	 *         </tr>
	 *         </table>
	 */
	public static String determineFamilyTypeOfApplication(MaintainApplicationVO application) {

		String familyType = "";
		List<ApplicationMemberVO> memberList = application.getApplicationMemberList();

		// Determine S or not
		if (application.getApplicationMemberList().size() == 1) { // No. of Members is 1
			if (memberList.get(0).getPregnant() != null) {
				if (memberList.get(0).getPregnant().equals("Y")) { // Check Pregnant
					familyType = "N";
				} else {
					familyType = "S";
				}
			} else {
				familyType = "S";
			}
		} else { // Determine N/F
			for (ApplicationMemberVO member : memberList) {
				if (member.getRelationshipCode().equals("H") || member.getRelationshipCode().equals("W")
						|| member.getRelationshipCode().equals("F") || member.getRelationshipCode().equals("M")
						|| member.getRelationshipCode().equals("S") || member.getRelationshipCode().equals("D")) {
					familyType = "N";
					break;
				} else {
					familyType = "F";
				}
			}
		}

		return familyType;
	}

	/**
	 * @param application
	 * 
	 * @return Principal member in the member list
	 */
	public static ApplicationMemberVO getPrincipalMemberOfApplication(MaintainApplicationVO application) {
		return IterableUtils.find(application.getApplicationMemberList(), new Predicate<ApplicationMemberVO>() {
			@Override
			public boolean evaluate(ApplicationMemberVO member) {
				return "P".equals(member.getRelationshipCode());
			}
		});
	}

	/**
	 * @param dateOfBirthString Date string in format yyyyMMdd
	 * 
	 * @return {@link Date} object coverted from the dateOfBirthString
	 */
	public static Date convertDOBStringToDate(String dateOfBirthString) {

		Date dateOfBirth = null;
		final String parsePattern = "yyyyMMdd";

		try {
			if (dateOfBirthString.matches("^([0-9]{4})0000$")) {
				dateOfBirth = DateUtils.parseDate(dateOfBirthString.replaceAll("^([0-9]{4})0000$", "$10101"),
						parsePattern);
			} else if (dateOfBirthString.matches("^([0-9]{6})00$")) {
				dateOfBirth = DateUtils.parseDate(dateOfBirthString.replaceAll("^([0-9]{6})00$", "$101"), parsePattern);
			} else {
				dateOfBirth = DateUtils.parseDate(dateOfBirthString, parsePattern);
			}
		} catch (ParseException e) {
			System.err.println("Cannot parse date of birth: " + dateOfBirthString);
		}

		return dateOfBirth;
	}
}
