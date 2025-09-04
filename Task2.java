public class Task2 {
    public static void main(String[] args) {
        // Primitives: byte, short, int, float, char, boolean (excluding long and double)
        byte b = 3;
        short s = 1;
        int i = 1;
        float f = 2.0f;
        char c1 = 'H';
        char c4 = 'o';
        char c5 = 'w';
        char c6 = 'r';
        char c7 = 'd';
        boolean bool = true;
        //String: H3110 w0r1d 2.0 true
        String output = "" + c1 + b + s + i + c4 + " " + c5 + 0 + c6 + i + c7 + " " + f + " " + bool;
        System.out.println(output);
    }
}
