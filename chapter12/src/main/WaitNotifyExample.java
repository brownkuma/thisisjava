package main;

import thread.ConsumerThread;
import thread.ProducerThread;
import thread.WorkThreadA;
import thread.WorkThreadB;
import vo.DataBox;
import vo.WorkObject;

public class WaitNotifyExample {
  /**
   * 스레드 간 협업 wait() notify() notifyAll()
   * @param args
   */
  public static void main(String[] args) {
//    example1();
    example2();
  }

  public static void example1() {
    WorkObject shareObject = new WorkObject();  // 협업을 위한 공유객체 생성

    WorkThreadA workThreadA = new WorkThreadA(shareObject);
    WorkThreadB workThreadB = new WorkThreadB(shareObject);

    workThreadA.start();
    workThreadB.start();
  }

  public static void example2() {
    DataBox dataBox = new DataBox();  // 협업을 위한 공유객체 생성

    ProducerThread producerThread = new ProducerThread(dataBox);
    ConsumerThread consumerThread = new ConsumerThread(dataBox);

    producerThread.start();
    consumerThread.start();
  }
}
