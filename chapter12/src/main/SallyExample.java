package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SallyExample {
  public static void main(String[] args) {

    List<String> fileList = new ArrayList<>();
    for(int i=1; i<=100; i++) {
      fileList.add(i+"");
    }
    List<List<String>> subjectLists =  choppedList(fileList, 10);

    WorkResult workResult = new WorkResult();
    List<Runnable> taskList = new ArrayList<>();
    for(List<String> subjects : subjectLists) {
      taskList.add(new Task(subjects, workResult));
    }

    ExecutorService executorService = Executors.newFixedThreadPool(3);
    List<Future<WorkResult>> futureList = new ArrayList<>();
    for(Runnable task : taskList) {
      futureList.add(executorService.submit(task, workResult));
    }

    try {
      for(Future<WorkResult> future : futureList) {
        future.get();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    executorService.shutdown();
    System.out.println("::: 성공 = "+workResult.succCnt);
    System.out.println("::: 실패 = "+workResult.failCnt);

  }

  static <T> List<List<T>> choppedList(List<T> list, final int L) {
    List<List<T>> parts = new ArrayList<List<T>>();
    final int N = list.size();
    for (int i = 0; i < N; i += L) {
      parts.add(new ArrayList<T>(
          list.subList(i, Math.min(N, i + L)))
      );
    }
    return parts;
  }
}

class WorkResult {
  int succCnt;
  int failCnt;

  synchronized void addSuccCnt(int count) {
    succCnt += count;
  }

  synchronized void addFailCnt(int count) {
    failCnt += count;
  }
}

class Task implements Runnable {
  List<String> subjects;
  WorkResult workResult;

  Task(List<String> subjects, WorkResult workResult) {
    this.subjects = subjects;
    this.workResult = workResult;
  }

  @Override
  public void run() {
    int succ = 0;
    int fail = 0;

    for(String accountId : subjects) {
      try {
        System.out.println("["+Thread.currentThread().getName()+"] "+accountId);
        succ++;
      } catch(Exception e) {
        fail++;
      }
    }

    workResult.addSuccCnt(succ);
    workResult.addFailCnt(fail);

    try {
      Thread.sleep(2000);
    } catch (InterruptedException ie) {}
  }

}
