/*
 * Copyright (C) 2014 Miguel Gamboa at CCISEL
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Adapted by António Borba da Silva, 22908 at ISEL
 * (Group 01, Class LI41N, Summer 2013-2014, MPD, LEIC)
 */

package pt.isel.leic.mpd.v1314n.g01.a22908.probe.test.model;

import pt.isel.leic.mpd.v1314n.g01.a22908.probe.Format;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by António on 2014/04/09.
 */
class Grade {
}

class Course {
}

public class Student {

  public final int id;
  private Date birthDate;
  private List<Grade> grades;
  private String name;
  private Course course;

  public Student() {
    this(0, null, "DEFAULT NAME");
  }

  public Student(int id, Date birthDate, String name) {
    this(id, birthDate, name, null);
  }

  public Student(int id, Date birthDate, String name, Course course) {
    this.id = id;
    this.birthDate = birthDate;
    this.name = name;
    this.course = course;
    this.grades = new LinkedList<>();
  }


  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public List<Grade> getGrades() {
    return grades;
  }

  public void setGrades(List<Grade> grades) {
    this.grades = grades;
  }

  public String getName() {
    return name;
  }

  @Format(formatterKlass = FormatterToUpperCase.class)
  public void setName(String name) {
    this.name = name;
  }

  public Course getCourse() {
    return course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }


}