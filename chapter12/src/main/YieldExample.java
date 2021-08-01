package main;

import thread.ThreadA;
import thread.ThreadB;

public class YieldExample {
  /**
   * 다른 스레드에게 실행 양보 yield()
   * @param args
   */
  public static void main(String[] args) {
    ThreadA threadA = new ThreadA();
    ThreadB threadB = new ThreadB();
    threadA.start();
    threadB.start();

    try { Thread.sleep(1000); } catch (InterruptedException ie) {}
    threadA.work = false;

    try { Thread.sleep(3000); } catch (InterruptedException ie) {}
    threadA.work = true;

    try { Thread.sleep(1000); } catch (InterruptedException ie) {}
    threadA.stop = true;
    threadB.stop = true;
  }
}
