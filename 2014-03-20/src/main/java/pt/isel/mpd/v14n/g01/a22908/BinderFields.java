package pt.isel.mpd.v14n.g01.a22908;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by António on 2014/03/21.
 *
 * TPC 02 - 2014-03-13
 * Adicione à classe Binder o método estático <T> T bindToFields(Class<T> klass, Map<String, Object> fieldsVals) que
 * retorna uma instância de T cujos campos são afectados com os valores dos pares de nome correspondente em filedsVals
 * -- só devem ser afectados os campos com o nome igual ao do par e tipo compatível com o valor do par.
 */

public class BinderFields extends AbstractBinder {

    AbstractBinder propertySetters;

    public BinderFields() {
        propertySetters = null;
    }

    public BinderFields(AbstractBinder propertySetters) {
        this.propertySetters = propertySetters;
    }

    @Override
    public boolean bindWith(Object o, String target, Object value)
            throws IllegalAccessException, InvocationTargetException, NoSuchFieldException, NoSuchMethodException {
        if (propertySetters == null || ! propertySetters.bindWith(o, target, value)) {
            Field field = o.getClass().getDeclaredField(target);
            field.setAccessible(true); // Suppresses the accessibility check
            field.set(o, BinderUtil.prepare(field, value));
        }
        return true;
    }

}
