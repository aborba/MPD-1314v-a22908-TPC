package pt.isel.mpd14.alerter;

/**
 * Hello world!
 */
public class App {
  public static void main(String[] args) {
    Alarm a = new Alarm();
    long now = System.currentTimeMillis();
    a.addEvent(now + 2000);
    a.addEvent(now + 5000);
    a.addEvent(now + 6000);

    a.subscribeAlarmNotifications(new MessageBoxReport());
    HoraHHMMSSreport hhmmsSreport = new HoraHHMMSSreport();
    a.subscribeAlarmNotifications(hhmmsSreport);
    a.subscribeAlarmNotifications(new WebServiceReport());

    System.out.println("\n---> Starting...");
    a.start();

    now = System.currentTimeMillis();
    a.addEvent(now + 2000);
    a.addEvent(now + 5000);
    a.addEvent(now + 6000);

    a.unsubscribeAlarmNotifications(hhmmsSreport);

    System.out.println("\n---> Starting...");
    a.start();

  }
}
