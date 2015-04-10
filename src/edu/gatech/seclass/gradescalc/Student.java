package edu.gatech.seclass.gradescalc;

public class Student {
    private int attendance;
    private String gtid;
    private String name;
    private String team;

    public Student(String n, String i) {
        attendance = 0;
        gtid = i;
        name = n;
        team = "";

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
