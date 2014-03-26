package pt.isel.leic.pdm.v1314n.g01.a22908;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by António on 2014/03/26.
 */
public class BinderProps implements BinderInterface {
    @Override
    public <T> boolean bindMember(T target, String key, Object value)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        Method[] ms = target.getClass().getMethods();
        for (Method m : ms) {
            String mName = m.getName();
            if (!mName.equalsIgnoreCase("set" + key)) {
                continue;
            }
            Class<?>[] paramsKlasses = m.getParameterTypes();
            if (paramsKlasses.length != 1) {
                continue;
            }
            Class<?> propType = WrapperUtilites.toWrapper(paramsKlasses[0]);
            if (propType.isAssignableFrom(value.getClass())) {
                m.setAccessible(true);
                m.invoke(target, value);
                return true;
            }
        }
        return false;
    }
}
