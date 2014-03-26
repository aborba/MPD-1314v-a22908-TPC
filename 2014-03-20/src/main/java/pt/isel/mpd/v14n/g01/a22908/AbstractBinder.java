package pt.isel.mpd.v14n.g01.a22908;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Set;

/**
 * Created by António on 2014/03/21.
 */
public abstract class AbstractBinder<T> {

    public abstract boolean bindWith(T t, String target, Object value)
            throws IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException;

    public T bindTo(Class<T> klass, Map<String, Object> map)
            throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {

        if (klass == null || map == null) {
            throw new IllegalArgumentException("Invalid null argument.");
        }

        Set<String> mapKeys = map.keySet(); // Available keys in map
        Field[] fields = klass.getDeclaredFields(); // Fields to fill in
        T t = klass.newInstance();

        for (Field field : fields) {
            String fieldName = field.getName();
            if (! mapKeys.contains(fieldName)) {
                continue;
            }
            Object mapValue = map.get(fieldName);
            if (mapValue == null) {
                continue;
            }
            if (BinderUtil.isCompatible(field.getType(), mapValue.getClass())) {
                bindWith(t, fieldName, mapValue);
            }
        }

        return t;
    }

}
