public class Control_FLow_Statements {
    public static void main(String[] args) {
        // Create an int variable and name it check_number with an initial value of 10
        int check_number = 10;
        
        // Create a String variable and name it message
        String message;
        
        // Use check_number as a condition on how many loops the program can print
        for (int i = 1; i <= check_number; i++) {
            // Create a loop that will check and print each number if odd or even numbers
            message = (i % 2 == 0) ? i + " is even number" : i + " is odd number";
            
            // Print message value
            System.out.println(message);
        }
    }
}
