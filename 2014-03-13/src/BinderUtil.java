import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by António on 18-03-2014.
 */
public class BinderUtil<T, V> {

    public static <T, V> T prepare(T fieldClass, V value) {
        return fieldClass == String.class ? (T)value.toString() : (T)value;
    }

    public static boolean isCompatible(Class source, Class target) {

        if (target == String.class || target.isAssignableFrom(source)) {
            return true;
        }

        final HashSet<Class> booleanCompatibility = new HashSet<>();
        booleanCompatibility.add(boolean.class);
        booleanCompatibility.add(Boolean.class);

        final HashSet<Class> charCompatibility = new HashSet<>();
        charCompatibility.add(char.class);
        charCompatibility.add(Character.class);

        final HashSet<Class> byteCompatibility = new HashSet<>();
        byteCompatibility.add(byte.class);
        byteCompatibility.add(Byte.class);

        final HashSet<Class> shortCompatibility = new HashSet<>();
        shortCompatibility.add(short.class);
        shortCompatibility.add(Short.class);
        for (Class klass : byteCompatibility)
            shortCompatibility.add(klass);

        final HashSet<Class> intCompatibility = new HashSet<>();
        intCompatibility.add(int.class);
        intCompatibility.add(Integer.class);
        for (Class klass : shortCompatibility)
            intCompatibility.add(klass);

        final HashSet<Class> longCompatibility = new HashSet<>();
        longCompatibility.add(long.class);
        longCompatibility.add(Long.class);
        for (Class klass : intCompatibility)
            longCompatibility.add(klass);

        final HashSet<Class> floatCompatibility = new HashSet<>();
        floatCompatibility.add(float.class);
        floatCompatibility.add(Float.class);
        for (Class klass : longCompatibility)
            floatCompatibility.add(klass);

        final HashSet<Class> doubleCompatibility = new HashSet<>();
        doubleCompatibility.add(double.class);
        doubleCompatibility.add(Double.class);
        for (Class klass : floatCompatibility)
            doubleCompatibility.add(klass);

        final HashMap<Class, HashSet<Class>> compatibility = new HashMap<>();
        compatibility.put(boolean.class, booleanCompatibility);
        compatibility.put(Boolean.class, booleanCompatibility);
        compatibility.put(char.class, charCompatibility);
        compatibility.put(Character.class, charCompatibility);
        compatibility.put(short.class, shortCompatibility);
        compatibility.put(Short.class, shortCompatibility);
        compatibility.put(int.class, intCompatibility);
        compatibility.put(Integer.class, intCompatibility);
        compatibility.put(long.class, longCompatibility);
        compatibility.put(Long.class, longCompatibility);
        compatibility.put(float.class, floatCompatibility);
        compatibility.put(Float.class, floatCompatibility);
        compatibility.put(double.class, doubleCompatibility);
        compatibility.put(Double.class, doubleCompatibility);

        if (compatibility.containsKey(source) && compatibility.get(source).contains(target)) {
            return true;
        }
        return false;
    }

}
