package pt.isel.leic.mpd.v1314n.g01.a22908.probe;

/**
 * Created by António on 2014/04/02.
 */
public class BindToUpperCase<T> implements BindMember<T> {

  private final BindMember bindMember;

  public BindToUpperCase(BindMember bindMember) {
    if (bindMember instanceof BindToUpperCase)
      throw new IllegalArgumentException("BindMember cannot be an instance of BindToUpperCase");
    this.bindMember = bindMember;
  }

  /**
   * bind - Default implementation - Converts a string to uppercase
   */
  @Override
  public boolean bind(T target, String name, Object value) {
    if (value == null) {
      return false;
    }
    if (value instanceof String) {
      value = ((String) value).toUpperCase();
    }
    return bindMember.bind(target, name, value);
  }

}
