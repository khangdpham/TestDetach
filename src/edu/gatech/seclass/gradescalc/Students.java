package edu.gatech.seclass.gradescalc;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Students {
	private String studentDB;
	private List<Student> students;
	public Students(String db){
		studentDB=db;
		students = establishStudentDB();
	}
	public List <Student> getStudentsList(){
		return students;
	}
	private List <Student> establishStudentDB() {
		
		List<Student> students = new ArrayList<Student>();
        try {
            FileInputStream file = new FileInputStream(new File(studentDB));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one

            Iterator < Row > rowIterator = sheet.iterator();
            String entry = "";
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                if(row.getRowNum() == 0) continue;
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
                            //System.out.print(cell.getNumericCellValue() + "t");
                            break;
                        case Cell.CELL_TYPE_STRING:
                            entry += cell.getStringCellValue() + "#";
                            //System.out.print(cell.getStringCellValue() + "t");
                            break;
                    }

                }
                //entry = entry.replaceAll("+$","");
                students.add(processData(entry));
               // System.out.println(entry);
                System.out.println("");
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
    private Student processData(String rawStr){
    	//System.out.println(rawStr);
    	String[] tokens = rawStr.split("#");
    	return (new Student(tokens[0],tokens[1]));
    	
    }


}