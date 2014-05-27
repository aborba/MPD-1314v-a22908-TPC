package pt.isel.mpd14.alerter;

/**
 * Created by António on 2014/05/27.
 */
public interface Reporter {
  public void report(String message, long time);
}
