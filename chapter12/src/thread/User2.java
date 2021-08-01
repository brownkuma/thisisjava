package thread;

import vo.Calculator;

public class User2 extends Thread {
  private Calculator calculator;

  public void setCalculator(Calculator calculator) {
    this.setName("thread.User2");
    this.calculator = calculator;
  }

  public void run() {
    calculator.setMemory(50);
  }
}
