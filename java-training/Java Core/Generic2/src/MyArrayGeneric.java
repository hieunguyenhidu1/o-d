public class MyArrayGeneric <T>{
    private T[] array; // khởi tạo mảng

    public MyArrayGeneric(T[] array) {
        this.array = array;
    }

    public T[] getArray() {
        return array;
    }

    public T getFirstElement() {
        if (this.array.length == 0) {
            return null;
        }
        return this.array[0];
    }

    public T getLastElement() {
        if (this.array.length == 0) {
            return null;
        }
        return this.array[this.array.length - 1];
    }
}
