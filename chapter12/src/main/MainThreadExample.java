package main;

import thread.User1;
import thread.User2;
import vo.Calculator;

public class MainThreadExample {
  public static void main(String[] args) {
    Calculator calculator = new Calculator();

    User1 user1 = new User1();
    user1.setCalculator(calculator);
    user1.start();

    User2 user2 = new User2();
    user2.setCalculator(calculator);
    user2.start();

  }
}
