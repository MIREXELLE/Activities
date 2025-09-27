interface Shape {
    double calculateArea();
    double calculatePerimeter();
}
// AbstractShape abstract class
abstract class AbstractShape implements Shape {
    protected String color;
    protected double length;
    protected double width;

    public AbstractShape(String color, double length, double width) {
        this.color = color;
        this.length = length;
        this.width = width;
    }
    //implementations for calculateArea and calculatePerimeter
    @Override
    public abstract double calculateArea();
    @Override
    public abstract double calculatePerimeter();
}
// Circle class
    class Circle extends AbstractShape {
    private final double radius;

    public Circle(String color, double radius){
        super(color, 0,0);
        this.radius = radius;

    }
    @Override
    public double calculateArea(){
        return Math.PI * radius * radius;
    }
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    void displayDetails(){
        System.out.println("\n-----Circle Details-----\n");
        System.out.println("The color of circle is: " + this.color);
        System.out.println("The area of circle is: " + this.calculateArea());
        System.out.println("The perimeter of circle is: " + this.calculatePerimeter());
    }
}

//Rectangle Class
    class Rectangle extends AbstractShape {

    public Rectangle(String color){super(color,20,10);}

    @Override
    public double calculateArea(){
        return length * width;
    }
    @Override
    public double calculatePerimeter() {
        return 2 * (length * width);
    }
    void displayDetails(){
        System.out.println("\n-----Rectangle Details-----\n");
        System.out.println("The color of rectangle is: " + this.color);
        System.out.println("The area of rectangle is: " + this.calculateArea());
        System.out.println("The perimeter of rectangle is: " + this.calculatePerimeter());
    }
}

//Main class
public class Task15 {
    public static void main(String[] args) {
        Circle circle = new Circle("red", 5);
        Rectangle rectangle = new Rectangle("Red");
        circle.displayDetails();
        System.out.println();
        rectangle.displayDetails();
    }
}
