package main;

import thread.PrintThread1;

public class StopFlagExample {
  public static void main(String[] args) {
    PrintThread1 printThread1 = new PrintThread1();
    printThread1.start();

    try { Thread.sleep(100); } catch (InterruptedException ie) {}

    printThread1.setStop(true);
  }
}
