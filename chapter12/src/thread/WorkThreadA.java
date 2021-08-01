package thread;

import vo.WorkObject;

public class WorkThreadA extends Thread {
  private WorkObject workObject;

  public WorkThreadA(WorkObject workObject) {
    this.workObject = workObject;
  }

  @Override
  public void run() {
    for(int i=0; i<10; i++) {
      workObject.methodA();
    }
  }
}
