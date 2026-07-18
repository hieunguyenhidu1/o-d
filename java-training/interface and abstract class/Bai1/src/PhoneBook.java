import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class PhoneBook extends Phone{
    ArrayList<PhoneEntry> PhoneList = new ArrayList<>();

    @Override
    void insertPhone(String name, String phone) {
        for (PhoneEntry entry : PhoneList) {
            if (entry.name.equals(name)) {
                String[] phones = entry.phone.split(" : ");
                for (String p : phones) {
                    if (p.trim().equals(phone)) {
                        return;
                    }
                }
                entry.phone += " : " + phone;
                return;
            }
        }
        PhoneList.add(new PhoneEntry(name, phone));
    }

    @Override
    void removePhone(String name) {
        PhoneList.removeIf(entry -> entry.name.equals(name));
    }

    @Override
    void updatePhone(String name, String newphone) {
        for (PhoneEntry entry: PhoneList){
            if(entry.name.equals(name)){
                entry.phone = newphone;
                return;
            }
        }
        System.out.println("Ten khong ton tai");
    }

    @Override
    void searchPhone(String name) {
        for (PhoneEntry entry : PhoneList) {
            if (entry.name.equals(name)) {
                System.out.println(entry);
                return;
            }
        }
        System.out.println("Không tìm thấy.");
    }

    @Override
    void sort() {
        PhoneList.sort(Comparator.comparing(entry -> entry.name));
        System.out.println("Danh bạ sau khi sắp xếp:");
        for (PhoneEntry entry : PhoneList) {
            System.out.println(entry);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PhoneBook phoneBook = new PhoneBook();
        int choice;
        do{
            System.out.println("\n=== PHONEBOOK MANAGEMENT SYSTEM ===");
            System.out.println("1. Insert Phone");
            System.out.println("2. Remove Phone");
            System.out.println("3. Update Phone");
            System.out.println("4. Search Phone");
            System.out.println("5. Sort");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number (1-6): ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            String name, phone;

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    phone = scanner.nextLine();
                    phoneBook.insertPhone(name, phone);
                    System.out.println("Inserted successfully.");
                    break;

                case 2:
                    System.out.print("Enter name to remove: ");
                    name = scanner.nextLine();
                    phoneBook.removePhone(name);
                    System.out.println("Removed (if existed).");
                    break;

                case 3:
                    System.out.print("Enter name to update: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new phone: ");
                    phone = scanner.nextLine();
                    phoneBook.updatePhone(name, phone);
                    break;

                case 4:
                    System.out.print("Enter name to search: ");
                    name = scanner.nextLine();
                    phoneBook.searchPhone(name);
                    break;

                case 5:
                    phoneBook.sort();
                    break;

                case 6:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please select from 1 to 6.");
            }
        }while(choice != 6);

        scanner.close();
    }
}