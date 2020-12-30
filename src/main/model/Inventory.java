package model;

import java.util.ArrayList;
import java.util.List;

public class Inventory extends ItemListManager {
    private Balance balance;

    public Inventory() {
        balance = new Balance(0);
        items = new ArrayList<>();
    }

    //EFFECTS: returns the balance object
    public Balance getInventoryBalance() {
        return this.balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

}
