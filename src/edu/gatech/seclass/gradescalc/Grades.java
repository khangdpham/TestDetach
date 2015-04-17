package edu.gatech.seclass.gradescalc;

import java.util.List;

public class Grades {
  private String gradesDB;
  private List < String > class_attendance;
  private List < String > assignments_grade;
  private List < String > team_grade;
  private List < String > projects_contrib;


  public Grades(String db) {
    gradesDB = db;
    //System.out.println("How many times");
    class_attendance = DatabaseHelper.getRawDatabase(db, 0);
    assignments_grade = DatabaseHelper.getRawDatabase(db, 1);
    projects_contrib = DatabaseHelper.getRawDatabase(db, 2);
    team_grade = DatabaseHelper.getRawDatabase(db, 3);
  }
  public void setGrade(Grades g) {
    gradesDB = g.gradesDB;
    class_attendance = g.class_attendance;
    assignments_grade = g.assignments_grade;
    team_grade = g.team_grade;
    projects_contrib = g.projects_contrib;
  }
  public String getDatabaseName() {
    return gradesDB;
  }

  public int getAttendance(String gtid) {
    for (String s: class_attendance) {
      if (s.contains(gtid)) return Integer.parseInt(s.split("#")[1]);
    }
    return 0;
  }
  public String getAssigmentGrade(String gtid) {
    for (String s: assignments_grade) {
      if (s.contains(gtid)) return s;
    }
    return "";
  }

  public double getProjectGrade(String gtid, String team) {
    String contrib = "";
    String grade = "";
    for (String s: projects_contrib) {
      if (s.contains(gtid)) contrib = s;
    }
    for (String g: team_grade) {
      if (g.contains(team)) grade = g;
    }
    String[] contrib_arr = contrib.split("#");
    String[] grade_arr = grade.split("#");
    double average = 0;
    for (int i = 1; i < contrib_arr.length && i < grade_arr.length; i++) {
      average += (Double.parseDouble(contrib_arr[i]) / 100 * Integer.parseInt(grade_arr[i]));

    }
    return average;
  }

}