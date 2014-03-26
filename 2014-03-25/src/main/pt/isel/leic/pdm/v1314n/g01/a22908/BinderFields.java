package pt.isel.leic.pdm.v1314n.g01.a22908;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by António on 2014/03/26.
 */
public class BinderFields implements BinderInterface {
    @Override
    public <T> boolean bindMember(T target, String key, Object value)
            throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        Field[] fields = target.getClass().getDeclaredFields();
        for (Field f : fields) {
            String fName = f.getName();
            if (fName.equals(key)) {
                Class<?> fType = f.getType();
                f.setAccessible(true);
                if (fType.isPrimitive()) {
                    fType = f.get(target).getClass();
                }
                /*
                 * Verifica se o tipo do campo (fType) é tipo base do tipo de fValue.
                 * Nota: Tipo base inclui superclasses ou superinterfaces.
                 */
                if (fType.isAssignableFrom(value.getClass())) {
                    f.set(target, value);
                    return true;
                } else {
                    return false;
                }
            }
        }

        return false;
    }
}
