public class MyGeneric<T> {
    private T obj;
    public MyGeneric(Class<T> classObject) throws InstantiationException, IllegalAccessException {
        // lấy tên Class và gán nó vào đối tượng obj
        this.obj = (T) classObject.newInstance();

    }
    public T getObj() {
        return obj; // trả về obj
    }
}
