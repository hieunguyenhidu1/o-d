import java.util.ArrayList;

public class Store {
    // Arraylist sẽ chứa các phần tử E
    // ta sẽ trả về phần tử đầu tiền trong arr khi được gọi
    public static <E> E getFirstElement(ArrayList<E> arr) {
        if (arr.isEmpty())
            return null;
        E first = arr.get(0);
        return first;
    }
}
