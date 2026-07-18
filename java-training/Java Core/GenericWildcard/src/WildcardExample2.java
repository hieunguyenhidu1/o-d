import java.util.Arrays;
import java.util.List;

public class WildcardExample2 {
    public static double sum(List<? extends Number> list) {
        double total = 0;
        for (Number num : list) {
            total += num.doubleValue();
        }
        return total;
    }

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);

        System.out.println(sum(integers)); // 6.0
        System.out.println(sum(doubles));  // 7.5
    }
}
