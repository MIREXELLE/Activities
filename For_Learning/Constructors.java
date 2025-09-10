package For_Learning;

public class Constructors {
    public static void main(String[] args) {
        Bunny b1 = new Bunny("Bugs", "Gray");
        Bunny b2 = new Bunny("Lola", "White");
        Hamster h1 = new Hamster("Hammy", "Brown");
        System.out.println("The first name of the bunny ever: " + b1.name + ", color: " + b1.color);
        System.out.println("The second name of the bunny ever: " + b2.name + ", color: " + b2.color);
    }
    
}

class Bunny{
    String name;
    String color;
    Bunny(String name, String color){
        this.name = name;
        this.color = color;
        System.out.println("this is Bunny Constructor");
    }
}

class Hamster{
    String name;
    String color;
    Hamster(String name, String color){
        this.name = name;
        this.color = color;
        System.out.println("this is Hamster Constructor");
    }
}



