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

import pt.isel.leic.mpd.v1314n.g01.a22908.probe.util.SneakyUtils;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractBindMember<T>
    implements BindMember<T> {

  protected Map<AnnotatedElement, Formatter> formattersMap = new HashMap<>();

  protected void addFormatter(AnnotatedElement annotatedElement, Class<T> hostClass) {

    Format formatAnnotation = annotatedElement.getAnnotation(Format.class);

    if (formatAnnotation == null) {
      return;
    }

    try {
      Class<? extends Formatter> formatterClass = formatAnnotation.formatterClass();
      if (formatterClass != Formatter.class) {
        Formatter formatter = formatterClass.newInstance();
        formattersMap.put(annotatedElement, formatter);
        return;
      }
      String formatterMethodName = formatAnnotation.formatterMethod();
      if (formatterMethodName.equals("")) {
        return;
      }
      Method[] methods = hostClass.getDeclaredMethods();
      for (Method method : methods) {
        if (!method.getName().equals(formatterMethodName) ||
            method.getReturnType() == void.class ||
            method.getParameterCount() > 1) {
          continue;
        }
        method.setAccessible(true);
        formattersMap.put(annotatedElement, new FormatterAdapter(method));
      }
    } catch (InstantiationException | IllegalAccessException ex) {
      SneakyUtils.throwAsRTException(ex);
    }
  }

  protected Object format(AnnotatedElement annotatedElement, Object target)
      throws InstantiationException, IllegalAccessException {
    Formatter formatter = formattersMap.get(annotatedElement);
    if (formatter != null) {
      return formatter.format(target);
    }
    return target;
  }

}
