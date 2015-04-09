package edu.gatech.seclass.gradescalc;

import java.util.List;

public class GradesCalc {

	public static void main(String[] args) {
	    String STUDENTS_DB = "DB/GradesDatabase6300-students.xlsx";
	    String GRADES_DB = "DB/GradesDatabase6300-grades.xlsx";

		Students roster = new Students(STUDENTS_DB);
		for(Student s: roster.getStudentsList()){
			System.out.print("Name: "+s.getName());
			System.out.println("GTID: "+s.getGtid());
			
			
		}
		
	}

}
