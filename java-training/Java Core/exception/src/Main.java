import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

//    public static int getPlayerScore(String playerFile)
//            throws FileNotFoundException {
//
//        Scanner contents = new Scanner(new File(playerFile));
//        return Integer.parseInt(contents.nextLine());
//    }

//    public static int getPlayerScore(String playerFile) {
//        Scanner contents = null;
//        try {
//            contents = new Scanner(new File(playerFile));
//            return Integer.parseInt(contents.nextLine());
//        } catch (FileNotFoundException e) {
////            throw new IllegalArgumentException("File not found");
//            System.out.println("File not found! Return default score 0.");
//            return 0;
//        } finally {
//            if (contents != null) {
//                contents.close();
//            }
//            System.out.println("Scanner closed!");
//
//            try {
//                if (contents != null) {
//                    contents.close();
//                }
//            } catch (IOException io) {
//                Logger.error("Couldn't close the reader!", io);
//            }
//        }
//    }

//    try with resource
//    public static int getPlayerScore(String playerFile) {
//        try (Scanner contents = new Scanner(new File(playerFile))) {
//            return Integer.parseInt(contents.nextLine());
//        } catch (FileNotFoundException e ) {
//            logger.warning("File not found, resetting score.");
//            return 0;
//        }
//    }

//    Multiple catch Blocks
//    public static int getPlayerScore(String playerFile) {
//        try (Scanner contents = new Scanner(new File(playerFile))) {
//            return Integer.parseInt(contents.nextLine());
//        } catch (IOException e) {
//            logger.warning("Player file wouldn't load!");
//            return 0;
//        } catch (NumberFormatException e) {
//            logger.warning("Player file was corrupted!");
//            return 0;
//        }
//    }

//    Union catch Blocks
    public static int getPlayerScore(String playerFile) {
        try (Scanner contents = new Scanner(new File(playerFile))) {
            return Integer.parseInt(contents.nextLine());
        } catch (IOException | NumberFormatException e) {
            logger.warning("Failed to load score!");
            return 0;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        int score = getPlayerScore("player.txt");
        System.out.println("Score: " + score);

    }
}