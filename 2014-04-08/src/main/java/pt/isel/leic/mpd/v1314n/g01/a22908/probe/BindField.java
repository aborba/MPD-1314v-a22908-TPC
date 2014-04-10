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

import static pt.isel.leic.mpd.v1314n.g01.a22908.probe.util.SneakyUtils.throwAsRTException;

/**
 * Created by António on 2014/04/10.
 */
public class BindField<T> extends AbstractBindMember<T> {

  public BindField(Class<T> targetKlass) {
    for (Field f : targetKlass.getDeclaredFields()) {
      addFormatter(f);
    }

  }

  @Override
  public boolean bind(T target, String name, Object v) {
    try {
      Field[] fields = target.getClass().getDeclaredFields();
      for (Field f : fields) {
        String fName = f.getName();
        if (fName.equals(name)) {
          Class<?> fType = f.getType();
          f.setAccessible(true);
          if (fType.isPrimitive()) {
            fType = f.get(target).getClass();
          }
                    /*
                     * Verifica se o tipo do campo (fType) é tipo base do tipo de fValue.
                     * Nota: Tipo base inclui superclasses ou superinterfaces.
                     */
          if (fType.isAssignableFrom(v.getClass())) {
            f.set(target, format(f, v));
            return true;
          } else {
            return false;
          }
        }
      }
    } catch (IllegalArgumentException | IllegalAccessException | InstantiationException ex) {
      throwAsRTException(ex);
    }
    return false;

  }

}