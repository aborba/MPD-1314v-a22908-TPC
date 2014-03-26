package pt.isel.leic.pdm.v1314n.g01.a22908;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by António on 2014/03/26.
 */
public interface BinderInterface {
    public <T> boolean bindMember(T target, String key, Object value)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;
}
