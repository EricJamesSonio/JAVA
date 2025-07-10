package abstraction;

class Book extends LibraryItem {
    String topic;

    Book(String name, int code, String author, String topic) {
        super(name, code, author);
        this.topic = topic;
    }

    @Override
    void getDetails() {
        System.out.println("Name : " + name + ", Code" + code + ", Author : " + author + ", Topic : " + topic);
    }

}