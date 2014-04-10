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

package pt.isel.leic.mpd.v1314n.g01.a22908.probe.test;

import org.junit.Assert;
import org.junit.Test;
import pt.isel.leic.mpd.v1314n.g01.a22908.probe.BindField;
import pt.isel.leic.mpd.v1314n.g01.a22908.probe.BindNonNull;
import pt.isel.leic.mpd.v1314n.g01.a22908.probe.BindProp;
import pt.isel.leic.mpd.v1314n.g01.a22908.probe.Binder;
import pt.isel.leic.mpd.v1314n.g01.a22908.probe.test.model.Student;
import pt.isel.leic.mpd.v1314n.g01.a22908.probe.test.model.StudentDto;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by António on 2014/04/10.
 */
public class BinderTest {

  final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

  @Test
  public void test_bind_student_to_studentDto() throws Exception {
        /*
         Arrange
        */
    Student s1 = new Student(31531, sdf.parse("05-6-1994"), "Jose Cocacola", null);
    Map<String, Object> s1fields = Binder.getFieldsValues(s1);
        /*
          Act
        */
    StudentDto s2 =
        new Binder<>(StudentDto.class, new BindField<StudentDto>(StudentDto.class))
            .bindTo(s1fields);
    System.out.println(s2);

    Assert.assertEquals(s1.id, s2.id);
    Assert.assertEquals(s1.getName().toUpperCase(), s2.name);
    Assert.assertEquals(null, s2.birthDate);

  }

  @Test
  public void test_bind_fields_filter_null_values() throws Exception {
        /*
         Arrange
        */
    Map<String, Object> v = new HashMap<>();
    v.put("name", null);
    v.put("id", 657657);
    v.put("birthDate", "4-5-1997");
        /*
          Act
        */
    // StudentDto s2 = new Binder(new BindFieldNonNull())
    StudentDto s2 = new Binder<>(StudentDto.class, new BindNonNull<StudentDto>(new BindField(StudentDto.class)))
        .bindTo(v);
    System.out.println(s2);
        /*
          Assert
        */
    Assert.assertEquals(v.get("id"), s2.id);
    Assert.assertEquals("DEFAULT NAME", s2.name);
    Assert.assertEquals(v.get("birthDate"), s2.birthDate);

  }

  @Test
  public void test_bind_fields_and_put_string_in_uppercase() throws Exception {
        /*
         Arrange
        */
    Map<String, Object> v = new HashMap<>();
    v.put("name", "jose manel baptista");
    v.put("id", 657657);
    v.put("birthDate", "4/5/1997");
        /*
          Act
        */
    // StudentDto s2 = new Binder(new BindFieldNonNull())
    StudentDto s2 = new Binder<>(StudentDto.class, new BindField<>(StudentDto.class))
        .bindTo(v);
    System.out.println(s2);
        /*
          Assert
        */
    Assert.assertEquals(v.get("id"), s2.id);
    Assert.assertEquals("JOSE MANEL BAPTISTA", s2.name);
    Assert.assertEquals("4-5-1997", s2.birthDate);

  }

  @Test
  public void test_bind_to_student_properties() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
        /*
        Arrange
        */
    Map<String, Object> v = new HashMap<>();
    v.put("name", "Maria josefina");
    v.put("id", 657657);
    v.put("birthdate", sdf.parse("4-5-1997"));
        /*
        Act
        */
    Student s = new Binder<>(Student.class, new BindProp<>(Student.class))
        .bindTo(v);
        /*
        Assert
        */
    Assert.assertEquals(v.get("name").toString().toUpperCase(), s.getName());
    Assert.assertEquals(0, s.id); // id is not set because it is not a property
    Assert.assertEquals(v.get("birthdate"), s.getBirthDate());
  }

  @Test
  public void test_bind_to_student_properties_and_fields() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
        /*
        Arrange
        */
    Map<String, Object> v = new HashMap<>();
    v.put("name", "Maria josefina");
    v.put("id", 657657);
    v.put("birthdate", sdf.parse("4-5-1997"));
        /*
        Act
        */
    Student s = new Binder<>(Student.class, new BindProp<>(Student.class), new BindField<>(Student.class))
        .bindTo(v);
        /*
        Assert
        */
    Assert.assertEquals(v.get("name").toString().toUpperCase(), s.getName());
    Assert.assertEquals(v.get("id"), new Integer(s.id));
    Assert.assertEquals(v.get("birthdate"), s.getBirthDate());
  }


}

