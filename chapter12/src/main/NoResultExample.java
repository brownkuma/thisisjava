package main;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NoResultExample {
  /**
   * 스레드의 리턴값이 필요 없는 작업의 경우 runnable을 사용한다
   * 결과값이 없음에도 불구하고 반환되는 Future객테는 스레드가 작업 처리를 정상적으로 완료했는지, 아니면 작업 처리 도중에 예외가 발생했는지 확인하기 위한 값이다
   *  ㄴ 정상완료: Future.get은 null
   *  ㄴ 예외발생: ExecutionException 발생
   * @param args
   */
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(
        Runtime.getRuntime().availableProcessors()
    );

    System.out.println("작업 처리 요청");
//    Runnable runnable = new Runnable() {
//      @Override
//      public void run() {
//        int sum = 0;
//        for(int i=0; i<=10; i++) {
//          sum += i;
//        }
//        System.out.println("[처리 결과] " + sum);
//      }
//    };
//    Future future = executorService.submit(runnable);

    // lamda version
    Future future = executorService.submit(() -> {
      int sum = 0;
      for(int i=0; i<=10; i++) {
        sum += i;
      }
      System.out.println("[처리 결과] " + sum);
    });

    try {
      future.get();
      System.out.println("작업 처리 완료");
    } catch (InterruptedException ie) {
      System.out.println("작업 중 스레드 interrupt 발생 " + ie.getMessage());
    } catch (ExecutionException ee) {
      System.out.println("작업 중 예외 발생" + ee.getMessage());
    }

    executorService.shutdown();
  }

}
