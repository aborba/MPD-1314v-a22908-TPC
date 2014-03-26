package pt.isel.mpd.v14n.g01.a22908;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by António on 2014/03/21.
 */
public class BinderProperties extends AbstractBinder {

    @Override
    public boolean bindWith(Object o, String target, Object value)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        String targetMethod = "set" + target.substring(0, 1).toUpperCase() + target.substring(1);
        Method[] methods = o.getClass().getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.equals(targetMethod)) {
                method.setAccessible(true); // Suppresses the accessibility check
                method.invoke(o, value);
                return true;
            }
        }
        return false;
    }

}
