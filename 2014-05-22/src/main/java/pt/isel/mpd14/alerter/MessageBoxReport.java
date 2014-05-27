package pt.isel.mpd14.alerter;

/**
 * Created by António on 2014/05/27.
 */
public class MessageBoxReport implements Reporter {

  @Override
  public void report(String message, long time) {
    System.out.println(String.format("Acknowledge %s at MessageBoxReport.", message));
    System.out.println("Popping up a message box...");
  }
}
