package edu.gatech.seclass.gradescalc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Student {
    private int attendance;
    private String id;
    private String name;
    private String team;

    public Student(String n, String i) {
        //attendance = a;
        id = i;
        name = n;
        //team = t;

    }
    public int getAttendance() {
        return 0;
    }
    public String getGtid() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getTeam() {
        return "";
    }
 

}
