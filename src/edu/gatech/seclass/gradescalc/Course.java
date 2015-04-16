package edu.gatech.seclass.gradescalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Course {
	Students students;
	Grades grades;
	int numOfProjects;
	int numOfAssignments;
	public Course(Students s, Grades g) {
		
		students = s;
		grades = g;
		numOfProjects = this.findNumberOfProjects();
		numOfAssignments = this.findNumberOfAssignments();
		this.setAttendance();
		
	}
	private void setAttendance() {
		students.updateAttendance(grades);
	}
	public int getNumStudents() {
		return students.getStudentsSize();
	}
	public int getNumAssignments() {
		return numOfAssignments;
	}
	public int getNumProjects() {
		return numOfProjects;
	}
	public HashSet < Student > getStudents() {
		return students.getStudentsHashSet();
	}
	public Student getStudentByName(String sName) {
		return students.getStudentByName(sName);
	}
	public Student getStudentByID(String string) {
		return students.getStudentById(string);
	}
	private int findNumberOfProjects() {
		int num = DatabaseHelper.getNumberOfFields(grades.getDatabaseName(), 3);
		// Return number of projects in the course
		return num - 1;
	}
	private int findNumberOfAssignments() {
		int num = DatabaseHelper.getNumberOfFields(grades.getDatabaseName(), 1);

		// Return number of assignments in the course
		return num - 1;
	}
	public void addAssignment(String assigmentName) {
		
		// TODO Auto-generated method stub
		
	}
	public void updateGrades(Grades g) {
		grades = g;
		
	}
	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> grades2) {
		// TODO Auto-generated method stub
		
	}
	public int getAverageAssignmentsGrade(Student student) {
		int average = 0 ;
		String[]tokens = grades.getAssigmentGrade(student.getGtid()).split("#");
		for (int i = 1; i<tokens.length ;i++)
			average += Integer.parseInt(tokens[i]);
		return average/this.numOfAssignments;
	}
	public int getAverageProjectsGrade(Student student) {

		// TODO Auto-generated method stub
		return 0;
	}
	public void addIndividualContributions(String projectName1,
			HashMap<Student, Integer> contributions1) {
		// TODO Auto-generated method stub
		
	}

}