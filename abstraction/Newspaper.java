package abstraction;

class Newspaper extends LibraryItem {
    String date;

    Newspaper(String name, int code, String author, String date) {
        super(name, code, author);
        this.date = date;
    }

    @Override
    void getDetails() {
        System.out.println("Name : " + name + ", Code" + code + ", Author : " + author + ", Date : " + date);
    }

}
