package TaskSheet;

interface Animal {
    boolean feed(boolean timeToEat);
    void groom();
    void pet();
}
class Gorilla implements Animal {
    @Override
    public boolean feed(boolean timeToEat) {
        // put gorilla food into cage
        if(timeToEat) {
            System.out.println("The gorilla is eating");
            return true;
        } else {
            System.out.println("The gorilla is not hungry right now");
            return false;
        }
    }
    @Override
    public void groom() {
        // lather, rinse, repeat
        System.out.println("The gorilla is grooming itself.");
    }
    @Override
    public void pet() {
        // pet at your own risk
        System.out.println("Careful! Petting gorilla......");
    }
}

public class Task16 {
    public static void main(String[] args) {
        Gorilla monke = new Gorilla();
        System.out.println("Feeding: " + monke.feed(true));
        monke.groom();
        monke.pet();
    }
}