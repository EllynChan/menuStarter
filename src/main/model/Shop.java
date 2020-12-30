package model;

import persistence.InventoryFullException;
import persistence.NotInBoundsException;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private Products products;
    private ShopKeeper shopKeeper;

    public Shop(String shopKeeperName) {
        products = new Products();
        shopKeeper = new ShopKeeper(shopKeeperName);
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }

    public ShopKeeper getShopKeeper() {
        return shopKeeper;
    }

    public void setShopKeeper(ShopKeeper shopKeeper) {
        this.shopKeeper = shopKeeper;
    }

}
