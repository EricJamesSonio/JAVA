package abstraction;

public class Main {
    public static void main(String[] args) {
        Book english = new Book("English", 201, "Michael", "English Language");
        english.getDetails();

        Magazine calendar = new Magazine("Calendar", 203, "Philps", "SPG");
        calendar.getDetails();

        Newspaper today = new Newspaper("News update", 206, "GMA", "June 12 2004");
        today.getDetails();
    }

}