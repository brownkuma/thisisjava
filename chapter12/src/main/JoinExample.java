package main;

import thread.SumThread;

public class JoinExample {
  /**
   * 다른 스레드의 종료를 기다림 join()
   * @param args
   */
  public static void main(String[] args) {
    SumThread sumThread = new SumThread();
    sumThread.start();

    try {
      sumThread.join();   //sumThread.run() 메소드가 종료될때까지 기다림
    } catch (InterruptedException ie) {}

    System.out.println("1~100까지의 합: "+sumThread.getSum());
  }

}
