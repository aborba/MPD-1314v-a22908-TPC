/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.isel.mpd14.alerter;

import java.util.*;

/**
 * @author Miguel Gamboa at CCISEL
 */
public class Alarm {

  /*
   * Cada evento é representado por um instante de tempo em Milisegundos
  */
  final private SortedSet<Long> events = new TreeSet<Long>();
  final private Collection<AlarmNotificationsSubscriber> subscribers = new LinkedList<AlarmNotificationsSubscriber>();

  public void addEvent(long time) {
    events.add(time);
  }

  public void subscribeAlarmNotifications(AlarmNotificationsSubscriber subscriber) {
    this.subscribers.add(subscriber);
  }

  public void unsubscribeAlarmNotifications(AlarmNotificationsSubscriber item) {
    for (AlarmNotificationsSubscriber subscriber : subscribers) {
      if (subscriber.equals(item)) {
        subscribers.remove(subscriber);
      }
    }
  }

  public void start() {

    Iterator<Long> iter = events.iterator();
    Long instant = iter.hasNext() ? iter.next() : null;
    while (instant != null) {
      long justNow = System.currentTimeMillis();
      if (justNow >= instant) {
        System.out.println("\n---> Triiiim");

        // Notify enrolled reporters
        AlarmBag alarmBag = new AlarmBag("Triiim", justNow);
        for (AlarmNotificationsSubscriber subscriber : subscribers) {
          subscriber.notify(alarmBag);
        }

        iter.remove();
        instant = iter.hasNext() ? iter.next() : null;

      }
    }
  }

}
