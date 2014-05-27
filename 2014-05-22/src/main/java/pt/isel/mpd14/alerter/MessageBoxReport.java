package pt.isel.mpd14.alerter;

/**
 * Created by António on 2014/05/27.
 */
public class MessageBoxReport implements AlarmNotificationsSubscriber {

  @Override
  public void notify(AlarmBag alarmBag) {
    System.out.println(String.format("Acknowledge %s at MessageBoxReport.", alarmBag.message));
    System.out.println("Popping up a message box...");
  }
}
