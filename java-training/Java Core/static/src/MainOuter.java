public class MainOuter {
    public static void main(String[] args) {
        Outer.StaticNested obj = new Outer.StaticNested();
        obj.msg();

        Outer outer = new Outer();
        Outer.NormalClass obj2 = outer.new NormalClass();
        obj2.msg();
    }
}
