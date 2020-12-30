package model;

public class Item {
    private String name;
    private int price;

    //EFFECT: constructs an item with a name and a price
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    //EFFECT: returns the item's name
    public String getName() {
        return name;
    }

    //MODIFIES: this
    //EFFECT: sets the item's name
    public void setName(String name) {
        this.name = name;
    }

    //EFFECT: returns the item's price
    public int getPrice() {
        return price;
    }

    //MODIFIES: this
    //EFFECT: sets the item's price
    public void setPrice(int price) {
        this.price = price;
    }
}
