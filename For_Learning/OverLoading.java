package For_Learning;

public class OverLoading {
    public void fly(int numMiles){
    System.out.println();
    }
    public void fly(short numFeet){
        System.out.println("short:");

    }
    public boolean fly(){
        return false;
    }
void fly(int numMiles, short numFeet){
    System.out.println("int, short");
}

public void fly(short numFeet, int numMiles) throws Exception{

}
public static void main(String[] args) {

    OverLoading oll = new OverLoading();
    short s = 1;

    oll.fly(1);
    oll.fly(s);
    System.out.println(oll.fly());
    oll.fly(1, (short)1);
    try{
        oll.fly((short)1, 1);
    }
    catch(Exception e){
        System.out.println("oll.fly((short)1, 1) has been catched");
    }
}
}
