package Programming_Challenge;

public class stringMethods {
    public static void main(String[] args) {

        String length = "Mirexelle";

        System.out.println(length.length());
        System.out.println(length.toUpperCase());
        System.out.println(length.toLowerCase());
        System.out.println(length.charAt(0));
        System.out.println(length.charAt(length.length() - 1));
        System.out.println(length.substring(1,6));
    }
}
