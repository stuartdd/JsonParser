package safe;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    public Map<String, Contents> safe = new HashMap<>();
    public Map<String, Customer> customers = new HashMap<>();

    public void newCustomer(Customer c) {
        customers.put(c.getId(), c);
        if (!safe.containsKey(c.getId())) {
            safe.put(c.getId(), new Contents(0.0));
        }
    }

    public Customer findCustomer(String id) {
        return customers.get(id);
    }

    public void removeCustomer(String id) {
        customers.remove(id);
    }

    public boolean isOverdrawn(String id) {
        if (customers.containsKey(id)) {
            return safe.get(id).isOverdrawn();
        }
        return false;
    }

    public void withdraw(String id, double amount) {
        if (customers.containsKey(id)) {
            Contents contents = safe.get(id);
            contents.sub(amount);
        }
    }

    public void deposite(String id, double amount) {
        if (customers.containsKey(id)) {
            Contents contents = safe.get(id);
            contents.add(amount);
        }
    }
}
