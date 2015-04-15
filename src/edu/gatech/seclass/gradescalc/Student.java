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
        team = "";
        course = c;
        if(c != null){
        /*
        assignment_grade = c.grades.getAssigmentGrade(i);
        projects_grade   = c.grades.getProjectGrade(i);
        System.out.println("DEBUG: "+assignment_grade );
        System.out.println("DEBUG: "+projects_grade );
        */
        }

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
