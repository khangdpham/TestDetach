package edu.gatech.seclass.gradescalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DatabaseHelper {

	public static List < String > getRawDatabase(String db_name, int sheetNum) {
		List < String > raw_data = new ArrayList < String > ();
		try {
			FileInputStream file = new FileInputStream(new File(db_name));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(sheetNum);

			//Iterate through each rows one by one

			Iterator < Row > rowIterator = sheet.iterator();
			String entry = "";
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Skip first row contains column names
				if (row.getRowNum() == 0) continue;

				//For each row, iterate through all the columns
				Iterator < Cell > cellIterator = row.cellIterator();
				entry = "";
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					//Check the cell type and format accordingly	                    

					switch (cell.getCellType()) {
						case Cell.CELL_TYPE_NUMERIC:
							int val = (int) cell.getNumericCellValue();
							entry += val + "#";
							break;
						case Cell.CELL_TYPE_STRING:
							entry += cell.getStringCellValue() + "#";
							break;
					}

				}
				entry = entry.trim();
				if (entry.length() > 0) raw_data.add(entry);

			}
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return raw_data;
	}
	public static int getNumberOfFields(String db_name, int sheetNum) {
		int noOfColumns = 0;
		try {
			FileInputStream file = new FileInputStream(new File(db_name));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(sheetNum);
			noOfColumns = sheet.getRow(0).getLastCellNum();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noOfColumns;
	}
	public static void updateCell(String db_name, int sheetNum,int column ,String data){
		try {
			FileInputStream inputXls = new FileInputStream(new File(db_name));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(inputXls);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(sheetNum);

			//Iterate through each rows one by one

			Iterator < Row > rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			if(row.getRowNum() == 0){
				Iterator < Cell > cellIterator = row.cellIterator();
				
				while (cellIterator.hasNext()){ 
					cellIterator.next();
					
				}
					
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void addNewField(String db_name, int sheetNum,String data){
		int count = 0;
		try {
			FileInputStream inputXls = new FileInputStream(new File(db_name));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(inputXls);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(sheetNum);

			//Iterate through each rows one by one

			Iterator < Row > rowIterator = sheet.iterator();

			Row row = rowIterator.next();
			if(row.getRowNum() == 0){
				Iterator < Cell > cellIterator = row.cellIterator();
				
				while (cellIterator.hasNext()){ 
					cellIterator.next();
					count++;
				}
					
			}
				if( sheet.getRow(0).getCell(count) == null ){
					XSSFCell cell = sheet.getRow(0).createCell(count);
					cell.setCellStyle(sheet.getRow(0).getCell(count-1).getCellStyle());
					cell.setCellValue(data);
				FileOutputStream outputXls = new FileOutputStream(new File(db_name));
				workbook.write(outputXls);
				outputXls.close();
				}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}