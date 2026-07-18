public class Main {
    public static void change(int x){
        x = 100;
    }

    public static void change2(Foo a1, Foo b1){
        a1.num++;
        b1 = new Foo(1);
        b1.num++;
    }

    public static void main(String[] args) {
        int x = 10;
        change(x);
        System.out.println(x);

        Foo a = new Foo(1);
        Foo b = new Foo(1);
        change2(a,b);
        System.out.println(a.num + " " + b.num);
    }
}