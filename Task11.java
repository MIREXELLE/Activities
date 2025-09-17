class book{
    String title;
    String author;
    int yearPublished;
    double price;

    public book (String title, String author, int yearPublished, double price) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.price = price;
    }
    public String toString(){
        return "Title: \"" + title + "\"\nAuthor: \"" + author + "\"\nYear Published: " + yearPublished + "\nPrice: " + price;
    }
}
public class Task11 {
    public static void main(String[] args) {
        book b = new book("Romio and Jolyet","Maxwell",2025,150);
        book b2 = new book("Slindir Min", "Mirexsele", 2025, 120);
        System.out.println(b);
        System.out.println("\n");
        System.out.println(b2);

    }
}
