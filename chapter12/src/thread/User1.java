package thread;

import vo.Calculator;

public class User1 extends Thread {
  private Calculator calculator;

  public void setCalculator(Calculator calculator) {
    this.setName("thread.User1");
    this.calculator = calculator;
  }

  public void run() {
    calculator.setMemory(100);
  }
}
