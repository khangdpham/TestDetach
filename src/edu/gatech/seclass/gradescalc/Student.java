package edu.gatech.seclass.gradescalc;

public class Student {
  private int attendance;
  private String gtid;
  private String name;
  private String team;
  private String email;
  private Course course;

  public Student(String n, String i) {
    attendance = 0;
	gtid = i;
    name = n;
  }
  
  public String toString() {
    return "Student:" + name + "\nGTID:" + gtid;
  }
  public Course getCourse() {
    return course;
  }
  public int getAttendance() {
    return attendance;
  }
  public String getGtid() {
    return gtid;
  }
  public String getName() {
    return name;
  }
  public String getTeam() {
    return team;
  }
  public void setTeam(String t) {
    team = t;
  }
  public void setAttendance(int a) {
    attendance = a;
  }
  public String getEmail() {
	return email;
  }
  public void setEmail(String e) {
    email = e;
  }

}