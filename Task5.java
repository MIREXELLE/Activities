import java.util.Scanner;

public class Task5 {
    public static int[] numbers = new int[3];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < numbers.length; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }
        
        // Check if all numbers are equal
        if (numbers[0] == numbers[1] && numbers[1] == numbers[2]) {
            System.out.println("All numbers are equal");
        } else {
            // Find the largest number
            int largest = Math.max(Math.max(numbers[0], numbers[1]), numbers[2]);
            System.out.println("The largest number is: " + largest);
        }
        scanner.close();
    }
}
