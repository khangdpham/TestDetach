package edu.gatech.seclass.gradescalc;

import java.util.List;

public class Grades {
	String gradesDB;
	List < String > grade_data;
	public Grades(String db) {
		gradesDB = db;
		grade_data = DatabaseHelper.getRawDatabase(db, 0);

	}

	public String getDatabaseName() {
		return gradesDB;
	}

	public int getAttendance(String gtid) {
		for (String s: grade_data) {
			if (s.contains(gtid)) 
				return Integer.parseInt(s.split("#")[1]);
		}
		return 0;
	}
}