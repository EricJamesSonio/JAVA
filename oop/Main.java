package oop;

public class Main {
    public static void main (String[] args) {
        Book b1 = new Book("English", 10, 100, 10);
        b1.setlibrary("Here");
        b1.getDetails();

        Animal a1 = new Animal();
        a1.color = "Red";
        a1.name = "charles";


    }

}

class LibraryItem {
    String name;
    int id;
    float price;
    int quantity;

    LibraryItem (String name, int id, float price, int quantity) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.quantity = quantity;
    }

    public void getDetails() {
        System.out.println("Name : " + name );
    }
}

class Book extends LibraryItem {
    private String library;

    Book (String name, int id, float price, int quantity) {
        super(name, id, price, quantity);
    }

    public void setlibrary (String library) {
        this.library = library;
    }

    @Override
    public void getDetails() {
        System.err.println("Library " + library + "Name : " + name );
    }

}

class Animal {
    String name;
    String color;

    public Animal () {
        System.out.println("Creatd animal class");
    }

    public void s() {
        System.out.println("Im an Animal");
    }


}

class Dog extends Animal {
    public Dog () {
        System.out.println("Creayted a dog");
    }

    @Override
    public void s() {
        super.s();
    }

}

class Magazine extends LibraryItem {
    String type;

    Magazine (String name, int id, float price, int quantity, String type) {
        super(name, id, price, quantity);
        this.type = type;
    }


}

class Newspaper extends LibraryItem {
    private String date;

    Newspaper(String name, int id, float price, int quantity) {
        super(name, id, price, quantity);
    }

    void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
    return date;
}


}