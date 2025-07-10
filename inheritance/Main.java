package inheritance;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Eric james", 21, "CMI");
        s1.getDetails();
        
    }


}

class Person {
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

class Student extends Person {
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

class Teacher extends Person {
    String department;
    int contactNo;

    public Teacher(String name, int age, String department, int contactNo) {
        super(name, age);
        this.department = department;
        this.contactNo = contactNo;
    }

    @Override
    public void getDetails() {
        System.out.println("Teacher Name : " + name + " Department : " +  department);
    }


}
