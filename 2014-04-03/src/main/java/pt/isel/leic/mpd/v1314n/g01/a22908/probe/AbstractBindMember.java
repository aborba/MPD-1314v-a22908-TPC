package pt.isel.leic.mpd.v1314n.g01.a22908.probe;

import java.lang.reflect.AnnotatedElement;
import java.util.HashMap;

/**
 * Created by António on 2014/04/08.
 */
public abstract class AbstractBindMember<T> implements BindMember<T> {

  private HashMap<AnnotatedElement, Formatter> formats = new HashMap<>();

  protected Object format(AnnotatedElement mb, Object v)
      throws IllegalAccessException, InstantiationException
  {
    Formatter f = formats.get(mb);
    if (f != null) {
      return f.format(v);
    }

    Format a = mb.getAnnotation(Format.class);
    if (a != null) {
      f = a.formatter().newInstance();
      formats.put(mb, f);
      v = f.format(v);
    }
    return v;
  }
}
