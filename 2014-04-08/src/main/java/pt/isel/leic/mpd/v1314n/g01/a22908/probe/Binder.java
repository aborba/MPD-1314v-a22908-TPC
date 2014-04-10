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

package pt.isel.leic.mpd.v1314n.g01.a22908.probe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static pt.isel.leic.mpd.v1314n.g01.a22908.probe.util.SneakyUtils.throwAsRTException;

/**
 * Created by António on 2014/04/10.
 */
public class Binder<T> {

  private final Class<T> targetKlass;
  private final BindMember<T>[] bms;

  public Binder(Class<T> k, BindMember<T>... bms) {
    this.targetKlass = k;
    this.bms = bms;
  }

  public static Map<String, Object> getFieldsValues(Object o)
      throws IllegalArgumentException, IllegalAccessException {
    Map<String, Object> res = new HashMap<>();
    Field[] fs = o.getClass().getDeclaredFields();
    for (Field f : fs) {
      f.setAccessible(true);
      res.put(f.getName(), f.get(o));
    }
    return res;
  }

  public T bindTo(Map<String, Object> vals) {
    try {
      if (vals == null) {
        throw new IllegalArgumentException();
      }
      T target = targetKlass.newInstance();
      for (Map.Entry<String, Object> e : vals.entrySet()) {
        for (BindMember bm : bms) {
          if (bm.bind(target, e.getKey(), e.getValue()))
            break;
        }

      }
      return target;
    } catch (InstantiationException | IllegalAccessException ex) {
      throwAsRTException(ex);
    }
    throw new IllegalStateException();
  }

}

class WrapperUtilites {

  final static Map<Class<?>, Class<?>> wrappers = new HashMap<>();

  static {
    wrappers.put(boolean.class, Boolean.class);
    wrappers.put(short.class, Short.class);
    wrappers.put(boolean.class, Boolean.class);
    wrappers.put(int.class, Integer.class);

  }

  public static Class<?> toWrapper(Class<?> c) {
    return c.isPrimitive() ? wrappers.get(c) : c;
  }

}
