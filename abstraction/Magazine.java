package abstraction;

class Magazine extends LibraryItem {
    String rated;

    Magazine(String name, int code, String author, String rated) {
        super(name, code, author);
        this.rated = rated;
    }

    @Override
    void getDetails() {
        System.out.println("Name : " + name + ", Code" + code + ", Author : " + author + ", Rated : " + rated);
    }

}
