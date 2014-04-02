package pt.isel.leic.pdm.v1314n.g01.a22908.probe.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import pt.isel.leic.pdm.v1314n.g01.a22908.probe.*;
import pt.isel.leic.pdm.v1314n.g01.a22908.probe.test.model.Student;
import pt.isel.leic.pdm.v1314n.g01.a22908.probe.test.model.StudentDto;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for Binder class.
 */
public class BinderTest extends TestCase {

  final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

  public void test_bind_student_to_studentDto() throws Exception {
    // Arrange
    Student s1 = new Student(31531, sdf.parse("05-6-1994"), "Jose Cocacola", null);
    Map<String, Object> s1fields = Binder.getFieldsValues(s1);
    // Act
    StudentDto s2 =
        new Binder<>(StudentDto.class, new BindField<StudentDto>())
            .bindTo(s1fields);
    System.out.println(s2);

    Assert.assertEquals(s1.id, s2.id);
    Assert.assertEquals(s1.getName(), s2.name);
    Assert.assertEquals(null, s2.birthDate);

  }

  public void test_bind_fields_filter_null_values() throws Exception {
    // Arrange
    Map<String, Object> v = new HashMap<>();
    v.put("name", null);
    v.put("id", 657657);
    v.put("birthDate", "4-5-1997");
    // Act
    // StudentDto s2 = new Binder(new BindFieldNonNull())
    StudentDto s2 = new Binder<>(StudentDto.class, new BindNonNull<StudentDto>(new BindField()))
        .bindTo(v);
    System.out.println(s2);
    // Assert
    Assert.assertEquals(v.get("id"), s2.id);
    Assert.assertEquals("DEFAULT NAME", s2.name);
    Assert.assertEquals(v.get("birthDate"), s2.birthDate);

  }

  public void test_bind_to_student_properties() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
    // Arrange
    Map<String, Object> v = new HashMap<>();
    v.put("name", "Maria josefina");
    v.put("id", 657657);
    v.put("birthdate", sdf.parse("4-5-1997"));
    // Act
    Student s = new Binder<>(Student.class, new BindProp<Student>(Student.class))
        .bindTo(v);
    // Assert
    Assert.assertEquals(v.get("name"), s.getName());
    Assert.assertEquals(0, s.id); // id is not set because it is not a property
    Assert.assertEquals(v.get("birthdate"), s.getBirthDate());
  }

  public void test_bind_to_student_properties_and_fields() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ParseException {
    // Arrange
    Map<String, Object> v = new HashMap<>();
    v.put("name", "Maria josefina");
    v.put("id", 657657);
    v.put("birthdate", sdf.parse("4-5-1997"));
    // Act
    Student s = new Binder<>(Student.class, new BindProp<>(Student.class), new BindField<>())
        .bindTo(v);
    // Assert
    Assert.assertEquals(v.get("name"), s.getName());
    Assert.assertEquals(v.get("id"), new Integer(s.id));
    Assert.assertEquals(v.get("birthdate"), s.getBirthDate());
  }

  public void test_bind_fields_convert_strings_uppercase() throws Exception {
    // Arrange
    Map<String, Object> v = new HashMap<>();
    v.put("name", "José CocaCola");
    v.put("id", 657657);
    v.put("birthDate", "4-5-1997");
    // Act
    // StudentDto s2 = new Binder(new BindFieldNonNull())
    StudentDto s2 = new Binder<>(StudentDto.class, new BindToUpper<StudentDto>(new BindField()))
        .bindTo(v);
    System.out.println(s2);
    // Assert
    Assert.assertEquals(v.get("id"), s2.id);
    Assert.assertEquals("JOSÉ COCACOLA", s2.name);
    Assert.assertEquals(v.get("birthDate"), s2.birthDate);
  }

}
