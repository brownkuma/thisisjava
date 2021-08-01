package main;

import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 콜백 방식의 작업 진행
 * 블로킹 방식은 작업 처리를 요청한 후 작업이 완료될 때까지 블로킹되지만(작업 처리 요청 후 Future.get()이 되기까지 블로킹)
 * 콜백 방식은 작업 처리 요청 후 결과를 기다릴 필요 없이 다른 기능을 수행할 수 있다(작업이 완료되면 콜백 메소드를 실행해주기 때문)
 */
public class CallbackExample {
  private ExecutorService executorService;

  public CallbackExample() {
    executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
  }

  /**
   * CompletionHandler 인터페이스는 비동기 통신의 콜백 객체를 만들때 사용된다.
   */
  private CompletionHandler<Integer, Void> callback =
      new CompletionHandler<Integer, Void>() {
        /**
         * 작업을 정상 처리했을 경우 호출
         * @param result  결과값
         * @param attachment  콜백 메서드에 결과값 이외에 추가적으로 전달하는 첨부값(필요없을 경우 Void)
         */
        @Override
        public void completed(Integer result, Void attachment) {
          System.out.println("completed() 실행: " + result);
        }

        /**
         * 예외가 발생했을 경우 호출
         * @param exc
         * @param attachment
         */
        @Override
        public void failed(Throwable exc, Void attachment) {
          System.out.println("failed() 실행: " + exc.toString());
        }
      };

  public void doWork(final String x, final String y) {
    Runnable task = ()->{
      try {
        int intX = Integer.parseInt(x);
        int intY = Integer.parseInt(y);
        int result = intX+intY;
        callback.completed(result, null);
      } catch (NumberFormatException nfe) {
        callback.failed(nfe, null);
      }
    };

    executorService.submit(task);
  }

  public void finish() {
    executorService.shutdown();
  }

  public static void main(String[] args) {
    CallbackExample example  = new CallbackExample();
    example.doWork("3", "3");
    example.doWork("3", "삼");
    example.finish();
  }
}
