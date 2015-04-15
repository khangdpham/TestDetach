package edu.gatech.seclass.gradescalc;

import java.util.List;

public class Grades {
	private String gradesDB;
	private List < String > attendance_data;
	private List < String > assignments_data;
	private List < String > projects_data;
	private List <String> assignment_avg;
	private List <String> project_avg;
	
	public Grades(String db) {
		gradesDB = db;
		attendance_data  = DatabaseHelper.getRawDatabase(db, 0);
		assignments_data = DatabaseHelper.getRawDatabase(db, 1);
		projects_data    = DatabaseHelper.getRawDatabase(db, 2);

	}

	public String getDatabaseName() {
		return gradesDB;
	}

	public int getAttendance(String gtid) {
		for (String s: attendance_data) {
			if (s.contains(gtid)) 
				return Integer.parseInt(s.split("#")[1]);
		}
		return 0;
	}
	public String getAssigmentGrade(String gtid) {
		for (String s: assignments_data) {
			if (s.contains(gtid)) 
				return s;
		}
		return "";
	}
	public String getProjectGrade(String gtid) {
		for (String s: projects_data) {
			if (s.contains(gtid)) 
				return s;
		}
		return "";
	}
}