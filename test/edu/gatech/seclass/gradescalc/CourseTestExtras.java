package edu.gatech.seclass.gradescalc;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

///////////////////////
// NO CHANGES STARTS //
///////////////////////

public class CourseTestExtras {

    Students students = null;
    Grades grades = null;
    Course course = null;
    static final String GRADES_DB = "DB" + File.separator + "GradesDatabase6300-grades.xlsx";
    static final String GRADES_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-grades-goldenversion.xlsx";
    static final String STUDENTS_DB = "DB" + File.separator + "GradesDatabase6300-students.xlsx";
    static final String STUDENTS_DB_GOLDEN = "DB" + File.separator + "GradesDatabase6300-students-goldenversion.xlsx";
    
    @Before
    public void setUp() throws Exception {
        FileSystem fs = FileSystems.getDefault();
        Path gradesdbfilegolden = fs.getPath(GRADES_DB_GOLDEN);
        Path gradesdbfile = fs.getPath(GRADES_DB);
        Files.copy(gradesdbfilegolden, gradesdbfile, REPLACE_EXISTING);
        Path studentsdbfilegolden = fs.getPath(STUDENTS_DB_GOLDEN);
        Path studentsdbfile = fs.getPath(STUDENTS_DB);
        Files.copy(studentsdbfilegolden, studentsdbfile, REPLACE_EXISTING);    	
    	students = new Students(STUDENTS_DB);
        grades = new Grades(GRADES_DB);
        course = new Course(students, grades);
    }

    @After
    public void tearDown() throws Exception {
        students = null;
        grades = null;
        course = null;
    }

    @Test
    public void testAddStudent() {
    	Student student1 = new Student("Kevin Smith", "901234599");
    	course.addStudent(student1, course);
    	//student1.setTeam("Team 2");
        course.addStudent(student1,course);
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(96, course.getAverageProjectsGrade(student1));
    }
    @Test
    public void testAddProject() {
        course.addProject("Project: Quick Pick");
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(5, course.getNumProjects());
        course.addAssignment("Project: Sprint");
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(6, course.getNumAssignments());
    }
    @Test
    public void testAddGradesForProject() {
        Student student1 = new Student("Josepha Jube", "901234502");
        Student student2 = new Student("Grier Nehling", "901234503");
    	String project1= "Project: Quick Pick";
        course.addProject(project1);
        course.updateGrades(new Grades(GRADES_DB));
        course.addGradesForProject(project1, grades);
        course.updateGrades(new Grades(GRADES_DB));
        assertEquals(92, course.getAverageProjectsGrade(student1));
        assertEquals(95, course.getAverageAssignmentsGrade(student2));

    }
}
    
