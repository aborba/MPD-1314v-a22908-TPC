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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static pt.isel.leic.mpd.v1314n.g01.a22908.probe.util.SneakyUtils.throwAsRTException;

/**
 * Created by António on 2014/04/10.
 */
public class BindProp<T>
    extends AbstractBindMember<T> {

  private final Map<String, Method> setters = new HashMap<>();

  public BindProp(Class<T> targetKlass) {
    Method[] ms = targetKlass.getMethods();
    for (Method m : ms) {
      String mName = m.getName();
      if (!mName.startsWith("set")) {
        continue;
      }
      if (!(m.getReturnType() == void.class)) {
        continue;
      }
      Class<?>[] paramsKlasses = m.getParameterTypes();
      if (paramsKlasses.length != 1) {
        continue;
      }
      setters.put(m.getName().substring(3).toLowerCase(), m);
      addFormatter(m, targetKlass);
    }
  }

  @Override
  public boolean bind(T target, String key, Object v) {
    try {
      Method m = setters.get(key.toLowerCase());
      if (m != null) {
        Class<?> propType = WrapperUtilites.toWrapper(m.getParameterTypes()[0]);
        if (propType.isAssignableFrom(v.getClass())) {
          m.setAccessible(true);
          m.invoke(target, format(m, v));
          return true;
        }
      }
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException ex) {
      throwAsRTException(ex);
    }
    return false;

  }

}
