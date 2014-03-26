import org.junit.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by António on 12-03-2014.
 */
public class BinderTests {

    public class Grade {
        private int ucId;
        private int grade;

        public Grade(int ucId, int grade) {
            this.ucId = ucId;
            this.grade = grade;
        }

        public int getUcId() { return ucId; }

        public int getGrade() { return grade; }
    }

    public class Student {
        private int id;
        private String name;
        private Date birthDate;
        private String course;
        private List<Grade> grades;

        public Student(int id, String name, Date birthDate, String course, List<Grade> grades) {
            this.id = id;
            this.name = name;
            this.birthDate = birthDate;
            this.course = course;
            this.grades = grades;
        }

        public int getId() { return id; }

        public String getName() { return name; }

        public void setName(String name) { this.name = name; }

        public Date getBirthDate() { return birthDate; }

        public void setBirthDate(Date birthDate) { this.birthDate = birthDate; }

        public String getCourse() { return course; }

        public void setCourse(String course) { this.course = course; }

        public List<Grade> getGrades() { return grades; }

        public void setGrades(List<Grade> grades) { this.grades = grades; }
    }

    @Test
    public void testGetGrade() throws Exception {
        Grade myGrade = new Grade(1, 18);
        Map<String, Object> fieldsValuesMap = Binder.getFieldsValues(myGrade);

        Object tstId = fieldsValuesMap.get("ucId");
        Class tstIdClass = tstId.getClass();
        assertEquals("class java.lang.Integer", tstIdClass.toString());
        assertEquals(1, ((Integer)tstId).intValue());

        Object tstGrade = fieldsValuesMap.get("ucId");
        Class tstGradeClass = tstGrade.getClass();
        assertEquals("class java.lang.Integer", tstGradeClass.toString());
        assertEquals(1, ((Integer)tstGrade).intValue());
    }

    @Test
    public void testStudent() throws Exception {
        Student student = new Student(
                22908,
                "António Borba",
                new GregorianCalendar(1962, 5, 18).getTime(),
                "LEIC",
                Arrays.asList(
                        new Grade(1, 16),
                        new Grade(2, 18)
                )
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
        assertEquals(new GregorianCalendar(1962, 5, 18).getTime(), tstBirthDate);

        Object tstCourse = fieldsValuesMap.get("course");
        Class tstCourseClass = tstName.getClass();
        assertEquals("class java.lang.String", tstCourseClass.toString());
        assertTrue(tstCourse.toString().compareTo("LEIC") == 0);

        Object tstGrades = fieldsValuesMap.get("grades");
        Class tstGradesClass = tstGrades.getClass();
        assertEquals("class java.util.Arrays$ArrayList", tstGradesClass.toString());
        assertEquals(((Grade)((List)tstGrades).get(0)).getUcId(),   1);
        assertEquals(((Grade)((List)tstGrades).get(0)).getGrade(), 16);
        assertEquals(((Grade)((List)tstGrades).get(1)).getUcId(),   2);
        assertEquals(((Grade)((List)tstGrades).get(1)).getGrade(), 18);
    }

}