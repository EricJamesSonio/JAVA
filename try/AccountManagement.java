package try;

import java.util.*;

public class AccountManagement {
    private List<Account> accounts;
    private static int nextId;

    public AccountManagement() {
        this.accounts = new ArrayList<>();
    }

    public void addAccount(User user, String email, String password) {
        int id = nextId++;
        Account account = new Account(user, id, email, password);
        accounts.add(account);
    }

    public void removeAccount(int id) {
        Account existing = findAccount(id);
        if (existing == null) {
            return;
        }

        accounts.remove(existing);
    }

    public Account findAccount(int id) {
        for (Account acc : accounts) {
            if (acc.getId() == id) {
                return acc;
            }
        }
        return null;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
