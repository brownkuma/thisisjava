public class Car {

  String company = "현대자동차";
  String model = "그랜저";
  String color = "검정";
  int maxSpeed = 350;
  int speed;

  Car() {}

  Car(String model) {
    this(model, "은색", 250);
  }

  Car(String model, String color) {
    this(model, color, 250);
  }

  Car(String model, String color, int maxSpeed) {
    this.model = model;
    this.color = color;
    this.maxSpeed = maxSpeed;
  }

  @Override
  public String toString() {
    return "제작회사: " +company
        +"\n모델명: " +model
        +"\n색깔: " +color
        +"\n최고속도: " +maxSpeed
        +"\n현재속도: " +speed
        ;
  }

}
