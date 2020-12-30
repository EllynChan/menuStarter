package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.InventoryFullException;
import persistence.NotInBoundsException;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    private Inventory inventory;

    @BeforeEach
    void runBefore() {
        inventory = new Inventory();
    }

    //Test adding item to inventory typical case
    @Test
    void testAddItem() {
        try {
            inventory.addItem("Apple", 50);
            assertEquals(1, inventory.getItems().size());
        } catch (InventoryFullException e) {
            fail("shouldn't throw exception here");
        }

        Item i = new Item("Orange",60);
        try {
            inventory.addItem(i);
            assertEquals(2, inventory.getItems().size());
        } catch (InventoryFullException e) {
            fail("shouldn't throw exception here");
        }
    }

    //Test getting item from inventory typical case
    @Test
    void testGetItem() {
        try {
            Item i = new Item("Apple", 50);
            inventory.addItem(i);
            try {
                assertEquals("Apple", inventory.getItem(0).getName());
                assertEquals(50, inventory.getItem(0).getPrice());
                assertEquals(i, inventory.getItem(0));
            } catch (NotInBoundsException e) {
                fail("shouldn't throw exception here");
            }
        } catch (InventoryFullException e) {
            fail("shouldn't throw exception here");
        }
    }

    //Test getting item from inventory fail case given index over bounds
    @Test
    void testGetItemOverBoundsException() {
        try {
            Item i = new Item("Apple", 50);
            try {
                inventory.addItem(i);
            } catch (InventoryFullException e) {
                fail("shouldn't throw exception here");
            }
            inventory.getItem(5);
            fail("Exception expected");
        } catch (NotInBoundsException e) {
            //pass
        }
    }

    //Test getting item from inventory fail case given index under bounds
    @Test
    void testGetItemUnderBoundsException() {
        try {
            Item i = new Item("Apple", 50);
            try {
                inventory.addItem(i);
            } catch (InventoryFullException e) {
                fail("shouldn't throw exception here");
            }
            inventory.getItem(-5);
            fail("Exception expected");
        } catch (NotInBoundsException e) {
            //pass
        }
    }

    //Test removing item from inventory typical case
    @Test
    void testRemoveItem() {
        try {
            try {
                inventory.addItem("Apple", 50);
                inventory.addItem("Orange", 60);
                inventory.addItem("Banana", 40);
            } catch (InventoryFullException e) {
                fail("shouldn't throw exception here");
            }
            inventory.removeItem(1);
            assertEquals(2, inventory.getItems().size());
            try {
                assertEquals("Apple", inventory.getItem(0).getName());
                assertEquals(40, inventory.getItem(1).getPrice());
            } catch (NotInBoundsException e) {
                fail("shouldn't throw exception here");
            }
        } catch (NotInBoundsException e) {
            fail("shouldn't throw exception here");
        }
    }

    //Test removing item from inventory fail case index over bounds
    @Test
    void testRemoveItemOverBoundsException() {
        try {
            try {
                inventory.addItem("Apple", 50);
                inventory.addItem("Orange", 60);
                inventory.addItem("Banana", 40);
            } catch (InventoryFullException e) {
                fail("shouldn't throw exception here");
            }
            inventory.removeItem(5);
            fail("Exception expected");
        } catch (NotInBoundsException e) {
            //pass
        }
    }

    //Test removing item from inventory fail case index under bounds
    @Test
    void testRemoveItemUnderBoundsException() {
        try {
            try {
                inventory.addItem("Apple", 50);
                inventory.addItem("Orange", 60);
                inventory.addItem("Banana", 40);
            } catch (InventoryFullException e) {
                fail("shouldn't throw exception here");
            }
            inventory.removeItem(-5);
            fail("Exception expected");
        } catch (NotInBoundsException e) {
            //pass
        }
    }

    //Test setting and getting inventory balance
    @Test
    void testInventorySetBalance(){
        assertEquals(0,inventory.getInventoryBalance().getBalance());
        inventory.getInventoryBalance().addMoney(500);
        assertEquals(500,inventory.getInventoryBalance().getBalance());
        inventory.getInventoryBalance().subtractMoney(300);
        assertEquals(200,inventory.getInventoryBalance().getBalance());

        Balance newBalance = new Balance(1000);
        newBalance.setBalance(10000);
        assertTrue(newBalance.isBalanceOverMax());
        assertEquals("9999+", newBalance.returnBalanceOverMax());

        inventory.setBalance(newBalance);
        assertEquals(10000,inventory.getInventoryBalance().getBalance());
    }

    //Test setting and getting inventory items list
    @Test
    void testInventorySetItemList() {
        try {
            inventory.addItem("Apple", 50);
            inventory.addItem("Orange", 60);
            inventory.addItem("Banana", 40);

        } catch (InventoryFullException e) {
            fail("shouldn't throw exception here");
        }

        assertEquals(3,inventory.getItems().size());

        Inventory newInventory = new Inventory();
        inventory.setItems(newInventory.getItems());
        assertEquals(0,inventory.getItems().size());
    }

    //Test setting and getting inventory capacity
    @Test
    void testInventorySetCapacity() {
        assertEquals(10,inventory.getCapacity());
        inventory.setCapacity(20);
        assertEquals(20,inventory.getCapacity());
    }

    //Test setting and Item name and price
    @Test
    void testSetItem() {
        Item item = new Item("Apple",50);
        item.setName("Juice");
        item.setPrice(100);
        assertEquals("Juice", item.getName());
        assertEquals(100,item.getPrice());
    }

}
