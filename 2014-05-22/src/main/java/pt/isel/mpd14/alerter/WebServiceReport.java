package pt.isel.mpd14.alerter;

/**
 * Created by António on 2014/05/27.
 */
public class WebServiceReport implements AlarmNotificationsSubscriber {

  @Override
  public void notify(AlarmBag alarmBag) {
    System.out.println(String.format("Acknowledge %s at WebServiceReport.", alarmBag.message));
    System.out.println("Invoking Web Service...");
  }
}
