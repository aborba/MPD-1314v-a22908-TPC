package pt.isel.mpd.v14n.g01.a22908;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by António on 12-03-2014.
 */
public class BinderTests {

    final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public class Course {
    }

    public class Grade {
    }

    public class Student {
        final int id;
        final Date birthDate;
        final List<Grade> grades;
        final String name;
        final Course course;

        public Student(int id, Date birthDate, String name, Course course) {
            this.id = id;
            this.birthDate = birthDate;
            this.name = name;
            this.course = course;
            this.grades = new LinkedList<Grade>();
        }
    }

    public static class StudentDto {
        int id;
        int birthDate;
        String name;

        public int getId() {
            return id;
        }

        public void setId(int _id) {
            id = _id;
        }

        public int getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(int _birthDate) {
            birthDate = _birthDate;
        }

        public String getName() {
            return name;
        }

        public void setName(String _name) {
            name = _name;
        }
    }

    @Test
    public void testStudent_getFieldsValues() throws Exception {
        Student student = new Student(
                22908,
                sdf.parse("1962-05-18"),
                "António Borba",
                null
        );
        Map<String, Object> fieldsValuesMap = Binder.getFieldsValues(student);

        Object tstId = fieldsValuesMap.get("id");
        Class tstIdClass = tstId.getClass();
        assertEquals("class java.lang.Integer", tstIdClass.toString());
        assertEquals(22908, ((Integer)tstId).intValue());

        Object tstName = fieldsValuesMap.get("name");
        Class tstNameClass = tstName.getClass();
        assertEquals("class java.lang.String", tstNameClass.toString());
        assertTrue(tstName.toString().compareTo("António Borba") == 0);

        Object tstBirthDate = fieldsValuesMap.get("birthDate");
        Class tstBirthDateClass = tstBirthDate.getClass();
        assertEquals("class java.util.Date", tstBirthDateClass.toString());
        assertEquals(new GregorianCalendar(1962, 4, 18).getTime(), tstBirthDate);

        Object tstCourse = fieldsValuesMap.get("course");
        Class tstCourseClass = tstName.getClass();
        assertEquals("class java.lang.String", tstCourseClass.toString());
        assertTrue(tstCourse == null);

        Object tstGrades = fieldsValuesMap.get("grades");
        Class tstGradesClass = tstGrades.getClass();
        assertEquals("class java.util.LinkedList", tstGradesClass.toString());
    }

    @Test
    public void testStudent_bindToFields() throws Exception {
        Student student = new Student(
                22908,
                sdf.parse("1962-05-18"),
                "António Borba",
                null
        );
        Map<String, Object> studentFieldsValuesMap = Binder.getFieldsValues(student);
        StudentDto studentDto = Binder.bindToFields(StudentDto.class, studentFieldsValuesMap);

        String tstName = studentDto.name;
        assertTrue(tstName.toString().compareTo("António Borba") == 0);
    }

    @Test
    public void testStudent_bindToProperties() throws Exception {
        Student student = new Student(
                22908,
                sdf.parse("1962-05-18"),
                "António Borba",
                null
        );
        Map<String, Object> studentFieldsValuesMap = Binder.getFieldsValues(student);
        StudentDto studentDto = Binder.bindToProperties(StudentDto.class, studentFieldsValuesMap);

        assertEquals("Expected António Borba", "António Borba", studentDto.getName());
        assertEquals("Expected 22908", 22908, studentDto.getId());
        assertEquals("Student.birthDate:Date and StudentDto.birthDate:int; No value assignement was expected.", 0, studentDto.getBirthDate());
    }

}