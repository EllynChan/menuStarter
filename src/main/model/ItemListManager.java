package model;

import persistence.InventoryFullException;
import persistence.NotInBoundsException;

import java.util.List;

public abstract class ItemListManager {
    protected List<Item> items;
    protected int capacity = 10;

    //MODIFIES: this
    //EFFECTS: adds new item with the name itemName and the price itemPrice to the items list,
    // does nothing if inventory is full
    public void addItem(String itemName, int itemPrice) throws InventoryFullException {
        if (items.size() >= capacity) {
            throw new InventoryFullException();
        }
        Item i = new Item(itemName,itemPrice);
        items.add(i);
    }

    //MODIFIES: this
    //EFFECTS: adds the item i to the items list
    // does nothing if inventory is full
    public void addItem(Item i) throws InventoryFullException {
        if (items.size() >= capacity) {
            throw new InventoryFullException();
        }
        items.add(i);
    }

    //EFFECTS: returns the item at index i
    public Item getItem(int i) throws NotInBoundsException {
        if (!(0 <= i && i < items.size())) {
            throw new NotInBoundsException();
        }
        Item itemI = items.get(i);
        return itemI;
    }

    //MODIFIES: this
    //EFFECTS: removes the item at the i-th index
    public void removeItem(int i) throws NotInBoundsException {
        if (!(0 <= i && i < items.size())) {
            throw new NotInBoundsException();
        }
        items.remove(i);
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
