public class CarExample {
  public static void main(String[] args) {
    Car myCar = new Car();
    System.out.println("[myCar]");
    System.out.println(myCar.toString());
    System.out.println();

    Car car1 = new Car("자가용");
    System.out.println("[car1]");
    System.out.println(car1.toString());
    System.out.println();

    Car car2 = new Car("자가용", "빨강");
    System.out.println("[car2]");
    System.out.println(car2.toString());
    System.out.println();

    Car car3 = new Car("택시", "검정", 200);
    System.out.println("[car3]");
    System.out.println(car3.toString());

  }
}
