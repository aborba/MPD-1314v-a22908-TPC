package pt.isel.mpd14.alerter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by António on 2014/05/27.
 */
public class HoraHHMMSSreport implements Reporter {

  @Override
  public void report(String message, long time) {
    System.out.println(String.format("Acknowledge %s at HoraHHMMSSreport at %s.",
        message, new SimpleDateFormat("HH:mm:ss").format(new Date(time))));
  }
}
