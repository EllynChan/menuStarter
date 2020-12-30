import model.*;
import persistence.InventoryFullException;
import persistence.NotInBoundsException;

import java.util.Random;
import java.util.Scanner;

public class MenuConsole {
    private Scanner input;
    private Random rand = new Random();
    private Shop shop;
    private Inventory inventory;
    private boolean canPurchase;
    private boolean canSell;

    public MenuConsole() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("l")) {
                System.out.println("Come again to see Salad. Any Time!!");
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

    }

    //EFFECTS: initiates a shop and inventory with some initial stats
    private void init() {
        input = new Scanner(System.in);
        shop = new Shop("Salad");
        shop.getShopKeeper().setDialog("Welcom! To Salad's own little shop!");
        Products products = new Products();
        try {
            products.addItem("Silver Apple", 50);
            products.addItem("Legendary Orange", 60);
            products.addItem("Banana Bandanna", 40);
            products.addItem("AAAAAA Meat", 90);
            products.addItem("Not Salad", 5);
            shop.setProducts(products);
        } catch (InventoryFullException e) {
            System.out.println("Can't add more items to list, list full");
        }
        inventory = new Inventory();
        inventory.setBalance(new Balance(200));
    }

    //EFFECTS: displays a menu
    private void displayMenu() {
        System.out.println(shop.getShopKeeper().getDialog());
        System.out.println("\tA ----- About " + shop.getShopKeeper().getName());
        System.out.println("\tB ----- Buy");
        System.out.println("\tS ----- Sell");
        System.out.println("\tE ----- Earn money");
        System.out.println("\tL ----- Leave");
        System.out.println("\tYou have: $" + inventory.getInventoryBalance().getBalance());
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            shopKeeperDialog();
        } else if (command.equals("b")) {
            displayProducts();
            buyItemsParsed(input.next());
        } else if (command.equals("s")) {
            System.out.println("You can sell whatever you just bought from Salad right back to Salad! " +
                    "For the convenient price of $10 for any Item!");
            System.out.println("Pls type the number of the item you want to sell!");
            displayInventory();
            sellItemsParsed(input.next());
        } else if (command.equals("e")) {
            earnMoney();
        } else {
            System.out.println("Try to choose options from the menu!");
        }
    }

    private void displayProducts() {
        int numbering = 1;
        for (Item i : shop.getProducts().getItems()) {
            System.out.println(numbering + ". " + i.getName() + " ----- $" + i.getPrice());
            numbering += 1;
        }
        System.out.println("\tYou have: $" + inventory.getInventoryBalance().getBalance());
    }

    private void displayInventory() {
        int numbering = 1;
        for (Item i : inventory.getItems()) {
            System.out.println(numbering + ". " + i.getName() + " ----- $" + 10);
            numbering += 1;
        }
        System.out.println("\tYou have: $" + inventory.getInventoryBalance().getBalance());
    }

    private void shopKeeperDialog() {
        System.out.println("Hoi!! I'm Salad! Salad is my favourite foooood~~");
    }


    //EFFECTS: remove the item at index i from the inventory, throws exception if there is no
    // item with index = i
    private void sellItems(int i) {
        canSell = true;
        try {
            inventory.removeItem(i);
        } catch (NotInBoundsException e) {
            canSell = false;
        }
    }

    //MODIFIES: this
    //EFFECTS: Parse input value, remove the item at index s - 1 from the inventory if
    //the input is an integer, and adds 10 to inventory balance. Throws NumberFormatException otherwise
    private void sellItemsParsed(String s) {
            try {
                sellItems(Integer.parseInt(s) - 1);
                if (canSell) {
                    int balance = inventory.getInventoryBalance().getBalance();
                    Balance newBalance = new Balance(balance + 10);
                    inventory.setBalance(newBalance);

                    canPurchase = true;
                    System.out.println("Tank you!!");
                } else {
                    System.out.println("Try to pick an Item that you actually have!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Pls give a number! Salad did not understand");
            }
    }

    //EFFECTS: adds the item at index i of products to the inventory, throws exception if there is no
    // item with index = i
    private void buyItems(int i) {
        try {
            if (shop.getProducts().getItem(i).getPrice() < inventory.getInventoryBalance().getBalance()) {
                inventory.addItem(shop.getProducts().getItem(i));
                canPurchase = true;
            } else {
                System.out.println("You don't have enough money!");
                canPurchase = false;
            }
        } catch (NotInBoundsException e) {
            System.out.println("Try to pick something that the shop has~");
        } catch (InventoryFullException e) {
            canPurchase = false;
            System.out.println("You are carrying too much~");
            System.out.println("Maybe you could sell something first!");
        }
    }

    //MODIFIES: this
    //EFFECTS: Parse input value, adds the item at index s - 1 of products to the inventory if
    //the input is an integer, takes away price of the item from inventory balance.
    // Throws NumberFormatException otherwise
    private void buyItemsParsed(String s) {
        try {
            buyItems(Integer.parseInt(s) - 1);
        } catch (NumberFormatException e) {
            System.out.println("Pls give a number! Salad did not understand");
        }
        if (canPurchase) {
            int balance = inventory.getInventoryBalance().getBalance();
            try {
                int productPrice = shop.getProducts().getItem(Integer.parseInt(s) - 1).getPrice();
                Balance newBalance = new Balance(balance - productPrice);
                inventory.setBalance(newBalance);
                System.out.println("Tank you for ya purchase!");
            } catch (NotInBoundsException e) {
                System.out.println("Try to pick something that the shop has~");
            }
        }
    }

    private void earnMoney() {
        boolean inLoop = true;
        Scanner newInput = new Scanner(System.in);
        String newCommand = null;

        while (inLoop) {
            displaySubMenu();
            newCommand = newInput.next();
            newCommand = newCommand.toLowerCase();

            if (newCommand.equals("b")) {
                inLoop = false;
            } else {
                processSubCommand(newCommand);
            }
        }
    }

    private void displaySubMenu() {
        System.out.println("You left the shop to earn more money");
        System.out.println("What will you do?");
        System.out.println("\tF ----- Fight");
        System.out.println("\tP ----- Perform");
        System.out.println("\tC ----- Clean");
        System.out.println("\tB ----- go back");
        System.out.println("\tYou have: $" + inventory.getInventoryBalance().getBalance());
    }

    private void processSubCommand(String command) {

        if (command.equals("f")) {
            int randNum = rand.nextInt(30) + 190;
            int numDemons = rand.nextInt(10);
            System.out.println("You cleansed " + (numDemons + 1) + " otherworldly demons. "
            + "The ruler of the Nation was very impressed and offered you $" + randNum);

            int balance = inventory.getInventoryBalance().getBalance();
            Balance newBalance = new Balance(balance + randNum);
            inventory.setBalance(newBalance);
        } else if (command.equals("p")) {
            int randNum = rand.nextInt(35) + 5;
            System.out.println("You impressed a crowd by performing gymnastics on the street. " +
                    "You collected $" + randNum + " worth of donation");

            int balance = inventory.getInventoryBalance().getBalance();
            Balance newBalance = new Balance(balance + randNum);
            inventory.setBalance(newBalance);
        } else if (command.equals("c")) {
            int randNum = rand.nextInt(40) + 20;
            System.out.println("You offered random people to clean their homes for them. " +
                    "Through hard work and dedication you earned $" + randNum);

            int balance = inventory.getInventoryBalance().getBalance();
            Balance newBalance = new Balance(balance + randNum);
            inventory.setBalance(newBalance);
        } else {
            System.out.println("Try to choose options from the menu!");
        }
    }

}
