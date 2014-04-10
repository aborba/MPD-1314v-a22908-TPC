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

import junit.framework.TestCase;
import org.junit.Assert;
import pt.isel.leic.mpd.v1314n.g01.a22908.probe.Binder;
import pt.isel.leic.mpd.v1314n.g01.a22908.probe.test.model.Student;

import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by António on 2014/04/09.
 */
public class BinderTest_Pre_1dot5 extends TestCase {

  final static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

  public void test_tpc01_binder() throws Exception {

    Student student = new Student(1, sdf.parse("05-6-1994"), "José Cocacola");

    Map<String, Object> studentFields = Binder.getFieldsValues(student);

    String key = "id";
    Assert.assertTrue(studentFields.containsKey(key));
    Assert.assertEquals(student.id, studentFields.get(key));

    key = "name";
    Assert.assertTrue(studentFields.containsKey(key));
    Assert.assertEquals(student.getName(), studentFields.get(key));

    key = "birthDate";
    Assert.assertTrue(studentFields.containsKey(key));
    Assert.assertEquals(student.getBirthDate(), studentFields.get(key));
  }

}
