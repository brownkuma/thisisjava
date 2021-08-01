package main;

import java.util.concurrent.*;

public class ResultByCallableExample {

  public static void main(String[] args) {
//    example1();
//    example2();

    String tn = "01092593226";
    tn = tn.charAt(0) == '+' ? tn.substring(1) : "82" + tn.substring(1);
    System.out.println(tn);
  }

  /**
   * 스레드의 리턴값이 필요한 작업의 경우 callable을 사용한다
   * 리턴받을 타입은 submit에 전달한 callable의 call()메소드가 리턴하는 타입이 된다
   */
  public static void example1() {
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    System.out.println("[작업 처리 요청]");
    /**
     * Callable<T> T: call() 메소드가 리턴하는 타입이다
     */
    Callable<Integer> task = new Callable<Integer>() {
      @Override
      public Integer call() throws Exception {
        int sum = 0;
        for(int i=1; i<10; i++) {
          sum += i;
        }
        return sum;
      }
    };

    // Future<T> future = executorService.submit(Callable<T> callable);
    // submit() 메소드는 작업 큐에 Callable 객제를 저장하고 즉시 Future<T>를 리턴한다.
    Future<Integer> future = executorService.submit(task);

    try {
      // submit() 메소드가 호출되는 시점에 리턴되는 건 스레드가 실행결과가 아닌 Future<T>
      // 스레드가 Callable객제의 call()메소드를 모두 실행하고 T 타입의 값을 리턴하면 Future<T>의 get()메소드릐 블로킹이 해제되고 T 타입의 값을 리턴한다
      int sum = future.get();
      System.out.println("[작업 처리 결과] "+sum);
      System.out.println("[작업 처리 완료]");
    } catch (InterruptedException ie) { // 작업 도중 스레드가 interrupt 될 경우
    } catch (ExecutionException ee) {   // 작업 도중 예외가 발생 된 경우
    } catch (Exception e) {
      System.out.println("[실행 예외 발생]"+e.getMessage());
    }

    executorService.shutdown();
  }

  /**
   * 스레드의 리턴값을 전달하는 값이 아닌 외부 객체에 저장해야하는 경우 submit(Runnable runnable, V result) 를 사용할 수 있다
   *  ㄴ V : 리턴받을 외부 객체.
   *  주의해야 할 점은 스레드 내부에서 외부 객체에 접근, 값을 변경해야하기 때문에 생성자를 통해 V result 객체를 주입받도록 해야 한다
   */
  public static void example2() {
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    System.out.println("[작업 처리 요청]");
    class Task implements Runnable {
      Result result;
      Task(Result result) {
        this.result = result;
      }

      @Override
      public void run() {
        int sum = 0;
        for(int i=1; i<=10; i++) {
          sum += i;
        }
        result.addValue(sum);
      }
    }

    Result result = new Result();
    Runnable task1 = new Task(result);
    Runnable task2 = new Task(result);
    Future<Result> future1 = executorService.submit(task1, result);
    Future<Result> future2 = executorService.submit(task2, result);

    try {
      result = future1.get();
      result = future2.get();
      System.out.println("[작업 처리 결과] "+result.accumValue);
      System.out.println("[작업 처리 완료]");
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("[실행 예외 발생] "+e.getMessage());
    }

    executorService.shutdown();
  }
}

class Result {
  int accumValue;
  synchronized void addValue(int value) {
    accumValue += value;
  }
}