public class PhoneEntry {
    String name;
    String phone;

    PhoneEntry(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + " : " + phone;
    }
}
