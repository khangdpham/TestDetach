package edu.gatech.seclass.gradescalc;

import java.util.HashMap;
import java.util.HashSet;

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
		String grade_str = grades.getAssigmentGrade(student.getGtid());
		System.out.println("Student " + student.getGtid());
		System.out.println(grade_str);
		
		// TODO Auto-generated method stub
		return 0;
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