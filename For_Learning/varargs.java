package For_Learning;

public class varargs {
    public static float marsGravity = 3.71f;
    public int sumOfAllNums(String name,int p, int... y){
        int sum = 0;
        for (int num:y)
            sum += num;
        return sum;
    }
    public static void main(String[] args) {
        varargs vl = new varargs();
        varargs vl2 = new varargs();
        int [] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(vl.sumOfAllNums("Piol", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(vl.sumOfAllNums("Piol", 1, a));
        System.out.println(getEarthGravity());
        System.out.println(marsGravity);
    }
}
