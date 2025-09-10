package For_Learning;

public class MouseConstructor {
    private int numTeeth;
    private int numWhiskers;
    private int weight;
    
    // Constructor with 1 parameter
    public MouseConstructor(int weight) {
        this(weight, 16); // calls constructor with 2 parameters
    }
    
    // Constructor with 2 parameters
    public MouseConstructor(int weight, int numTeeth) {
        this(weight, numTeeth, 6); // calls constructor with 3 parameters
    }
    
    // Constructor with 3 parameters
    public MouseConstructor(int weight, int numTeeth, int numWhiskers) {
        this.weight = weight;
        this.numTeeth = numTeeth;
        this.numWhiskers = numWhiskers;
    }
    
    public void print() {
        System.out.println("weight = " + weight + " numTeeth = " + numTeeth + " numWhiskers = " + numWhiskers);
    }
    
    public static void main(String[] args) {
        MouseConstructor mouse = new MouseConstructor(15);
        mouse.print();
    }
}
