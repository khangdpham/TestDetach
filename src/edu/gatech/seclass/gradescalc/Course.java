package edu.gatech.seclass.gradescalc;

import java.util.HashSet;

public class Course {
	Students students;
	Grades grades;
	public Course(Students s, Grades g){
		students = s;
		grades = g;
	
	}
	
	public int getNumStudents(){
		return students.getStudentsList().size();
		//return 16;
	}
	
	public int getNumAssignments(){
		int numAssgmnt = 0 ;
		
		return numAssgmnt;
	}
	public int getNumProjects(){
		int numPrj = 0 ;
		
		return numPrj;
	}
	
	public HashSet<Student> getStudents(){
		
		return null;
	}
	public Student getStudentByName(String sName){
		
		
		return null;
	}

	public Student getStudentByID(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}