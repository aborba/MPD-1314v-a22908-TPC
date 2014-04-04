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
 */

package pt.isel.leic.mpd.v1314n.g01.a22908.probe.util;

import static pt.isel.leic.mpd.v1314n.g01.a22908.probe.util.SneakyUtils.throwAsRTException;

/**
 * @author Miguel Gamboa at CCISEL
 *
 *         adapted by António Borba da Silva - 22908
 */
public class SneakyApp {

  public static void main(String[] args) {
    test();
  }

  public static void test() {
    try {
      method(0);
    } catch (IllegalAccessException ex) {
      throwAsRTException(ex);
    }
  }

  public static void method(int divider) throws IllegalAccessException {
    try {
      int number = 5;
      int result = number / divider;
    } catch (ArithmeticException ae) {
      throw new IllegalAccessException("Illegal divider = " + divider);
    }
  }
}
