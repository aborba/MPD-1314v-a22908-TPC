package pt.isel.mpd14.alerter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by António on 2014/05/27.
 */
public class HoraHHMMSSreport implements AlarmNotificationsSubscriber {

  @Override
  public void notify(AlarmBag alarmBag) {
    System.out.println(String.format("Acknowledge %s at HoraHHMMSSreport at %s.",
        alarmBag.message, new SimpleDateFormat("HH:mm:ss").format(new Date(alarmBag.time))));
  }
}
