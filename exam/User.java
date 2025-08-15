package exam;

import java.util.List;
import java.util.ArrayList;

public class User {
    private Id id;
    private List<Receipt> orderHistory;
    private Cart cart;

    public User (Id id) {
        this.id = id;
        this.orderHistory = new ArrayList<Receipt>();
        this.cart = new Cart();
    }

    public Id getId() {
        return id;
    }

    public List<Receipt> getOrderHistory() {
        return orderHistory;
    }

    public Cart getCart() {
        return cart;
    } 

}
