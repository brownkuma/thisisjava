package thread;

public class ThreadB extends Thread {
  public boolean stop = false;
  public boolean work = true;

  public ThreadB() {
    setName("ThreadB");
  }

  public void run() {
//    for(int i=0; i<2; i++) {
//      System.out.println(getName()+"가 출력한 내용");
//    }

    while(!stop) {
      if(work) {
        System.out.println("B");
      }else {
        Thread.yield();
      }
    }

    System.out.println(Thread.currentThread().getName()+" 종료 !!!!!");
  }
}
