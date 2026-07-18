public class Outer {
    static int data = 30;
    private int data2 = 20;

    static class StaticNested {
        void msg() {
            System.out.println("Data is: " + data);
        }
    }

    class NormalClass{
        void msg(){
            System.out.println("Data is: "+ data2);
        }
    }
}
