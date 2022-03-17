package hk.gov.housingauthority.nhs.rules.test.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Utility class for masking the HKIC in the data file
 */
public class MaskHKICUtils {

	public static String calculateHKIDCheckDigit(String prefix, String numbers) {

		String checkDigit;

		int sum = 0;
		for (int i = 0; i < prefix.length(); ++i) {
			sum += (prefix.charAt(i) - 64) * (7 + prefix.length() - i);
		}
		for (int i = 0; i < numbers.length(); ++i) {
			sum += (numbers.charAt(i) - 48) * (7 - i);
		}
		int checkDigitValue = 11 - sum % 11;
		if (checkDigitValue == 10) {
			checkDigit = "A";
		} else if (checkDigitValue == 11) {
			checkDigit = "0";
		} else {
			checkDigit = Integer.toString(checkDigitValue);
		}
		return checkDigit;
	}

	public static String calculateFullHKID(String prefix, String numbers) {
		return prefix + numbers + calculateHKIDCheckDigit(prefix, numbers);
	}

	public static String incrementPrefix(String prefix) {

		String nextPrefix = "";
		int position = prefix.length() - 1;

		boolean incrementNextPosition = true;
		while (position > -1) {
			char c = prefix.charAt(position);
			if (incrementNextPosition) {
				if (c == 'Z') {
					nextPrefix = 'A' + nextPrefix;
				} else {
					nextPrefix = Character.toString((char) (c + 1)) + nextPrefix;
					incrementNextPosition = false;
				}
			} else {
				nextPrefix = c + nextPrefix;
			}
			--position;
		}

		if (incrementNextPosition) {
			nextPrefix = 'A' + nextPrefix;
		}

		return nextPrefix;
	}

	final static String HKIC_REGEX_PATTERN_1 = "(IC[\\W])?([A-Z][0-9]{6}[A0-9])";
	final static String HKIC_REGEX_PATTERN_2 = "(IC[\\W])?([A-Z][0-9]{6}\\([A0-9]\\))";

	public static void main(String[] args) throws IOException, InvalidFormatException {
		String prefix = args[0];
		int numberValue = Integer.parseInt(args[1]);
		List<File> fileList = new ArrayList<File>();
		for (int i = 2; i < args.length; ++i) {
			fileList.add(new File(args[i]));
		}

		// Step 1: Read all HKIC to Map
		Map<String, String> hkicMap = new LinkedHashMap<String, String>();

		for (File file : fileList) {
			System.out.println("file = " + file);

			InputStream inputStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			for (Row row : sheet) {
				for (Cell cell : row) {
					String value = dataFormatter.formatCellValue(cell);
					if (value.matches(HKIC_REGEX_PATTERN_1)) {
						System.out.println("1: " + value);
						String originalHkic = value.replaceAll(HKIC_REGEX_PATTERN_1, "$2");
						hkicMap.put(originalHkic, "");
					} else if (value.matches(HKIC_REGEX_PATTERN_2)) {
						System.out.println("2: " + value);
						String originalHkic = value.replaceAll(HKIC_REGEX_PATTERN_2, "$2").replace("(", "").replace(")",
								"");
						hkicMap.put(originalHkic, "");
					}
				}
			}
			workbook.close();
			inputStream.close();
		}

		// Step 2: Determine the dummy IC values
		System.out.println("result list");
		for (Entry<String, String> entry : hkicMap.entrySet()) {

			String numbers = String.format("%06d", numberValue);
			String fullHkid = calculateFullHKID(prefix, numbers);
			entry.setValue(fullHkid);

			System.out.println(entry.getKey() + " -> " + entry.getValue());

			++numberValue;
		}

		// Step 3: Mark the HKIC
		for (File file : fileList) {
			System.out.println("file = " + file);

			InputStream inputStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = workbook.getSheetAt(0);

			DataFormatter dataFormatter = new DataFormatter();
			for (Row row : sheet) {
				for (Cell cell : row) {
					String value = dataFormatter.formatCellValue(cell);
					if (value.matches(HKIC_REGEX_PATTERN_1)) {
						String originalHkic = value.replaceAll(HKIC_REGEX_PATTERN_1, "$2");
						String newHkic = hkicMap.get(originalHkic);
						String newValue = value.replace(originalHkic, newHkic);
						System.out.println("1: " + value + " => " + newValue);
						cell.setCellValue(newValue);
					} else if (value.matches(HKIC_REGEX_PATTERN_2)) {
						String originalHkic = value.replaceAll(HKIC_REGEX_PATTERN_2, "$2").replace("(", "").replace(")",
								"");
						String newHkic = hkicMap.get(originalHkic);
						originalHkic = originalHkic.substring(0, 7) + "(" + originalHkic.substring(7, 8) + ")";
						newHkic = newHkic.substring(0, 7) + "(" + newHkic.substring(7, 8) + ")";
						String newValue = value.replace(originalHkic, newHkic);
						System.out.println("2: " + value + " => " + newValue);
						cell.setCellValue(newValue);
					}
				}
			}
			inputStream.close();

			OutputStream outputStream = new FileOutputStream(file);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		}

	}

}
