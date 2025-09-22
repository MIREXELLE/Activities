package Programming_Challenge;
import java.util.Scanner;

public class numToDay {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter day of the week: ");
        int day = in.nextInt();

        switch (day) {
            case 1 -> System.out.println("Day: Monday");
            case 2 -> System.out.println("Day: Tuesday");
            case 3 -> System.out.println("Day: Wednesday");
            case 4 -> System.out.println("Day: Thursday");
            case 5 -> System.out.println("Day: Friday");
            case 6 -> System.out.println("Day: Saturday");
            case 7 -> System.out.println("Day: Sunday");
            default -> System.out.println("Invalid input");

        }
    }

}
