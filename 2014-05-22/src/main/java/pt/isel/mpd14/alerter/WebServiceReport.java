package pt.isel.mpd14.alerter;

/**
 * Created by António on 2014/05/27.
 */
public class WebServiceReport implements Reporter {

  @Override
  public void report(String message, long time) {
    System.out.println(String.format("Acknowledge %s at WebServiceReport.", message));
    System.out.println("Invoking Web Service...");
  }
}
