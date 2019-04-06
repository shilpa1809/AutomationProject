package iSTOX_Utility;

import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadDataFromExcel {

	// Read data from Excel

	static Workbook book;

	static Sheet sheet;

	public static Object[][] readdata(String SheetName) throws Exception

	{
		FileInputStream file = null;

		file = new FileInputStream(
				System.getProperty("user.dir") + "\\InputDataSheet\\ISTOX_RegistrationDataSheet.xls");

		Workbook book = WorkbookFactory.create(file);

		sheet = book.getSheet(SheetName);

		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int i = 0; i < sheet.getLastRowNum(); i++)

		{

			for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++)

			{

				data[i][j] = sheet.getRow(i + 1).getCell(j).toString();

				System.out.println("valw" + data[i][j]);

			}

		}

		return data;

	}

}
