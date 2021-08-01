package main;

public class ThreadNameExample {
  public static void main(String[] args) {
    Thread mainT = Thread.currentThread();
    System.out.println("main thread name = "+mainT.getName());

    thread.ThreadA threadA = new thread.ThreadA();
    System.out.println("worker thread name = "+threadA.getName());
    threadA.start();

    thread.ThreadB threadB = new thread.ThreadB();
    System.out.println("worker thread name = "+threadB.getName());
    threadB.start();
  }
}
