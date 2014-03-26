package pt.isel.mpd.v14n.g01.a22908;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
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
 * Adicione à classe Binder o método estático <T> T bindToFields(Class<T> klass, Map<String, Object> fieldsVals) que
 * retorna uma instância de T cujos campos são afectados com os valores dos pares de nome correspondente em filedsVals
 * -- só devem ser afectados os campos com o nome igual ao do par e tipo compatível com o valor do par.
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

/**
 * Created by António on 18-03-2014.
 */
public class BinderUtil<T, V> {

    private final static HashMap<Class, HashSet<Class>> compatibilityMap = new HashMap<>();
    private final static HashSet<Class> booleanCompatibility = new HashSet<>();
    private final static HashSet<Class> charCompatibility = new HashSet<>();
    private final static HashSet<Class> byteCompatibility = new HashSet<>();
    private final static HashSet<Class> shortCompatibility = new HashSet<>();
    private final static HashSet<Class> intCompatibility = new HashSet<>();
    private final static HashSet<Class> longCompatibility = new HashSet<>();
    private final static HashSet<Class> floatCompatibility = new HashSet<>();
    private final static HashSet<Class> doubleCompatibility = new HashSet<>();

    static {
        booleanCompatibility.add(boolean.class);
        booleanCompatibility.add(Boolean.class);
        compatibilityMap.put(boolean.class, booleanCompatibility);
        compatibilityMap.put(Boolean.class, booleanCompatibility);

        charCompatibility.add(char.class);
        charCompatibility.add(Character.class);
        compatibilityMap.put(char.class, charCompatibility);
        compatibilityMap.put(Character.class, charCompatibility);

        byteCompatibility.add(byte.class);
        byteCompatibility.add(Byte.class);
        compatibilityMap.put(byte.class, byteCompatibility);
        compatibilityMap.put(Byte.class, byteCompatibility);

        shortCompatibility.add(short.class);
        shortCompatibility.add(Short.class);
        compatibilityMap.put(short.class, shortCompatibility);
        compatibilityMap.put(Short.class, shortCompatibility);

        intCompatibility.add(int.class);
        intCompatibility.add(Integer.class);
        compatibilityMap.put(int.class, intCompatibility);
        compatibilityMap.put(Integer.class, intCompatibility);

        longCompatibility.add(long.class);
        longCompatibility.add(Long.class);
        compatibilityMap.put(long.class, longCompatibility);
        compatibilityMap.put(Long.class, longCompatibility);

        floatCompatibility.add(float.class);
        floatCompatibility.add(Float.class);
        compatibilityMap.put(float.class, floatCompatibility);
        compatibilityMap.put(Float.class, floatCompatibility);

        doubleCompatibility.add(double.class);
        doubleCompatibility.add(Double.class);
        compatibilityMap.put(double.class, doubleCompatibility);
        compatibilityMap.put(Double.class, doubleCompatibility);
    }

    public static <T, V> T prepare(T fieldClass, V value) {
        return fieldClass == String.class ? (T)value.toString() : (T)value;
    }

    public static boolean isCompatible(Class source, Class target) {

        if (target.isAssignableFrom(source)) {
            return true;
        }

        if (compatibilityMap.containsKey(source) && compatibilityMap.get(source).contains(target)) {
            return true;
        }

        return false;
    }

}
