package Programming_Challenge;
import java.util.Scanner;

public class numToMonth {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter month number: ");
        int month = scan.nextInt();

        switch (month) {
            case 1 -> System.out.println("Month: January \nNumber of Days: 31");
            case 2 -> System.out.println("Month: February \nNumber of Days: 28");
            case 3 -> System.out.println("Month: March \nNumber of Days: 31");
            case 4 -> System.out.println("Month: April \nNumber of Days: 30");
            case 5 -> System.out.println("Month: May \nNumber of Days: 31");
            case 6 -> System.out.println("Month: June \nNumber of Days: 30");
            case 7 -> System.out.println("Month: July \nNumber of Days: 31");
            case 8 -> System.out.println("Month: August \nNumber of Days: 31");
            case 9 -> System.out.println("Month: September \nNumber of Days: 30");
            case 10 -> System.out.println("Month: October \nNumber of Days: 31");
            case 11 -> System.out.println("Month: November \nNumber of Days: 30");
            case 12 -> System.out.println("Month: December \nNumber of Days: 31");
            default -> System.out.println("Invalid month number.");
        }
    }

}
