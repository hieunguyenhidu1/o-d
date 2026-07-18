import java.util.LinkedList;
import java.util.List;

public class StaticBlockDemo {
    static int count;

    // Static block
    static {
        System.out.println("Static block is called!");
        count = 10; // gán giá trị ban đầu
    }

    public StaticBlockDemo() {
        System.out.println("Constructor is called!");
    }

    public static void main(String[] args) {
        System.out.println("Main method is running!");
        System.out.println("Count = " + count);

        StaticBlockDemo obj = new StaticBlockDemo();
    }
}
