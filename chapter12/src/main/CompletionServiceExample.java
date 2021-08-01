package main;

import java.util.concurrent.*;

public class CompletionServiceExample extends Thread {
  public static void main(String[] args) {
    System.out.println(Runtime.getRuntime().availableProcessors());
    ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    CompletionService<Integer> completionService = new ExecutorCompletionService<>(executorService);

    System.out.println("[작업 처리 요청] ");
    for(int i=0; i<3; i++) {
//      completionService.submit(new Callable<Integer>() {
//        @Override
//        public Integer call() throws Exception {
//          int sum = 0;
//          for(int i=0; i<=10; i++) {
//            sum += i;
//          }
//          return sum;
//        }
//      });

      // lamda version
      completionService.submit(() -> {
        int sum = 0;
        for(int i1 = 0; i1 <=10; i1++) {
          sum += i1;
        }
        return sum;
      });
    }

    System.out.println("[처리 완료된 작업 확인]");

//    executorService.submit(new Runnable() {
//       @Override
//       public void run() {
//         while (true) {
//           try {
//             Future<Integer> future = completionService.take();
//             int value = future.get();
//             System.out.println(Thread.currentThread().getName() + "-[처리 결과] " + value);
//           } catch (Exception e) {
//             break;
//           }
//       }
//     });

    // poll과 take 메소드를 이용해서 처리 완료된 작업의 Future를 얻으려면 CompletionService의 submit() 메소드로 작업 처리 요청을 해야 한다
    // 주의 할 점은 처리 완료된 작업은 처리 요청한 작업의 순서와 같지 않다 (작업 내용에 따라 먼저 요청한 작업이 나중에 완료될 수 있음)
    // CompletionService.submit(Callable<T> task);
    // CompletionService.submit(Runnable task, V result);
    // lamda version
    executorService.submit(() -> {
      while (true) {
        try {
          // Future<Integer> future = completionService.poll(); // 완료된 작업의 Future를 가져옴. 완료된 작업이 없다면 즉시 null을 리턴함
          Future<Integer> future = completionService.take();    // 완료된 작업의 Future를 가져옴. 완료된 작업이 없다면 있을 때까지 블로킹됨.
          int value = future.get();
          System.out.println(Thread.currentThread().getName() + "-[처리 결과] " + value);
        } catch (Exception e) {
          System.out.println("[take() 종료]");
          break;
        }
      }
    });

    try {
      Thread.sleep(3000);
    } catch (InterruptedException ignored) {}

    executorService.shutdownNow();  // take()에서 InterruptedException 발생하여 종료
  }
}
