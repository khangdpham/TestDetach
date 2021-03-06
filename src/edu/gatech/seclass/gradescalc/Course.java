package edu.gatech.seclass.gradescalc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Course {
  Students students;
  Grades grades;
  int numOfProjects;
  int numOfAssignments;
  String gradeFormula;
  public Course(Students s, Grades g) {

    students = s;
    grades = g;
    numOfProjects = this.findNumberOfProjects();
    numOfAssignments = this.findNumberOfAssignments();
    gradeFormula= "ATT * 0.2 + AAG * 0.4 + APG * 0.4";
    this.setAttendance();

  }
  private void setAttendance() {
    students.updateAttendance(grades);
  }
  public String getTeam(Student student) {
   return students.getStudentById(student.getGtid()).getTeam();
  }
  public int getAttendance(Student student) {
    return students.getStudentById(student.getGtid()).getAttendance();
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
    double total = grades.getProjectGrade(student.getGtid(), this.getTeam(student));
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
  
  public void addStudent(Student student1, Course course) {
    // TODO Auto-generated method stub
  }
  public void addProject(String string) {
  // TODO Auto-generated method stub
  
  }
  public void addGradesForProject(String project1, Grades grades2) {
  // TODO Auto-generated method stub
  
  }
public String getFormula() {
  return gradeFormula;
}
public void setFormula(String text) {
  gradeFormula = text;
  
}
public String getEmail(Student student) {
  return student.getEmail();
}
public String getOverallGrade(Student student) {
  int overallGrade = 0 ;
  String str = gradeFormula;
  str = str.replaceAll("ATT",String.valueOf(getAttendance(student)));
  str = str.replaceAll("AAG",String.valueOf(getAverageAssignmentsGrade(student)));
  str = str.replaceAll("APG",String.valueOf(getAverageProjectsGrade(student)));
  ScriptEngineManager manager = new ScriptEngineManager();
  ScriptEngine engine = manager.getEngineByName("js"); 
  try{
    Object result = engine.eval(str);
    overallGrade =(int) Math.round(Double.parseDouble(result.toString()));
  }
  catch (ScriptException e){
    throw new GradeFormulaException(e.getMessage());
    
  }
  return String.valueOf(overallGrade);
}

}