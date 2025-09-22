package Programming_Challenge;

import java.util.Scanner;

public class oddOrEven {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter a number: ");
        int num = in.nextInt();
        if(num == 0){
            System.out.println("It's a zero");
        } else {
            System.out.println("Odd or even: " + (num % 2 == 0? "Even" : "Odd"));
            System.out.println("Positive or negative: " + (num > 0?"Positive" : "Negative"));
        }
    }
}