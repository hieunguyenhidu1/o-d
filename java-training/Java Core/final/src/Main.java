import java.util.ArrayList;
import java.util.List;

public class Main extends Person2{
//    @Override
//    public void print(){
//        System.out.println("HELLO main");
//    }

    public static void main(String[] args) {
        final List<String> list = new ArrayList<>();
        list.add("Hello"); //  Được phép - thay đổi nội dung
//        list = new ArrayList<>(); // Không được - không thể gán lại biến list
        Person2 a = new Person2();
        a.print();
        Person b = new Person();
    }
}