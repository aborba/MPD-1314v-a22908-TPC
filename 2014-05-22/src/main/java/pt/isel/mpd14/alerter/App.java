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

    a.addReporter(new MessageBoxReport());
    a.addReporter(new HoraHHMMSSreport());
    a.addReporter(new WebServiceReport());

    a.start();
  }
}
