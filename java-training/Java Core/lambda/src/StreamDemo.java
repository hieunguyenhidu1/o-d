import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<String> items = new ArrayList<String>();
        items.add ( "một");
        items.add ( "hai");
        items.add ( "ba");
        Stream<String>  stream= items.stream();
        stream.forEach(System.out::println);

        List<String> names = Arrays.asList("An", "Binh", "Anh","Cuong");
        List<String> filtered1 = names.stream()
                .filter(name -> name.startsWith("A"))
                .toList();
        filtered1.forEach(System.out::println);

        List<String> filtered2 = names.stream()
                .map(String::toUpperCase)
                .toList();
        filtered2.forEach(System.out::println);

        long filered3 = names.stream()
                .filter(item-> item.startsWith("t"))
                .count();
        System.out.println(filered3);

        String filtered4 = names.stream()
                .min(Comparator.comparing(item->item.length()))
                .get();
        System.out.println(filtered4);

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        int sum = list.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);
    }
}
