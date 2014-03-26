package pt.isel.leic.pdm.v1314n.g01.a22908;

import org.junit.Test;
import pt.isel.leic.pdm.v1314n.g01.a22908.model.Student;
import pt.isel.leic.pdm.v1314n.g01.a22908.model.StudentDto;

import java.text.SimpleDateFormat;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by António on 2014/03/26.
 */
public class BinderTest {

    final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Test
    public void test_bind_student_to_studentDto_fields() throws Exception
    {
        /*
         * Arrange
         */
        Student s1 = new Student(31531, sdf.parse("05-6-1994"), "Jose Cocacola", null);
        Map<String, Object> s1fields = Binder.getFieldsValues(s1);
        /*
         * Act
         */
        StudentDto s2 = new Binder(new BinderFields()).bindTo(StudentDto.class, s1fields);
        System.out.println(s2);

        /*
        if(s2.id != s1.id){
            throw new IllegalStateException();
        }
        */

        /*
         * Assert
         */
        assertEquals(s1.getId(), s2.id);
        assertEquals(s1.getName(), s2.name);
        assertEquals(null, s2.birthDate);
    }

    @Test
    public void test_bind_student_to_studentDto_props() throws Exception
    {
        /*
         * Arrange
         */
        Student s1 = new Student(31531, sdf.parse("05-6-1994"), "Jose Cocacola", null);
        Map<String, Object> s1fields = Binder.getFieldsValues(s1);
        /*
         * Act
         */
        StudentDto s2 = new Binder(new BinderProps()).bindTo(StudentDto.class, s1fields);
        System.out.println(s2);

        /*
        if(s2.id != s1.id){
            throw new IllegalStateException();
        }
        */

        /*
         * Assert
         */
        assertEquals(s1.getId(), s2.id);
        assertEquals(s1.getName(), s2.name);
        assertEquals(null, s2.birthDate);
    }

    @Test
    public void test_bind_student_to_studentDto_propsandfields() throws Exception
    {
        /*
         * Arrange
         */
        Student s1 = new Student(31531, sdf.parse("05-6-1994"), "Jose Cocacola", null);
        Map<String, Object> s1fields = Binder.getFieldsValues(s1);
        /*
         * Act
         */
        StudentDto s2 = new Binder(new BinderPropsAndFields()).bindTo(StudentDto.class, s1fields);
        System.out.println(s2);

        /*
        if(s2.id != s1.id){
            throw new IllegalStateException();
        }
        */

        /*
         * Assert
         */
        assertEquals(s1.getId(), s2.id);
        assertEquals(s1.getName(), s2.name);
        assertEquals(null, s2.birthDate);
    }

}
