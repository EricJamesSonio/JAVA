package try;

public class Account {
    private User user;
    private int id;
    private String email;
    private String password;
    private Cart cart;
    
    public Account (User user, int id, String email, String password) {
        this.user = user;
        this.id = id;
        this.email = email;
        this.password = password;
        this.cart = new Cart();
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getEmail() { 
        return email;
    }

    public Cart getCart() {
        return cart;
    }
}
