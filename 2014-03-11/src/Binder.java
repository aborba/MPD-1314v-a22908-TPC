import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by António on 12-03-2014.
 *
 * Implemente a classe Binder com o método estático Map<String, Object> getFieldsValues(Object o) que retorna um mapa
 * com os pares nome - valor de todos os campos do objecto o recebido por parametro (incluindo campos privados).
 *
 * References:
 *     <http://docs.oracle.com/javase/tutorial/reflect/member/fieldValues.html>
 *     <http://docs.oracle.com/javase/tutorial/reflect/member/fieldTrouble.html>
 */
public class Binder {

    public static Map<String, Object> getFieldsValues(Object o)
            throws IllegalAccessException, IllegalArgumentException {

        if (o == null) {
            throw new IllegalArgumentException("Null argument.");
        }

        Map<String, Object> fieldsValuesMap = new HashMap<String, Object>();
        Field[] fields = o.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // Supresses the accessibility check
            String mapKey = field.getName();
            Object mapValue = field.get(o);
            fieldsValuesMap.put(mapKey, mapValue);
        }

        return fieldsValuesMap;
    }

}
