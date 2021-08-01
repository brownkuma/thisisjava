package thread;

public class CalThread extends Thread {
  public CalThread(String name) {
    setName(name);
  }

  public void run() {
    double j = 0;
    for (int i=0; i<2000000000; i++) {
      j += i;
    }
    System.out.println(getName());
  }
}
