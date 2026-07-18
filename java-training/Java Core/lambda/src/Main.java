import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class Main {
    public static void main(String[] args) {
        Hello s1 = () -> {
            return "Hello Lambda.";
        };
        System.out.println(s1.sayHello());

        // 1️⃣ Predicate: kiểm tra chuỗi có trống không
        Predicate<String> isEmpty = s -> s.isEmpty();
        System.out.println("Predicate: " + isEmpty.test("")); // true

        // 2️⃣ Function: chuyển String thành Integer (độ dài)
        Function<String, Integer> strLength = s -> s.length();
        System.out.println("Function: " + strLength.apply("Hello")); // 5

        // 3️⃣ Consumer: in ra màn hình
        Consumer<String> printer = s -> System.out.println("Consumer: " + s);
        printer.accept("Xin chào!");

        // 4️⃣ Supplier: tạo random số double
        Supplier<Double> randomNumber = () -> Math.random();
        System.out.println("Supplier: " + randomNumber.get());

        // 5️⃣ BiPredicate: kiểm tra 2 input
        BiPredicate<String, Integer> hasLength = (s, len) -> s.length() == len;
        System.out.println("BiPredicate: " + hasLength.test("Java", 4)); // true

        // 6️⃣ BiFunction: cộng 2 số
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("BiFunction: " + add.apply(2, 3)); // 5

        // 7️⃣ UnaryOperator: đảo chuỗi
        UnaryOperator<String> reverse = s -> new StringBuilder(s).reverse().toString();
        System.out.println("UnaryOperator: " + reverse.apply("abc")); // cba

        // 8️⃣ BinaryOperator: lấy max của 2 số
        BinaryOperator<Integer> max = (a, b) -> a > b ? a : b;
        System.out.println("BinaryOperator: " + max.apply(10, 20)); // 20

        // 9️⃣ Method Reference: viết gọn lambda
        List<String> names = Arrays.asList("Tom", "Jerry", "Spike");
        names.forEach(x-> System.out.println(x));
        names.forEach(System.out::println); // Method Reference

        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));

        // Sắp xếp theo tuổi tăng dần (dùng lambda)
        people.sort((p1, p2) -> Integer.compare(p1.age, p2.age));

        System.out.println("Danh sách sau khi sắp xếp:");
        for (Person p : people) {
            System.out.println(p);
        }
    }
}