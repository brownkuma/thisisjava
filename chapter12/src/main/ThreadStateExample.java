package main;

import thread.StatePrintThread;
import thread.TargetThread;

public class ThreadStateExample {
  public static void main(String[] args) {
    StatePrintThread statePrintThread = new StatePrintThread(new TargetThread());
    statePrintThread.start();
  }
}
