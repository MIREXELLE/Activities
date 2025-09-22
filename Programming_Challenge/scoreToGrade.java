package Programming_Challenge;
import java.util.Scanner;
public class scoreToGrade {
    public static void main(String[] args) {

    Scanner scan = new Scanner(System.in);

    System.out.print("Enter score: ");
    double score = scan.nextInt();

    if (score >= 90){
        System.out.println("Your Grade is A");
    } else if (score >= 80){
        System.out.println("Your Grade is B");
    } else if (score >= 70){
        System.out.println("Your Grade is C");
    } else if (score >= 60){
        System.out.println("Your Grade is D");
    } else if (score < 60) {
        System.out.println("Your Grade is F");
    } else {
        System.out.println("Invalid grade");
    }
}
}
