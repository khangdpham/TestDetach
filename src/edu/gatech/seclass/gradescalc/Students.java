package edu.gatech.seclass.gradescalc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Students {
  private String studentDB;

  private HashSet < Student > hashset;

  public Students(String db) {
    studentDB = db;
    hashset = establishStudentDB();
  }
  public String getDatabaseName() {
    return studentDB;
  }
  public HashSet < Student > getStudentsHashSet() {
    return hashset;
  }
  public int getStudentsSize() {
    return hashset.size();
  }
  public Student getStudentByName(String name) {
    Set < Student > s_set = this.hashset;
    for (Student s: s_set) {
      if (s.getName().compareTo(name) == 0) return s;
    }
    return null;
  }
  public Student getStudentById(String Id) {
    Set < Student > s_set = this.hashset;
    for (Student s: s_set) {
      if (s.getGtid().compareTo(Id) == 0) return s;
    }
    return null;
  }
  private HashSet < Student > establishStudentDB() {

    HashSet < Student > students = new HashSet < Student > ();
    List < String > students_data = DatabaseHelper.getRawDatabase(studentDB, 0);
    List < String > team_data = DatabaseHelper.getRawDatabase(studentDB, 1);
    
    for (String s: students_data) {
      
      Student student = this.processStudentData(s);
      student.setTeam(processTeamData(student.getName(), team_data));
      students.add(student);
    }
    return students;
  }
  private Student processStudentData(String rawStr) {
    String[] tokens = rawStr.split("#");
    Student student = new Student(tokens[0], tokens[1]);
    student.setEmail(tokens[2]);
    return student;

  }
  private String processTeamData(String s_name, List < String > team_data) {
    for (String s: team_data) {
      if (s.toLowerCase().contains(s_name.toLowerCase())) {
        return (s.split("#")[0]);
      }
    }
    return "";
  }
  
  public void updateAttendance(Grades grades) {
    for (Student s: hashset)
      s.setAttendance(grades.getAttendance(s.getGtid()));
  }

}