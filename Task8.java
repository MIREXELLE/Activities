import java.util.*;

public class Task8 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int num1, num2, num3;

        System.out.print("Enter first number: ");
        num1 = scan.nextInt();
        System.out.print("Enter second number: ");
        num2 = scan.nextInt();
        System.out.print("Enter third number: ");
        num3 = scan.nextInt();

        int comulative1 = 0;
        int comulative2 = 0;
        int comulative3 = 0;
        for (int i = 0 ; i <= num1; i++) {
            comulative1 += i;
        }
        for (int i = 0 ; i <= num2; i++) {
            comulative2 += i;
        }
        for (int i = 0 ; i <= num3; i++) {
            comulative3 += i;
        }
        int sum =  comulative1 + comulative2 + comulative3;

        System.out.println("Comulative sum of the first number: " + comulative1);
        System.out.println("Comulative sum of the second number: " + comulative2);
        System.out.println("Comulative sum of the third number: " + comulative3);
        System.out.println("Sum of all: " + sum );
    }
}
