import java.util.Arrays;
import java.util.Collection;

public class WildcardExample1 {
    public static void printCollection(Collection<?> coll) {
        for (Object item : coll) {
            System.out.println(item);
        }
    }

    public static void main(String[] args) {
        Collection<String> strings = Arrays.asList("A", "B", "C");
        Collection<Integer> integers = Arrays.asList(1, 2, 3);

        printCollection(strings);  // OK
        printCollection(integers); // OK
    }
}
