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

/**
 * Created by António on 2014/04/10.
 */
public class BindNonNull<T>
    implements BindMember<T> {

  private final BindMember bindMember;

  public BindNonNull(BindMember bindMember) {
    if (bindMember instanceof BindNonNull)
      throw new IllegalArgumentException("BindMember cannot be an instance of BindNonNull");
    this.bindMember = bindMember;
  }

  /**
   * bind - Default implementation - Acts on Non Null Values Only
   *
   * @param target
   * @param name
   * @param v
   * @return
   */
  public boolean bind(T target, String name, Object v) {
    if (v == null) {
      return false;
    }
    return bindMember.bind(target, name, v);

  }

}