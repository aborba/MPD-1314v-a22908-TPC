package pt.isel.mpd.v14n.g01.a22908;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by António on 12-03-2014.
 *
 * TPC 01 - 2014-03-11
 * Implemente a classe Binder com o método estático Map<String, Object> getFieldsValues(Object o) que retorna um mapa
 * com os pares nome - valor de todos os campos do objecto o recebido por parametro (incluindo campos privados).
 *
 * References:
 *  <http://docs.oracle.com/javase/tutorial/reflect/member/fieldValues.html>
 *  <http://docs.oracle.com/javase/tutorial/reflect/member/fieldTrouble.html>
 *
 * TPC 02 - 2014-03-13
 * Adicione à classe Binder o método estático <T> T bindTo(Class<T> klass, Map<String, Object> fieldsVals) que retorna
 * uma instância de T cujos campos são afectados com os valores dos pares de nome correspondente em filedsVals -- só
 * devem ser afectados os campos com o nome igual ao do par e tipo compatível com o valor do par.
 *
 * TPC 03 - 2014-03-18
 * Adicione à classe Binder o método estático <T> T bindToProperties(Class<T> klass, Map<String, Object> vals) que
 * retorna uma instância de T cujas propriedades são afectadas com os valores dos pares de nome correspondente em
 * vals -- só devem ser afectadas as propriedades com o nome igual ao do par e tipo compatível com o valor do par.
 *
 * TPC 04 - 2014-03-20
 * Adicione à classe Binder o método estático <T> T bindToFieldsAndProps(Class<T> klass, Map<String, Object> vals) que
 * retorna uma instância de T cujas propriedades e campos são afectados com os valores dos pares de nome correspondente
 * em vals -- só devem ser afectadas as propriedades ou campos com o nome igual ao do par e tipo compatível com o valor
 * do par.
 * Se existir uma propriedade e campo com o mesmo nome, então a propriedade prevalece sobre o campo.
 * NOTA: deve reestrutar o codigo da class Binder de modo não repetir codigo entre os seus métodos.
 *
 */
public class Binder<T> {

    // TPC 01 - 2014-03-11
    public static Map<String, Object> getFieldsValues(Object o)
            throws IllegalAccessException, IllegalArgumentException {

        if (o == null) {
            throw new IllegalArgumentException("Invalid null argument.");
        }

        Map<String, Object> fieldsValuesMap = new HashMap<String, Object>();
        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Suppresses the accessibility check
            String mapKey = field.getName();
            Object mapValue = field.get(o);
            fieldsValuesMap.put(mapKey, mapValue);
        }

        return fieldsValuesMap;
    }

    // TPC 02 - 2014-03-13
    public static <T> T bindToFields(Class<T> klass, Map<String, Object> fieldsVals)
            throws IllegalAccessException, InstantiationException, NoSuchMethodException, NoSuchFieldException,
            InvocationTargetException
    {
        return (T)new BinderFields().bindTo(klass, fieldsVals);
    }

    // TPC 03 - 2014-03-18
    public static <T> T bindToProperties(Class<T> klass, Map<String, Object> vals)
            throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException,
            IllegalAccessException
    {
        return (T)new BinderProperties().bindTo(klass, vals);
    }

    // TPC 04 - 2014-03-20
    public static <T> T bindToFieldsAndProps(Class<T> klass, Map<String, Object> vals)
            throws InvocationTargetException, NoSuchMethodException, NoSuchFieldException, InstantiationException,
            IllegalAccessException
    {
        return (T)new BinderFields(new BinderProperties()).bindTo(klass, vals);
    }


}
