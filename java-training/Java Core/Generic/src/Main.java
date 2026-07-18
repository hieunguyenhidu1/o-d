import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();

        //Tao class generic vowi kieu tham so generic
        Dictionary<String, String> d = new Dictionary<String, String>("Study", "hoc");
        String english = d.getKey();
        String vietnamese = d.getValue();
        System.out.println(english + ": " + vietnamese); //Ouput: Study: hoc

        //Thua ke Lop Generic
        Book l = new Book("Study", "hoc");
        String english1 = l.getKey();
        String vietnamese1 = l.getValue();
        System.out.println(english1 + ": " + vietnamese1); // Ouput: Study: hoc

        Book1<String, String> l2 = new Book1<String, String>("Study", "hoc");
        String english2 = l2.getKey();
        String vietnamese2 = l2.getValue();
        System.out.println(english2 + ": " + vietnamese2); // Ouput: Study: hoc

        Book3<String> l3 = new Book3<String>("Study", "hoc");
        String english3 = l3.getKey();
        String vietnamese3 = l3.getValue();
        System.out.println(english3 + ": " + vietnamese3); // Ouput: Study: hoc


        Book4<String, String, Integer> l4 = new Book4<String, String, Integer>("Study", "hoc", 123);
        String english4 = l4.getKey();
        String vietnamese4 = l4.getValue();
        int quanity = l4.getInfo();
        System.out.println(english4 + ": " + vietnamese4 + "\nQuantity: " + quanity);
        // Ouput:
        // Study: hoc
        // Quanity: 123

        // Khoi tao phuong thuc generic
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("Codelearn.io");
        list1.add("Google.com");
        list1.add("Azure");
        list1.add("GCF");
        System.out.println(Store.getFirstElement(list1));
        // output: Codelearn.io

    }
}