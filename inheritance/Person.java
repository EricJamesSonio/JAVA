package inheritance;

public class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void getDetails() {
        System.out.println("Name : " + name);
    }
}
