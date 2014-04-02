package pt.isel.leic.pdm.v1314n.g01.a22908.probe;

/**
 * Created by António on 2014/04/02.
 */
public class BindToUpper<T> implements BindMember<T> {

  private final BindMember bindMember;

  public BindToUpper(BindMember bindMember) {
    if (bindMember instanceof BindNonNull)
      throw new IllegalArgumentException("BindMember cannot be an instance of BindToUpper");
    this.bindMember = bindMember;
  }

  /**
   * bind - Default implementation - Converts a string to uppercase
   *
   * @param target
   * @param name
   * @param v
   * @return
   */
  @Override
  public boolean bind(T target, String name, Object v) {
    if (v instanceof String) {
      v = ((String) v).toUpperCase();
    }
    return bindMember.bind(target, name, v);
  }

}
