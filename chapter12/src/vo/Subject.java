package vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Subject {
  private List<String> fileList;

  public Subject(List<String> fileList) {
    this.fileList = fileList;
  }

  public synchronized List<String> getSebject() {
    int fileSize = fileList.size();
    if(fileSize > 0) {
      int toIndex = fileSize<200 ? fileSize:200;

      List<String> subjects = new ArrayList<>(fileList.subList(0, toIndex));
      fileList = new ArrayList<>(fileList.subList(toIndex, fileSize));
    }

    return fileList;
  }

}
