public class Main {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        // khởi tạo đối tượng Codelearn trên myObj
        MyGeneric<Codelearn> myObj = new MyGeneric<Codelearn>(Codelearn.class);
        // bây giờ myObj đã có đối tượng Codelearn
        // t chỉ cần gọi đối tượng đó ra bằng method getObj()
        myObj.getObj().sayHi();

//        T obj = new T(); // Khong khoi tao duoc
//        T obj[] = new T[5]; // error
//        T obj[];

        //Khoi tao mang
        String[] names = new String[] { "Dat", "Khoa", "Tin" };

        // truyền array names vào trong MyArrayGeneric để gắn mảng vào trong nó
        MyArrayGeneric<String> myArrayGeneric = new MyArrayGeneric<String>(names);
        // ta có thể dổi thành các kiểu khác như Double, Integer, ...

        System.out.println("First name: " + myArrayGeneric.getFirstElement());
        System.out.println("Last name: " + myArrayGeneric.getLastElement());

    }
}