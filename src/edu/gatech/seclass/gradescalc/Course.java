package edu.gatech.seclass.gradescalc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

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
    DatabaseHelper.addNewField(grades.getDatabaseName(), 1, assigmentName);
    numOfAssignments = findNumberOfAssignments();

  }
  public void updateGrades(Grades g) {
    grades.setGrade(g);
  }
  public void addGradesForAssignment(String assignmentName,
  HashMap < Student, Integer > individualGrades) {

    Iterator it = individualGrades.entrySet().iterator();

    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      Student student = (Student) pair.getKey();
      int column = DatabaseHelper.getIndexOfColumn(grades.getDatabaseName(), 1, assignmentName);
      DatabaseHelper.updateCell(grades.getDatabaseName(), 1, column, student.getGtid(), (int) pair.getValue());

    }

  }
  public int getAverageAssignmentsGrade(Student student) {
    double total = 0;
    String[] tokens = grades.getAssigmentGrade(student.getGtid()).split("#");

    for (int i = 1; i < tokens.length; i++) {
      total += Integer.parseInt(tokens[i]);
    }
    return (int) Math.round(total / numOfAssignments);
  }
  public int getAverageProjectsGrade(Student student) {
    double total = grades.getProjectGrade(student.getGtid(), student.getTeam());
    return (int) Math.round(total / numOfProjects);
  }

  public void addIndividualContributions(String projectName1,
  HashMap < Student, Integer > contributions1) {

    Iterator it = contributions1.entrySet().iterator();

    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry) it.next();
      Student student = (Student) pair.getKey();
      int column = DatabaseHelper.getIndexOfColumn(grades.getDatabaseName(), 2, projectName1);
      DatabaseHelper.updateCell(grades.getDatabaseName(), 2, column, student.getGtid(), (int) pair.getValue());

    }

  }

}