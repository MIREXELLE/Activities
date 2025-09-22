package Programming_Challenge;

public class stringBuilder {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("Mirexelles");

        System.out.println(sb.length());
        System.out.println(sb.charAt(0));
        System.out.println(sb.charAt(sb.length()-1));
        System.out.println(sb.length());
        System.out.println(sb.substring(3, 6));
        System.out.println(sb.append("123"));
        System.out.println(sb.insert(4, "xyz"));
        System.out.println(sb.delete(2, 4));
        System.out.println(sb.deleteCharAt(8));
        System.out.println(sb.reverse());
    }
}
