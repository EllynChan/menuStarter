package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.InventoryFullException;

import static org.junit.jupiter.api.Assertions.*;

public class ShopTest {
    private Shop shop;

    @BeforeEach
    void runBefore() {
        shop = new Shop("Salad");
    }

    @Test
    void testSetShopKeeper() {
        assertEquals("Salad",shop.getShopKeeper().getName());
        ShopKeeper newShopKeeper = new ShopKeeper("Moon");
        newShopKeeper.setName("Sun");
        shop.setShopKeeper(newShopKeeper);
        assertEquals("Sun",shop.getShopKeeper().getName());

        assertEquals("Well, I'm Sun. You can do whatever you want in my shop",
                shop.getShopKeeper().getDialog());

        shop.getShopKeeper().setDialog("Well, I'm Moon. You can do whatever you want in my shop");
        assertEquals("Well, I'm Moon. You can do whatever you want in my shop",
                shop.getShopKeeper().getDialog());
    }

    //Test setting and getting shop product list
    @Test
    void testInventorySetItemList() {
        try {
            shop.getProducts().addItem("Apple", 50);
            shop.getProducts().addItem("Orange", 60);
            shop.getProducts().addItem("Banana", 40);
        } catch (InventoryFullException e) {
            fail("shouldn't throw exception here");
        }

        assertEquals(3,shop.getProducts().getItems().size());

        Shop newShop = new Shop("Snow");
        shop.setProducts(newShop.getProducts());
        assertEquals(0,shop.getProducts().getItems().size());
    }

}
