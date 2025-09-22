public class Task13 {

     class vehicle{
        protected String make;
        protected String model;
        protected int year;

        public vehicle(String make, String model, int year){
            this.make = make;
            this.model = model;
            this.year = year;
        }
    }
    class car extends vehicle{
         int numberOfDoors;

         public car(String make, String model, int year, int numberOfDoors){
             super (make, model, year);
             this.numberOfDoors = numberOfDoors;
         }

        public static void main(String[] args) {

        }
    }

}
