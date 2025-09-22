package Programming_Challenge;

import java.util.Scanner;
public class sumOfInputs {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRunning = true;
        System.out.println("Sum of All integer");
        System.out.println("Enter 0 to stop loop");
        int sum = 0;
        while (isRunning) {
            System.out.print("Enter a Number: ");
            int num = in.nextInt();
            if (num == 0) {
             isRunning = false;
            }
            sum += num;
        }
        System.out.println("Sum of All integer Inputs: " + sum);

        in.close();
    }
}