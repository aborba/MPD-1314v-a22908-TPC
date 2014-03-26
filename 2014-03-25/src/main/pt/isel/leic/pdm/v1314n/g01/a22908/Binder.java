package pt.isel.leic.pdm.v1314n.g01.a22908;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by António on 2014/03/26.
 */
public class Binder {
    private static BinderFields binderF = new BinderFields();
    private static BinderProps binderP = new BinderProps();

    private BinderInterface binderInterface;

    public Binder(BinderInterface binderInterface) {
        this.binderInterface = binderInterface;
    }

    public static Map<String, Object> getFieldsValues(Object o)
            throws IllegalArgumentException, IllegalAccessException
    {
        Map<String, Object> res = new HashMap<>();
        Field[] fs = o.getClass().getDeclaredFields();
        for (Field f : fs) {
            f.setAccessible(true);
            res.put(f.getName(), f.get(o));
        }
        return res;
    }

    public <T> T bindTo(Class<T> klass, Map<String, Object> vals)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        if (klass == null || vals == null) {
            throw new IllegalArgumentException();
        }

        T target = klass.newInstance();

        for (Map.Entry<String, Object> e : vals.entrySet()) {
            binderInterface.bindMember(target, e.getKey(), e.getValue());
        }

        return target;
    }


    /*
    public static <T> T bindToProps(Class<T> klass, Map<String, Object> vals)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        return binderP.(klass, vals);
    }


    public static <T> T bindToFields(Class<T> klass, Map<String, Object> vals)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        return binderF.binderInterface(klass, vals);
    }

    public static <T> T bindToFieldsAndProperties(Class<T> klass, Map<String, Object> vals)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {


        // ?????????? A classe BinderPropsAndFields dava jeito que extendesse das
        // duas classes BinderFields and BinderProps

        return new BinderPropsAndFields().bindTo(klass, vals);

    }
    */
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
