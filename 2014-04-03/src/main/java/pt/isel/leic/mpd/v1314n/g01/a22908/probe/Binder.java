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
 * TPC 07
 * Utilizando a API da framework probe (e sem modificá-la) crie um Binder que converte para maiúsculas valores do tipo
 * String que sejam afectados a campos ou propriedades.
 * ATENÇÃO: Não pode alterar a API da classe Binder incluindo o construtor.
 */

/**
 * TPC 08
 * Reestruture o código da classe FieldBinderde modo a que:
 * - a funcionalidade de obter o Formatter e formatar um valor, possa ser reutilizada por outro binder, e.g. BindProp.
 * - a ter cuidados de desempenho para que nas afectações seguintes do mesmo membro (campo ou propriedade) não repita o
 * trabalho de reflexão de obtenção da anotação e instanciação do Formatter.
 */

package pt.isel.leic.mpd.v1314n.g01.a22908.probe;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static pt.isel.leic.mpd.v1314n.g01.a22908.probe.util.SneakyUtils.throwAsRTException;

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
