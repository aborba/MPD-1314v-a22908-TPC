package pt.isel.mpd.v14n.g01.a22908;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
            throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        return bindTo(klass, fieldsVals);
    }

    public static <T> T bindTo(Class<T> klass, Map<String, Object> fieldsVals)
        throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        if (klass == null || fieldsVals == null) {
            throw new IllegalArgumentException("Invalid null argument.");
        }

        T klassInstance;
        try {
            Class<?> klassParentClass = klass.getDeclaringClass();
            Constructor<?>[] cs = klass.getConstructors();
            Constructor<?> ctor = klass.getDeclaredConstructor(); // NoSuchMethodException
            klassInstance = klass.newInstance();
        }
        catch (InstantiationException ie) { // perhaps due to be an inner class
            Class<?> klassParentClass = klass.getDeclaringClass();
            Constructor<?> ctor = klass.getDeclaredConstructor(klassParentClass); // NoSuchMethodException
            klassInstance = (T) ctor.newInstance(klassParentClass.newInstance()); // IllegalAccessException, InstantiationException, InvocationTargetException
        }

        Field[] klassFields = klass.getDeclaredFields(); // Fields to fill in
        Set<String> fieldsValsKeys = fieldsVals.keySet(); // Available keys in map

        for (Field klassField : klassFields) {
            String klassFieldName = klassField.getName();
            Class klassFieldClass = klassField.getType();
            if (fieldsValsKeys.contains(klassFieldName)) {
                Object valueInFieldsVals = fieldsVals.get(klassFieldName);
                if (valueInFieldsVals == null) {
                    continue;
                }
                klassField.setAccessible(true); // Suppresses the accessibility check
                if (BinderUtil.isCompatible(klassFieldClass, valueInFieldsVals.getClass())) {
                    klassField.set(klassInstance, BinderUtil.prepare(klassFieldClass, valueInFieldsVals));
                }
            }
        }

        return klassInstance;
    }

    public static <T> T bindTo_Colega(Class<T> targetClass, Map<String, Object> fields)
            throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {

        T target = targetClass.newInstance();

        Field[] targetFields = targetClass.getDeclaredFields();
        for (Field f : targetFields) {
            Object o = fields.get(f.getName());
            if (o != null && WrapperUtilities.toWrapper(f.getType()).isAssignableFrom(o.getClass())) {
                f.setAccessible(true);
                f.set(target, o);
            }
        }
        return target;
    }

    public static class WrapperUtilities {

        final static Map<Class<?>, Class<?>> wrappers = new HashMap<>();
        static {
            wrappers.put(boolean.class, Boolean.class);
            wrappers.put(short.class, Short.class);
        }

        public static Class<?> toWrapper(Class<?> c) {
            return c.isPrimitive() ? wrappers.get(c) : c;
        }
    }

    // TPC 03 - 2014-03-18
    public static <T> T bindToProperties(Class<T> klass, Map<String, Object> vals)
            throws IllegalAccessException, InstantiationException, InvocationTargetException {

        if (klass == null || vals == null) {
            throw new IllegalArgumentException("Invalid null argument.");
        }

        T klassInstance = klass.newInstance();

        Method[] klassMethods = klass.getMethods();

        for (Method klassMethod : klassMethods) {
            // Go for name
            String klassMethodName = klassMethod.getName();
            if (klassMethodName.substring(0, 3).compareTo("set") != 0) {
                continue;
            }
            String propertyName = klassMethodName.substring(3);
            propertyName = propertyName.substring(0, 1).toLowerCase() + propertyName.substring(1);
            if (!vals.containsKey(propertyName)) {
                continue;
            }
            // Go for parameters
            Object valsValue = vals.get(propertyName);
            Class<?>[] klassMethodParameters = klassMethod.getParameterTypes();
            if (klassMethodParameters.length != 1) {
                continue;
            }
            if (BinderUtil.isCompatible(klassMethodParameters[0], valsValue.getClass())) {
                klassMethod.invoke(klassInstance, valsValue);
            }
        }

        return klassInstance;
    }

}
