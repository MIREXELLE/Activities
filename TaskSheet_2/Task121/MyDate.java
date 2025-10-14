package TaskSheet_2.Task121;

public class MyDate {
    public static void main(String[] args) {
        DateTask date1 = new DateTask();
        DateTask date2 = new DateTask();
        
        // Set date1 to January 1st, 1978
        date1.setDate(1, 1, 1978);
        
        // Set date2 to September 21st, 1984
        date2.setDate(9, 21, 1984);
        
        System.out.println(date1.toString());
        System.out.println(date2.toString());
        
        // display leapyears
        DateTask.leapYears();
    }
}