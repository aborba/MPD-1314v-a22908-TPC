package pt.isel.leic.pdm.v1314n.g01.a22908;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by António on 2014/03/26.
 */
public class BinderPropsAndFields implements BinderInterface {

    private BinderInterface binderF = new BinderFields();
    private BinderInterface binderP = new BinderProps();

    @Override
    public <T> boolean bindMember(T target, String key, Object value)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        if(!binderP.bindMember(target, key, value))
            return binderF.bindMember(target, key, value);
        return true;
    }
}
