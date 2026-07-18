public class Main extends Car{
    public Main(String name, String engine) {
        super(name, engine);
    }

    public static void main(String[] args) {
        Car xe1 = new Car("Jaguar", "V8");
        System.out.println(Car.numberOfCars);
        Car xe2 = new Car("Bugatti", "W16");

        System.out.println(Car.numberOfCars);
    }
}