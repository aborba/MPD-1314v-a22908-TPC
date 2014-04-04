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

import java.lang.reflect.Field;

import static pt.isel.leic.mpd.v1314n.g01.a22908.probe.util.SneakyUtils.throwAsRTException;

/**
 * @author Miguel Gamboa at CCISEL
 *
 *         adapted by António Borba da Silva - 22908
 */
public class BindField<T> implements BindMember<T> {

  @Override
  public boolean bind(T target, String name, Object value) {
    try {
      Field[] fields = target.getClass().getDeclaredFields();
      for (Field field : fields) {
        String fieldName = field.getName();
        if (fieldName.equals(name)) {
          Class<?> fieldType = field.getType();
          field.setAccessible(true);
          if (fieldType.isPrimitive()) {
            fieldType = field.get(target).getClass();
          }
          /*
           * Verifica se o tipo do campo (fieldType) é tipo base do tipo de fValue.
           * Nota: Tipo base inclui superclasses ou superinterfaces.
           */
          if (fieldType.isAssignableFrom(value.getClass())) {
            field.set(target, value);
            return true;
          } else {
            return false;
          }
        }
      }
    } catch (IllegalArgumentException | IllegalAccessException ex) {
      throwAsRTException(ex);
    }
    return false;
  }

}
