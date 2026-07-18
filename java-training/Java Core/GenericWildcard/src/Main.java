import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Banana", "Apple", "Orange");

        // Comparator<? super String>: có thể là Comparator<String> hoặc Comparator<Object>
        Comparator<? super String> comp = new MyStringComparator();

        // Sử dụng Collections.sort, chấp nhận Comparator<? super T>
        Collections.sort(names, comp);

        System.out.println(names);  // [Apple, Banana, Orange]
    }

    static class MyStringComparator implements Comparator<Object> {
        @Override
        public int compare(Object o1, Object o2) {
            // Ép kiểu về String để so sánh
            return o1.toString().compareTo(o2.toString());
        }
    }
}