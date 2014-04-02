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
package pt.isel.leic.pdm.v1314n.g01.a22908.probe;

/**
 * @author Miguel Gamboa at CCISEL
 *
 *         Adapted by António Borba - 22908 - on 2014/03/28.
 */
public class BindNonNull implements BindMember {

  private final BindMember bindMember;

  public BindNonNull(BindMember bindMember) {
    if (bindMember instanceof BindNonNull) {
      throw new IllegalArgumentException("BindMember cannot be an instance of BindNonNull");
    }
    this.bindMember = bindMember;
  }

  public <T> boolean bind(T target, String name, Object v) {
    if (bindMember == null || v == null) {
      return false;
    }
    return bindMember.bind(target, name, v);
  }

}
