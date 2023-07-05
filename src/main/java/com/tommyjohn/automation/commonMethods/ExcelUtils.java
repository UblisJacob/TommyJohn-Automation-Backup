package com.tommyjohn.automation.commonMethods;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	
	public static String readExcelValue(String filePath, String sheetName, String headerName) throws Exception{
		String headerValue = null;
		//Create an object of File class to open xlsx file
		File file =  new File(filePath);
		//Create an object of FileInputStream class to read excel file
		FileInputStream inputStream = new FileInputStream(file);
		Workbook workbook = null;
		//Find the file extension by splitting file name in substring  and getting only extension name
		String fileExtensionName = filePath.substring(filePath.indexOf("."));
		//Check condition if the file is xlsx file
		if(fileExtensionName.equals(".xlsx")){
			//If it is xlsx file then create object of XSSFWorkbook class
			workbook = new XSSFWorkbook(inputStream);
		}
		//Check condition if the file is xls file
		else if(fileExtensionName.equals(".xls")){
			//If it is xls file then create object of HSSFWorkbook class
			workbook = new HSSFWorkbook(inputStream);
		}
		//Read sheet inside the workbook by its name
		Sheet sheet = workbook.getSheet(sheetName);
		//Create a loop over all the rows of excel file to read it
		Row row = sheet.getRow(0);
		//Create a loop to print cell values in a row
		for (int i = 0; i < row.getLastCellNum(); i++) {
			//To format the Cell value to string
			DataFormatter formatter = new DataFormatter();
			String ExcelHeaderName = formatter.formatCellValue(row.getCell(i));
			//To compare the header name	    	
			if(headerName.equalsIgnoreCase(ExcelHeaderName))
			{
				headerValue = formatter.formatCellValue(sheet.getRow(1).getCell(i));
				System.out.println(formatter.formatCellValue(sheet.getRow(1).getCell(i)));
				break;
			}
		}
		return headerValue;
	}

}
