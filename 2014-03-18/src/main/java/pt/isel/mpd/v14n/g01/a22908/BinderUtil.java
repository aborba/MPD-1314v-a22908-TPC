package pt.isel.mpd.v14n.g01.a22908;

import java.util.HashMap;
import java.util.HashSet;

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
