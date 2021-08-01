package main;

import thread.AutoSaveThread;

public class DaemonExample {

  /**
   * 데몬 스레드는 주 스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드이다(워드프로세서의 자동 저장, 미디어 플레이어의 동영상 및 음악 재생, 가비지 컬렉터 등)
   * 주 스레드가 종료되면 데몬 스레드는 강제적으로 자동 종료된다
   * setDaamon(true);를 호출해주면 된다.
   * @param args
   */
  public static void main(String[] args) {
    AutoSaveThread autoSaveThread = new AutoSaveThread();
    autoSaveThread.setDaemon(true);
    autoSaveThread.start();

    try {
      Thread.sleep(3000);
    } catch (InterruptedException ie) {}

    System.out.println("메인 스레드 종료");
  }
}
