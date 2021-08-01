package vo;

/**
 * 공유자원에 접근할 경우 내가 원하지 않는 값으로 조작될 위험이 있다
 */
public class Calculator {
  private int memory;

  public int getMemory() {
    return memory;
  }

  /**
   * 동기화 메소드 - 임계 영역으로 동기화 메소드가 실행될 때 객체에 잠금이 일어나고 실행 종료하면 잠금이 풀린다.
   * @param memory
   */
  public synchronized void setMemory(int memory) {
    //synchronized(this) {  // synchronized를 함수가 아닌 block으로 만들수도 있다
      this.memory = memory;
      try {
        Thread.sleep(2000);
      } catch (InterruptedException ie) {
      }
      System.out.println(Thread.currentThread().getName() + ": " + this.memory);
    //}
  }

  public void setMemoryOriginal(int memory) {
    this.memory = memory;
    try {
      Thread.sleep(2000);
    } catch (InterruptedException ie) {}
    System.out.println(Thread.currentThread().getName() + ": " + this.memory);
  }
}
