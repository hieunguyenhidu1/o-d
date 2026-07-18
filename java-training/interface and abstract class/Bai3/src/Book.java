import java.util.ArrayList;
import java.util.Scanner;

public class Book implements IBook{
    private static int nextId = 1;
    private int id;
    private String name;
    private String publishDate;
    private String author;
    private String language;
    private float averagePrice;
    private int[] PriceList = new int[5];

    public Book() {
        this.id = nextId++;
    }

    public void setPriceList(int[] priceList) {
        PriceList = priceList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public float getAveragePrice() {
        return averagePrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void calculate(){
        int sum = 0;
        for(int price: PriceList){
            sum+=price;
        }
        this.averagePrice= sum/5.0f;
    }

    @Override
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("name"+ ": " + this.name);
        System.out.println("publishDate"+ ": " + this.publishDate);
        System.out.println("author"+ ": " + this.author);
        System.out.println("language "+ ": " + this.language );
        System.out.println("averagePrice "+ ": " + this.averagePrice );
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Book> bookList = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n=== BOOKS MANAGEMENT SYSTEM ===");
            System.out.println("1. Insert new book");
            System.out.println("2. View list of books");
            System.out.println("3. Average Price");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a number (1-4): ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    Book book = new Book();
                    System.out.print("Enter book name: ");
                    book.setName(scanner.nextLine());

                    System.out.print("Enter publish date: ");
                    book.setPublishDate(scanner.nextLine());

                    System.out.print("Enter author: ");
                    book.setAuthor(scanner.nextLine());

                    System.out.print("Enter language: ");
                    book.setLanguage(scanner.nextLine());

                    int[] prices = new int[5];
                    for (int i = 0; i < 5; i++) {
                        System.out.print("Enter price " + (i + 1) + ": ");
                        while (!scanner.hasNextInt()) {
                            System.out.print("Enter a valid number: ");
                            scanner.next();
                        }
                        prices[i] = scanner.nextInt();
                    }
                    scanner.nextLine(); // consume newline
                    book.setPriceList(prices);
                    bookList.add(book);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    if (bookList.isEmpty()) {
                        System.out.println("No books available.");
                    } else {
                        for (Book b : bookList) {
                            b.display();
                        }
                    }
                    break;

                case 3:
                    if (bookList.isEmpty()) {
                        System.out.println("No books to calculate.");
                    } else {
                        for (Book b : bookList) {
                            b.calculate();
                            b.display();
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose from 1 to 4.");
            }

        } while (choice != 4);

        scanner.close();
    }


}