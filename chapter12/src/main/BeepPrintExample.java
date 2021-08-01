package main;

import thread.BeepTask;
import thread.BeepThread;

import java.awt.*;

public class BeepPrintExample {
  public static void main(String[] args) {
//    onlyMainThread();
//    mainWithWorkThread();
//    mainWithWorkThread2();
//    mainWithWorkThread3();
//    mainWithWorkThread4();
  }

  public static void onlyMainThread() {
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    for(int i=0; i<5; i++) {
      toolkit.beep();
      try { Thread.sleep(500); } catch (Exception e) {}
    }

    for(int i =0; i<5; i++) {
      System.out.println("1");
      try { Thread.sleep(500); } catch (Exception e) {}
    }
  }

  public static void mainWithWorkThread() {
    Runnable beepTask = new BeepTask();
    Thread thread = new Thread(beepTask);
    thread.start();

    for(int i =0; i<5; i++) {
      System.out.println("2");
      try { Thread.sleep(500); } catch (Exception e) {}
    }
  }

  public static void mainWithWorkThread2() {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for(int i=0; i<5; i++) {
          toolkit.beep();
          try { Thread.sleep(500); } catch (Exception e) {}
        }
      }
    });
    thread.start();

    for(int i =0; i<5; i++) {
      System.out.println("3");
      try { Thread.sleep(500); } catch (Exception e) {}
    }
  }

  public static void mainWithWorkThread3() {
    Thread thread = new Thread(() -> {
      Toolkit toolkit = Toolkit.getDefaultToolkit();
      for(int i=0; i<5; i++) {
        toolkit.beep();
        try { Thread.sleep(500); } catch (Exception e) {}
      }
    });
    thread.start();

    for(int i =0; i<5; i++) {
      System.out.println("4");
      try { Thread.sleep(500); } catch (Exception e) {}
    }
  }

  public static void mainWithWorkThread4() {
    Thread thread = new BeepThread();
    thread.start();

    for(int i =0; i<5; i++) {
      System.out.println("5");
      try { Thread.sleep(500); } catch (Exception e) {}
    }
  }

  public static void mainWithWorkThread5() {
    Thread thread = new Thread() {
      @Override
      public void run() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for(int i=0; i<5; i++) {
          toolkit.beep();
          try { Thread.sleep(500); } catch (Exception e) {}
        }
      }
    };
    thread.start();

    for(int i =0; i<5; i++) {
      System.out.println("6");
      try { Thread.sleep(500); } catch (Exception e) {}
    }
  }

}
