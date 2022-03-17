package hk.gov.housingauthority.nhs.rules.test.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

public class DatabaseUtils {
	protected static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static void executeRawSql(Connection connection, Resource resource) throws SQLException {
		ScriptUtils.executeSqlScript(connection, resource);
	}

	public static void loadDataFromExcelFile(Connection connection, String tableName, Resource resource)
			throws SQLException, IOException {
		InputStream inputStream = resource.getInputStream();
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		XSSFSheet sheet = workbook.getSheetAt(0);

		DataFormatter dataFormatter = new DataFormatter();

		List<String> columns = new ArrayList<String>();
		List<String> values = new ArrayList<String>();

		Statement statement = connection.createStatement();

		for (Row row : sheet) {
			if (row.getRowNum() == 0) {
				// For column names
				for (Cell cell : row) {
					String value = dataFormatter.formatCellValue(cell);
					columns.add(value);
				}
			} else {
				// For values
				values.clear();
				for (Cell cell : row) {
					String value;
					if (cell.getCellType().equals(CellType.NUMERIC) && DateUtil.isCellDateFormatted(cell)) {
						value = DATE_FORMAT.format(cell.getDateCellValue());
					} else {
						value = dataFormatter.formatCellValue(cell);
					}
					values.add(value);
				}

				String sqlStatement = "INSERT INTO " + tableName + " \n"//
						+ "(\"" + StringUtils.join(columns.iterator(), "\", \"") + "\") \n" + "VALUES (\""
						+ StringUtils.join(values.iterator(), "\", \"") + "\") ";
				System.out.println("sqlStatement = " + sqlStatement);
				statement.execute(sqlStatement);
			}
		}

		statement.close();
		workbook.close();
	}
}
