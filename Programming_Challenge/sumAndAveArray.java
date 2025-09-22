package Programming_Challenge;

public class sumAndAveArray {


    public static void main(String[] args) {
        double [] nums = {1,2,3,4,5,6};
        double sum = 0;

        for (double num : nums){
            sum += num;
        }
        double average = sum / nums.length;
        System.out.println("The sum is : " + sum);
        System.out.println("The average is : " + average);
    }
}
