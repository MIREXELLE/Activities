package TaskSheet;

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

        int cumulative1 = 0;
        int cumulative2 = 0;
        int cumulative3 = 0;

        for (int i = 0 ; i <= num1; i++) {
            cumulative1 += i;
        }
        for (int i = 0 ; i <= num2; i++) {
            cumulative2 += i;
        }
        for (int i = 0 ; i <= num3; i++) {
            cumulative3 += i;
        }
        int sum =  cumulative1 + cumulative2 + cumulative3;

        System.out.println("Cumulative sum of the first number: " + cumulative1);
        System.out.println("Cumulative sum of the second number: " + cumulative2);
        System.out.println("Cumulative sum of the third number: " + cumulative3);
        System.out.println("Sum of all: " + sum );
    }
}
