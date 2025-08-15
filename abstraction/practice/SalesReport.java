package abstraction.practice;

import java.util.*;
public class SalesReport {
    private List<Cashier> cashiers;
    private double totalSales;
    private double totalDiscount;

    public SalesReport() {
        this.cashiers = new ArrayList<>();
        this.totalSales = computeTotalSales();
        this.totalDiscount = computeTotalDiscounts();
    }


    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
    }

    public double computeTotalSales() {
        double total = 0;

        for (Cashier cashier : cashiers) {
            for (Receipt receipt : cashier.getRecords()) {
                total += receipt.getTotalPayable();
            }
        }
        return total;
    }

    public double computeTotalDiscounts() {
        double total = 0;
        for (Cashier cashier : cashiers) {
            for (Receipt receipt : cashier.getRecords()) {
                total += receipt.getOrder().getDiscount();
            }
        }
        return total;
    }

    public double computeTotalTAx() {
        double total = 0;
        for (Cashier cashier : cashiers) {
            for (Receipt receipt : cashier.getRecords()) {
                total += receipt.getOrder().getTax();
            }
        }
        return total;
    }

    public double getTotalsales() {
        return totalSales;
    }

    public double getDiscount() {
        return totalDiscount;
    }
}
