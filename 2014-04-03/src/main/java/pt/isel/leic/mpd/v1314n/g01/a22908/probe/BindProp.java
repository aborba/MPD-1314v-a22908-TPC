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
package pt.isel.leic.mpd.v1314n.g01.a22908.probe;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static pt.isel.leic.mpd.v1314n.g01.a22908.probe.util.SneakyUtils.throwAsRTException;

/**
 * @author Miguel Gamboa at CCISEL
 *
 *         adapted by António Borba da Silva - 22908
 */
public class BindProp<T> implements BindMember<T> {

  private final Map<String, Method> setters = new HashMap<>();

  public BindProp(Class<T> targetKlass) {

    Method[] methods = targetKlass.getMethods();
    for (Method method : methods) {
      String methodName = method.getName();
      if (!methodName.startsWith("set")) {
        continue;
      }
      if (!(method.getReturnType() == void.class)) {
        continue;
      }
      Class<?>[] parameterTypes = method.getParameterTypes();
      if (parameterTypes.length != 1) {
        continue;
      }
      setters.put(method.getName().substring(3).toLowerCase(), method);
    }
  }

  @Override
  public boolean bind(T target, String key, Object value) {
    try {
      Method method = setters.get(key.toLowerCase());
      if (method != null) {
        Class<?> propertyType = WrapperUtilites.toWrapper(method.getParameterTypes()[0]);
        if (propertyType.isAssignableFrom(value.getClass())) {
          method.setAccessible(true);
          method.invoke(target, value);
          return true;
        }
      }
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
      throwAsRTException(ex);
    }
    return false;
  }

}
