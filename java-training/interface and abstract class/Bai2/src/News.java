import java.util.ArrayList;
import java.util.Scanner;

public class News implements INews{
    private int ID;
    private String Title;
    private String PublishDate;
    private String Author;
    private String Content;
    private float AverageRate;
    private int[] RateList = new int[3];


    public int getID() {
        return ID;
    }

    public String getTitle() {
        return Title;
    }

    public String getPublishDate() {
        return PublishDate;
    }

    public String getAuthor() {
        return Author;
    }

    public String getContent() {
        return Content;
    }

    public float getAverage() {
        return AverageRate;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setPublishDate(String publishDate) {
        PublishDate = publishDate;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int[] getRateList() {
        return RateList;
    }

    public void setRateList(int[] rateList) {
        RateList = rateList;
    }

    @Override
    public void Display() {
        System.out.println("Title" + ": " + this.Title);
        System.out.println("PublishDate" + ": " + this.PublishDate);
        System.out.println("Author" + ": " + this.Author);
        System.out.println("Content" + ": " + this.Content);
        System.out.println("AverageRate " + ": " + this.AverageRate );
    }

    public void Calculate(){
        int sum = 0;
        for (int rate : RateList) {
            sum += rate;
        }
        this.AverageRate = sum / 3.0f;
    }

    public static void main(String[] args) {
        ArrayList<News> newsList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n=== NEWS MANAGEMENT SYSTEM ===");
            System.out.println("1. Insert news");
            System.out.println("2. View list news");
            System.out.println("3. Average rate");
            System.out.println("4. Exit");
            System.out.print("Choose (1-4): ");

            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a number (1-4): ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    News news = new News();
                    System.out.print("Enter title: ");
                    news.setTitle(scanner.nextLine());

                    System.out.print("Enter publish date: ");
                    news.setPublishDate(scanner.nextLine());

                    System.out.print("Enter author: ");
                    news.setAuthor(scanner.nextLine());

                    System.out.print("Enter content: ");
                    news.setContent(scanner.nextLine());
                int[] rateList = new int[3];
                for (int i = 0; i < 3; i++) {
                    System.out.print("Enter rate " + (i + 1) + ": ");
                    while (!scanner.hasNextInt()) {
                        System.out.print("Enter a valid number: ");
                        scanner.next();
                    }
                    rateList[i] = scanner.nextInt();
                }
                scanner.nextLine(); // bỏ newline

                news.setRateList(rateList);
                newsList.add(news);
                System.out.println("News added successfully.");
                break;

                case 2:
                if (newsList.isEmpty()) {
                    System.out.println("No news to show.");
                } else {
                    for (News n : newsList) {
                        n.Display();
                    }
                }
                break;

                case 3:
                if (newsList.isEmpty()) {
                    System.out.println("No news to calculate.");
                } else {
                    for (News n : newsList) {
                        n.Calculate();
                        n.Display();
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