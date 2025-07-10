package inheritance;

public class Student extends Person {
    String school;

    public Student(String name, int age, String school) {
        super(name, age);
        this.school =school;
    }
    @Override
    public void getDetails() {
        System.out.print("Student Name : " + name + " School : " + school);
    }

}