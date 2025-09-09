package For_Learning;

public class varargs {
    public int sumOfAllNums(int... y){
        int sum = 0;
        for (int num:y)
            sum += num;
        return sum;
    }
    public static void main(String[] args) {
        varargs vl = new varargs();
        System.out.println(vl.sumOfAllNums(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
    }
}
