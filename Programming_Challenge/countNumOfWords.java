package Programming_Challenge;

import java.util.Scanner;

public class countNumOfWords {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter sentence: ");
        String input = in.nextLine();
        String[] words = input.trim().split("\\s+");
        System.out.println("Number of words: " + words.length);

    }
}
