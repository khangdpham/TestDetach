package edu.gatech.seclass.gradescalc;

public class Student {
    private int attendance;
    private String gtid;
    private String name;
    private String team;
    private Course course;
    private String assignment_grade;
    private String projects_grade;

    public Student(String n, String i, Course c) {
        attendance = 0;
        gtid = i;
        name = n;
        course = c;
        if(course!= null)
        	team = c.getStudentByID(i).getTeam();
        

    }
    public String toString(){
    	return "Student:"+name+"\nGTID:"+gtid;
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
    public void setTeam(String t){
    	team = t;
    }
    public void setAttendance(int a){
    	attendance = a;
    }


}
