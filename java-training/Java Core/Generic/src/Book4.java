public class Book4<K, V, I> extends Dictionary<K, V> {
    private I info; // khởi tạo thêm tham số generic

    public Book4(K key, V value) {
        super(key, value);
    }

    public Book4(K key, V value, I info) {
        super(key, value);
        this.info = info; // truyền Kiểu generic mới vào
    }

    public I getInfo() {
        return info;
    }

    public void setInfo(I info) {
        this.info = info;
    }

}
