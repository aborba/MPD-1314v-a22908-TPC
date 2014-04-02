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

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static pt.isel.leic.pdm.v1314n.g01.a22908.probe.util.SneakyUtils.throwAsRTException;

/**
 * TPC 01
 * Implemente a classe Binder com o método estático Map<String, Object> getFieldsValues(Object o) que retorna um mapa
 * com os pares nome - valor de todos os campos do objecto o recebido por parametro (incluindo campos privados).
 */

/**
 * TPC 02
 * Adicione à classe Binder o método estático <T> T bindTo(Class<T> klass, Map<String, Object> fieldsVals) que retorna
 * uma instância de T cujos campos são afectados com os valores dos pares de nome correspondente em filedsVals -- só
 * devem ser afectados os campos com o nome igual ao do par e tipo compatível com o valor do par.
 */

/**
 * TPC 03
 * Adicione à classe Binder o método estático <T> T bindToProperties(Class<T> klass, Map<String, Object> vals) que
 * retorna uma instância de T cujas propriedades são afectadas com os valores dos pares de nome correspondente em vals
 * -- só devem ser afectadas as propriedades com o nome igual ao do par e tipo compatível com o valor do par.
 */

/**
 * TPC 04
 * Adicione à classe Binder o método estático <T> T bindToFieldsAndProps(Class<T> klass, Map<String, Object> vals) que
 * retorna uma instância de T cujas propriedades e campos são afectados com os valores dos pares de nome correspondente
 * em vals -- só devem ser afectadas as propriedades ou campos com o nome igual ao do par e tipo compatível com o valor
 * do par.
 * Se existir uma propriedade e campo com o mesmo nome, então a propriedade prevalece sobre o campo.
 * NOTA: deve reestrutar o codigo da class Binder de modo não repetir codigo entre os seus métodos.
 */

/**
 * TPC 05
 * Refazer a solução da aula 08 de modo a que a class Binder dependa apenas de uma estratégia de afectação de membros
 * definida por uma instância da interface BindMember que tem um método bind(Object target, String key, Object v).
 * Actualizar os testes unitários em conformidade de modo a que para afectar campos passe a ser usado
 * new Binder(new BindFields()).bindTo(...) e para propriedades
 * new Binder(new BindProps()).bindTo(...)
 */

/**
 * TPC 06
 * A definição de um novo BindPropNonNull obedece aos mesmo passos da classe BindFieldNonNull. Implemente uma solução
 * que evite a repetição de código entre estas duas classes.
 * ATENÇÃO: Não pode alterar a API da classe Binder incluindo o construtor.
 */

/**
 * @author Miguel Gamboa at CCISEL
 *         <p>
 *         Adapted by António Borba - 22908 - on 2014/03/28.
 */
public class Binder {

  private final BindMember[] bms;

  public Binder(BindMember... bms) {
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

  public <T> T bindTo(Class<T> klass, Map<String, Object> vals) {
    try {
      if (klass == null || vals == null) {
        throw new IllegalArgumentException();
      }
      T target = klass.newInstance();
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

/**
 * @author Miguel Gamboa at CCISEL
 *         <p>
 *         Adapted by António Borba - 22908 - on 2014/03/28.
 */
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
