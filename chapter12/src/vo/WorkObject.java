package vo;

/**
 * 협업을 위한 공유객체는 각각의 스레드가 작업을 내용을 동기화 메소드(synchronized)로 구분해 놓는다
 * 한 스레드가 작업을 완료하면 notify()메소드를 호출하여 일시 정지 상태에 있는 다른 스레드를 실행 대기 상태로 만들고
 * 자신은 두 번 작업하지 않도록 wait() 메소드를 호출하여 일시 정지 상태로 만든다
 */
public class WorkObject {
  public synchronized void methodA() {
    System.out.println("WorkThreadA의 methodA 작업 실행 !!");
    notify();   // 일시정지중인 WorkThreadB를 대기상태로 전환

    try {
      wait();   // WorkThreadA를 일시정지 상태로 전환
    } catch (InterruptedException ie) {}
  }

  public synchronized void methodB() {
    System.out.println("WorkThreadB의 methodB 작업 실행 !!");
    notify();   // 일시정지중인 WorkThreadA를 대기상태로 전환

    try {
      wait();   // WorkThreadB를 일시정지 상태로 전환
    } catch (InterruptedException ie) {}
  }
}
