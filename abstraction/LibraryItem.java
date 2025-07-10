package abstraction;

abstract class LibraryItem {
    String name;
    int code;
    String author;

    LibraryItem(String name, int code, String author) {
        this.name = name;
        this.code = code;
        this.author = author;
    }

    abstract void getDetails();

}