package Programming_Challenge;

public class multiplicationTable {

    public static void main(String[] args) {
        System.out.println("\n\t\tMultiplication Table\n");

        for(int i = 1; i <= 10; ++i){
            for (int b=1; b <=10; b++){
                int product = i * b;
                System.out.print(product + "\t");
            }
            System.out.println();
        }
    }
}
