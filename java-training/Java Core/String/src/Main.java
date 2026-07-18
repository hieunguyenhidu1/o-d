import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {
        String str1 = "ABC";
        String str2 = new String("ABC");
        str1.toUpperCase();
        System.out.println(str1);


        System.gc();
        long start=new GregorianCalendar().getTimeInMillis();
        long startMemory=Runtime.getRuntime().freeMemory();
        StringBuffer sb = new StringBuffer();
//        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<10000000; i++){
            sb.append(":").append(i);
        }
        long end=new GregorianCalendar().getTimeInMillis();
        long endMemory=Runtime.getRuntime().freeMemory();
        System.out.println("Time Taken:"+(end-start));
        System.out.println("Memory used:"+(startMemory-endMemory));

        //Cac phuong thuc string builder, buffer
//        append
//        insert
//        replace
//        delete
//        deleteCharAt
//        reverse
//        length
//        charAt
//          toString
//        ...

        //String pool, heap
        String first = "Baeldung";
        String second = "Baeldung";
        System.out.println(first == second); // True

        String third = new String("Baeldung");
        String fourth = new String("Baeldung");
        System.out.println(third == fourth); // False

        String fifth = "Baeldung";
        String sixth = new String("Baeldung");
        System.out.println(fifth == sixth); // False

    }
}