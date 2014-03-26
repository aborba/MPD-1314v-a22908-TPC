package pt.isel.leic.pdm.v1314n.g01.a22908.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by António on 2014/03/26.
 */

class Grade {
}

class Course {
}

public class Student {

    private int id;
    private Date birthDate;
    private List<Grade> grades;
    private String name;
    private Course course;

    public Student() {
    }

    public Student(int id, Date birthDate, String name, Course course) {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
        this.course = course;
        this.grades = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
