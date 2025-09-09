package For_Learning;

public class Array {
    
    int [] numbers = new int [3];
    int [] numbers2 = new int [] {1, 2, 3};
    int [] numbers3 = {1, 2, 3};
    int [] id, types;   
    int id2[], types2;
   

    public static void main(String[] args) {
        String [] bugs = {"cricket", "beetle", "ladybug"};
        String [] alias = bugs;
        System.out.println(bugs.equals(alias)); // true
        System.out.println(bugs.toString()); // reference

        String [] strings = {"stringValue"};
        Object [] objects = strings;
        String [] againStrings = (String[]) objects;
        String[] mammals = {"monkey", "chimp", "donkey"};
        System.out.println(mammals.length); // 3
        System.out.println(mammals[0]); // monkey
        System.out.println(mammals[1]); // chimp
        System.out.println(mammals[2]); // donkey
    
    }
}

