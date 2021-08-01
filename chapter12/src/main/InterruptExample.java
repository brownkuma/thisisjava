package main;

import thread.PrintThread2;

public class InterruptExample {
  public static void main(String[] args) {
    Thread thread = new PrintThread2();
    thread.start();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException ie) {}

    thread.interrupt();
  }
}
