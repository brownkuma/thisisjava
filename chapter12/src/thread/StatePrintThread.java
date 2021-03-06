package thread;

public class StatePrintThread extends Thread {
  private Thread targetThread;

  public StatePrintThread(Thread targetThread) {
    this.targetThread = targetThread;
  }

  public void run() {
    while (true) {
      Thread.State state = targetThread.getState();
      System.out.println("타켓 스레드 상태: "+ state);

      if(state == State.NEW) {
        targetThread.start();
      }

      if(state == State.TERMINATED) {
        break;
      }

      System.out.println("SLEEP~~~~~~~~~~~~~~~~~~~~~~~~~~");
      try {
        Thread.sleep(500);
      } catch (Exception e) {}
    }
  }

}
