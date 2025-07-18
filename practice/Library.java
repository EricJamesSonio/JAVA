package practice;

import java.util.HashMap;

public class Library {
    public static void main(String[] args) {
        LibraryItem english = new LibraryItem("English book", 12, 20.20);
        LibraryStore lib = new LibraryStore("Uncle");
        lib.addItem(english, 10);

        Student stud1 = new Student("Eric", 10);

        lib.displayItems();
        stud1.borrowBook(english, 5, lib);
        stud1.displayBorrowed();

        lib.displayItems();
    }
}

class LibraryItem {
    private String name;
    private int code;
    private double price;

    public LibraryItem (String name, int code, double price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public String getDetails() {
        return String.format("Name : %s, Code : %d , Price : %.2f, Quantity : ", name, code , price);
    }
}

class Student {
    private String name;
    private int id;
    private HashMap<LibraryItem, Integer> borrowedbooks;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        this.borrowedbooks = new HashMap<>();
    }

    public String getDetails() {
        return String.format("Name : %s, Id : %d", name, id);
    }

    public HashMap<LibraryItem, Integer> getBorrowedBooks() {
        return borrowedbooks;
    }

    public void borrowBook(LibraryItem item, int quantity, LibraryStore lib)  {
        LibraryItem existing = lib.findItem(item.getCode());

        if (existing == null) {
            System.out.println("Book doesn't exist");
            return;
        }

        LibraryItem existingInBorrowed = findItem(existing.getCode());

        if (existingInBorrowed != null) {
            System.out.println("Already Exist!");
            int newqtty = lib.getItems().get(existing) + borrowedbooks.get(existingInBorrowed);
            borrowedbooks.put(existingInBorrowed, newqtty);
            lib.updateQuantity(existing.getCode(), -quantity);
        } else {
            System.out.println("Borrowed Item : " + existing.getName());
            borrowedbooks.put(existing, quantity);
            lib.updateQuantity(existing.getCode(), -quantity);
        }
    }

    public LibraryItem findItem(int code) {
        for (LibraryItem item : borrowedbooks.keySet()) {
            if (item.getCode() == code) {
                return item;
            }
        }
        return null;
    }

    public void displayBorrowed() {
        System.out.println("<--- Borrowed  --->");
        for (LibraryItem item : borrowedbooks.keySet()) {
            System.out.println(item.getDetails() + borrowedbooks.get(item));
        }
        System.out.println();
        }
}



class LibraryStore {
    private HashMap<LibraryItem, Integer> items;
    private String name;

    public LibraryStore (String name) {
        this.name = name;
        this.items = new HashMap<>();
    }

    public String getDetails() {
        return String.format("Name : %s", name);
    }

    public void displayItems() {
        System.out.println("<--- Library Items --->");
        for (LibraryItem item : items.keySet()) {
            System.out.println(item.getDetails() + items.get(item));
        }
        System.out.println();
    }

    public LibraryItem findItem(int code) {
        
        for (LibraryItem item : items.keySet()) {
            if (item.getCode() == code) {
                return item;
            }
        }
    return null;
    }

    public void addItem(LibraryItem item, int quantity) {
        LibraryItem existing = findItem(item.getCode());

        if (existing != null) {
            int newqtty = items.get(existing) + quantity;
            items.put(existing, newqtty);
        } else {
            System.out.println("Added Item : " + item.getName());
            items.put(item, quantity);
            }
    }

    public void updateQuantity(int code, int quantity) {
        LibraryItem existing = findItem(code);

        if (existing != null) {
            int newqtty = items.get(existing) + quantity;
            if (newqtty <= 0) {
                items.remove(existing);
            } else {
                items.put(existing, newqtty);
            }
        } else {
            System.out.println("Item Doesn't exist!");
            System.out.println();
        }
    }

    public HashMap<LibraryItem, Integer> getItems() {
        return items;
    }



}

