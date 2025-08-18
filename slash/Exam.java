package slash;

import java.util.*;

public class Exam {
    public static void main(String args[]) {
    }
}

class Person {
    private String firstName;
    private String middleName;
    private String lastName;
    private Address address;

    public Person (String firstName, String middleName, String lastName, Address address) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.address = address;
    } 

    public String getFirstname() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getAddress() {
        return address;
    }
}


class Address {
    private String country;
    private String province;
    private String city;
    private String zipCode;

    public Address (String country, String province, String city, String zipCode) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public String getProvince() {
        return province;
    }

    public String getCity() {
        return city;
    }

    public String getZipCode() {
        return zipCode;
    }
}

class Account {
    private Person person;
    private int id;
    private double balance;
    
    public Account (Person person, int id) {
        this.person = person;
        this.id = id;
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount <= 0 ) {
            return;
        }
        balance += amount;
    }

    public void withDraw(double amount ) { 
        if (amount <= 0 || amount > balance) {
            return;
        }

        balance -= amount;
    }

    public Person getPerson() {
        return person;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public String toString () {
        return "Name : " + person.getFirstname() + " " + person.getMiddleName() + " " + person.getLastName() ;
    }
}

class Bank {
    private double totalBankMoney;
    private double totalInterest;
    private double totalProfit;
    private List<Account> accounts;
    private static int nextId;

    public Bank () {
        this.totalBankMoney = 0;
        this.totalInterest = 0;
        this.totalProfit = 0;
        this.accounts = new ArrayList<>();
    }

    public double getTotalBankMoney() {
        return totalBankMoney;
    }

    public double getTotalInterest() {
        return totalInterest;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void displayAccounts() {
        for (Account acc : accounts) {
            System.out.println(acc);
        }
    }

    public Account findAccount(int id) {
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                return acc;
            }
            }
        return null;
    }

    public void depositAccount(double amount, int id) {
        Account existing = findAccount(id);

        if (existing == null) {
            return;
        }
        if (amount <= 0) {
            return;
        }

        existing.deposit(amount);
        computeTotalBankMoney();

    }

    public void withdrawAccount(double amount, int id) {
        Account existing = findAccount(id);

        if (existing == null || amount <= 0 || amount > existing.getBalance()) {
            return;
        }

        existing.withDraw(amount);
        computeTotalBankMoney();
    }

    public void createAccount(Person person) {
        int id = nextId++;
        Account newAccount = new Account(person, id);
        accounts.add(newAccount);
    }

    public void computeTotalBankMoney() {
        double total = 0;
        for (Account    acc : accounts) {
            total += acc.getBalance();
        }
        totalBankMoney = total;
    }

}


