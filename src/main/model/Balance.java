package model;

public class Balance {
    private int balance;
    private int MAX_MONEY = 9999;

    //EFFECT: constructs a balance with an amount of money
    public Balance(int balance) {
        this.balance = balance;
    }

    //MODIFIES: this
    //EFFECT: add i to balance
    public void addMoney(int i) {
        balance = balance + i;
    }

    //REQUIRES: i <= balance
    //MODIFIES: this
    //EFFECT: subtract i from balance
    public void subtractMoney(int i) {
        balance = balance - i;
    }

    //EFFECT: returns balance
    public int getBalance() {
        return balance;
    }

    //EFFECT: returns true if balance is over MAX_MONEY. False otherwise
    public boolean isBalanceOverMax() {
        return balance > 9999;
    }

    //EFFECT: returns string "9999+"
    public String returnBalanceOverMax() {
        return MAX_MONEY + "+";
    }

    //MODIFIES: this
    //EFFECT: sets the balance as balance
    public void setBalance(int balance) {
        this.balance = balance;
    }
}
