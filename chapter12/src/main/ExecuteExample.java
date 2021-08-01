package main;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExecuteExample {
  public static void main(String[] args) throws Exception {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    for(int i=0; i<10; i++) {
      Runnable runnable = new Runnable() {
        @Override
        public void run() {
          ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) executorService;
          int poolsize = threadPoolExecutor.getPoolSize();
          String threadName = Thread.currentThread().getName();
          System.out.println("[총 스레드 개수: "+ poolsize + "] 작업 스레드 이름: " + threadName);

          int value = Integer.parseInt("삼");  //(일부러)예외 발생
        }
      };

      //처리 결과를 받지 못함. 작업 처리 중 예외 발생하면 스레드 종료&스레드풀에서 제거
      //실행 결과 스레드가 종료되고 새로 생성되기 때문에 스레드이름이 계속 변경되는 것을 확인
      executorService.execute(runnable);

      //처리 결과를 받음(Future). 작업 처리 중 예외 발생하면 스레드는 종료되지 않고 다음 작업을 위해 재사용
      //실행 결과 스레드가 종료되지 않고 재사용되기 때문에 스레드이름은 계속 동일함을 확인
      //executorService.submit(runnable);

      Thread.sleep(10);
    }

    executorService.shutdown();
  }
}
