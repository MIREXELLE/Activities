
    class vehicle{
         String make;
         String model;
         int year;

         vehicle(String make, String model, int year){
            this.make = make;
            this.model = model;
            this.year = year;
        }
    }
    class Car extends vehicle{
         int numberOfDoors;

         Car(String make, String model, int year, int numberOfDoors){
             super (make, model, year);
             this.numberOfDoors = numberOfDoors;
         }

         void displayDetails(){
            System.out.println("Car Details: ");
            System.out.println("Make: " + this.make);
            System.out.println("Model: " + this.model);
            System.out.println("Year: " + this.year);
            System.out.println("Number of Doors: " + this.numberOfDoors);
        }
    }
    public class Task13 {
        public static void main(String[] args) {
            Car mycar = new Car("Mustang", "Lamborgini", 2001, 4);
            mycar.displayDetails();
        }
    }
