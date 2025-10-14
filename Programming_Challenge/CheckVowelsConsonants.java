package Programming_Challenge;

import java.util.Scanner;

public class CheckVowelsConsonants {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String line = scanner.nextLine();
        scanner.close();

        int vowelCount = 0;
        int consonantCount = 0;

        // Normalize to lower‐case so we only need to check one case
        line = line.toLowerCase();

        for (char ch : line.toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                if (isVowel(ch)) {
                    vowelCount++;
                } else {
                    consonantCount++;
                }
            }
            // else: ignore non-letter characters
        }

        System.out.println("Vowels: " + vowelCount);
        System.out.println("Consonants: " + consonantCount);
    }

    private static boolean isVowel(char c) {
        return c=='a' || c=='e' || c=='i' || c=='o' || c=='u';
    }
}