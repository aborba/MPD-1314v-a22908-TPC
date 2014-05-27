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
  SortedSet<Long> events = new TreeSet<Long>();
  List<Reporter> reporters = new LinkedList<Reporter>();

  public void addEvent(long time) {
    events.add(time);
  }

  public void addReporter(Reporter reporter) {
    reporters.add(reporter);
  }

  public void start() {

    Iterator<Long> iter = events.iterator();
    Long instant = iter.hasNext() ? iter.next() : null;
    while (instant != null) {
      long justNow = System.currentTimeMillis();
      if (justNow >= instant) {
        System.out.println("\n---> Triiiim");

        // Notify enrolled reporters
        for (Reporter reporter : reporters) {
          reporter.report("Triiim", justNow);
        }

        iter.remove();
        instant = iter.hasNext() ? iter.next() : null;

      }
    }
  }

}
