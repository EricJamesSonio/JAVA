package exam;
import java.util.*;

public class Employee {
    private String name;
    private int id;
    

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    } 

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
 } 

class Cashier extends Employee{
    private List<Receipt> transactionHistory;
    private OrderProcessor orderprocessor;

    public Cashier (String name, int id) {
        super(name, id);
        this.orderprocessor = new OrderProcessor();
        this.transactionHistory = new ArrayList<>();
    }


    public Receipt processOrder(User user, double payment) {
        return orderprocessor.processOrder(user, payment, this);
    }

    public List<Receipt> getTransactionHistory() {
        return transactionHistory;
    }
}


class Admin  extends Employee{

    public Admin(String name, int id) {
        super(name, id);
    }


}


