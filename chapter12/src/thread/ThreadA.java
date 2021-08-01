package thread;

public class ThreadA extends Thread {
  public boolean stop = false;
  public boolean work = true;

  public ThreadA() {
    //thread 이름을 설정할 수 있다
    setName("ThreadA");
  }

  public void run() {
//    for(int i=0; i<2; i++) {
//      System.out.println(getName()+"가 출력한 내용");
//    }

    while(!stop) {
      if(work) {
        System.out.println("A");
      }else {
        Thread.yield();
      }
    }

    System.out.println(Thread.currentThread().getName()+" 종료 !!!!!");
  }
}
