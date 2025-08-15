package exam;
import java.util.*;

public class EmployeeManagement {
    private Store store;
    private List<Employee> employees;


    public EmployeeManagement (Store store) {
        this.employees = new ArrayList<>();
        this.store = store;
    }

    public Employee findEmployee(int id) {
        for (Employee emp : employees) {
            if (emp.getId() == id) {
                return emp;
        }
        }
    return null;
    } 

    public void addEmployee(Employee emp) {
        Employee existing = findEmployee(emp.getId());

        if (existing != null) {
            return;
            }else {
            employees.add(emp);
        }
    }

    public Store getStore() {
        return store;
    }


    public List<Employee> getEmployees() {
        return employees;
    }

    

    public void displayEmployees() {
        for (Employee emp : employees) {
            System.out.println("Name : "+ emp.getName() + " Id : " + emp.getId());
        }
    }

}
