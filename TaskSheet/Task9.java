package TaskSheet;

import static java.lang.Math.multiplyExact;
import static java.lang.Math.floorDiv;
import static java.lang.Math.addExact;
import static java.lang.Math.subtractExact;

import java.util.Scanner;

public class Task9{

        public static int add(int a, int b) {
            return addExact(a, b) ;
        }

        public static int subtract(int a, int b) {
            return subtractExact(a, b) ;
        }

        public static int multiply(int a, int b) {
            return multiplyExact(a, b);
        }

        public static float divide(int a, int b) {
            if(b == 0) {
                System.out.println("Division: Cannot divide by zero");
                return  0;
            }
            return floorDiv(a, b);
        }

        public static void main(String[] args) {
            Scanner scan = new Scanner(System.in);
            System.out.print("Enter first number: ");
            int a = scan.nextInt();
            System.out.print("Enter second number: ");
            int b = scan.nextInt();
            System.out.println("Addition: " + add(a, b));
            System.out.println("Subtraction: " + subtract(a, b));
            System.out.println("Multiplication: " + multiply(a, b));
            System.out.println("Division: " + divide(a, b));

        }
    }

