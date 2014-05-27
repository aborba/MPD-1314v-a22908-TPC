package pt.isel.mpd14.alerter;

/**
 * Created by António on 2014/05/27.
 */
public class AlarmBag {
  final String message;
  final long time;

  public AlarmBag(String message, long time) {
    this.message = message;
    this.time = time;
  }

  public AlarmBag(String message) {
    this(message, 0);
  }

  public AlarmBag(Long time) {
    this(null, time);
  }

}
