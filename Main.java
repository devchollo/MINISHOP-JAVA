
import java.util.*;
import java.time.LocalDate; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class

public class Main {
    public static void main(String[] args) {
        ArrayList<Inventory> inventory = new ArrayList<>();
        // show title
        System.out.println("----------------------------------------------");
        System.out.println("MINI SHOP STOP -- YOUR 24/7 CONVENIENCE STORE!");
        System.out.println("----------------------------------------------");
        // show homepage
        homepage(inventory);
    }

    // HOMEPAGE METHOD
    public static void homepage(ArrayList<Inventory> inventory){
        // Show menu
        System.out.println("1. Add Item \t\t\t\t\t 2. Retrieve Items");
        System.out.println("3. Update Item \t\t\t\t\t 4. Delete Item");
        System.out.println("5. Exit Application");

        // option input field
        System.out.println("\nWhat would like to do?<1-5>: ");
        Scanner in = new Scanner(System.in);
        int homeOpt = in.nextInt();
        // conditional statement for the Menu -- USER OPTED IN
        if(homeOpt == 1) {
            // create item page - show
            addItemPage(inventory);
        }else if(homeOpt == 2) {
           // retrieve items options
            retrieveItemsOptions(inventory);
        }else if(homeOpt == 3) {
            // update item
            updateItem(inventory);
        }else if(homeOpt == 4) {
            // delete item
            deleteItem(inventory);
        }else if(homeOpt == 5) {
            exitApplication();
        } else {
            System.out.println("\nOops! I think you have entered an invalid input. Please try again. \n");
            homepage(inventory);
        }
    }

    public static void addItemPage(ArrayList<Inventory> inventory) {
        Scanner input = new Scanner(System.in);
        System.out.println("Input the item name: ");
        String itemName = input.nextLine();
        System.out.println("Input the item price: ");
        double itemPrice = input.nextDouble();
        input.nextLine();
        // continue?
        System.out.println("Would you like to continue?<Y/N>: ");
        String cont = input.nextLine();

        if(cont.equals("Y") || cont.equals("y")) {
            // generate random number to store in the array as the SKU
            int min = 100000;
            int max = 999999;
            int randomSKU = (int)Math.floor(Math.random()*(max-min+1)+min);

            // check if the SKU is already in the array
            int SKU = randomSKU;
            for (Inventory item : inventory) {
                // check if the SKU already exists in the array
                if (item.SKU == randomSKU) {
                    SKU = randomSKU + 17;
                    break;
                }
            }
            // create the date added and format it to DD/MM/YYY
            LocalDate rawDate = LocalDate.now();
            DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = formatPattern.format(rawDate); // store this for the date created and date modified

            Inventory inventoryItem = new Inventory(SKU, itemName, itemPrice, formattedDate, formattedDate);
            // add the item to the array list
            inventory.add(inventoryItem);

            System.out.println("\n---------------------------------------------");
            System.out.println("\t\t\t\t\tITEM SAVED");
            System.out.println("---------------------------------------------\n");
            // conditional statement method
            reOption(inventory);
        } else if(cont.equals("N") || cont.equals("n")) {
            System.out.println("\n---------------------------------------------");
            System.out.println("\t\t\t\t\tITEM NOT SAVED");
            System.out.println("---------------------------------------------\n");
            // conditional statement method
            reOption(inventory);
        }
    }

    // Update Item Method
    public static void updateItem(ArrayList<Inventory> inventory) {
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------------");
        System.out.println("\t\t\tUPDATE AN ITEM.");
        System.out.println("-----------------------------------");
        System.out.println("1. Update item name. \t\t\t\t\t\t\t\t\t\t 2. Update item price. ");
        System.out.println("3. Go back to retrieve item page. \t\t\t\t\t\t\t\t\t\t 4. Go back to homepage. ");
        System.out.println("5. Exit Application.");
        System.out.println("What would you like to do?<1-5>: ");
        int modOpt = input.nextInt();

        if (modOpt == 1) {
            System.out.println("Enter the item number you want to update: ");
            int itemNum = input.nextInt();
            input.nextLine();
            // find the item with the corresponding item number and update the item name
            for (Inventory item : inventory) {
                if (item.SKU == itemNum) {
                    // create the date modified and format it to DD/MM/YYY
                    LocalDate rawDate = LocalDate.now();
                    // PLEASE CHANGE IT BACK AGAIN TO dd/MM/yyyy - this is only for demonstration
                    DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String modifiedDate = formatPattern.format(rawDate); // store this for the date created and date modified

                    // once the item number has been found in the array, ask for the change.
                    System.out.println("Enter NEW ITEM NAME for " +item.itemName+ " #" +item.SKU+ ": ");
                    String itemName = input.nextLine();
                    inventory.set(inventory.indexOf(item), new Inventory(
                            item.SKU, itemName, item.itemPrice, item.dateAdded, modifiedDate));

                    System.out.println("\n-------------------------");
                    System.out.println("ITEM SUCCESSFULLY UPDATED!");
                    System.out.println("---------------------------\n");
                    showAllItems(inventory);
                }

            }
            System.out.println("The item number you have entered does not exist. Please try again.");
            updateItem(inventory);

        } else if(modOpt == 2) {
            // update item price
            System.out.println("Enter the item number you want to update: ");
            int itemNum = input.nextInt();
            input.nextLine();
            // find the item with the corresponding item number and update the item name
            for (Inventory item : inventory) {
                if (item.SKU == itemNum) {
                    // create the date modified and format it to DD/MM/YYY
                    LocalDate rawDate = LocalDate.now();
                    // PLEASE CHANGE IT BACK AGAIN TO dd/MM/yyyy - this is only for demonstration
                    DateTimeFormatter formatPattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                    String modifiedDate = formatPattern.format(rawDate); // store this for the date created and date modified

                    // once the item number has been found in the array, ask for the change.
                    System.out.println("Enter NEW ITEM PRICE for " +item.itemName+ " #" +item.SKU+ ": ");
                    double itemPrice = input.nextDouble();
                    inventory.set(inventory.indexOf(item), new Inventory(
                            item.SKU, item.itemName, itemPrice, item.dateAdded, modifiedDate));

                    System.out.println("\n-------------------------");
                    System.out.println("ITEM SUCCESSFULLY UPDATED!");
                    System.out.println("---------------------------\n");
                    showAllItems(inventory);
                }
            }
            System.out.println("The item number you have entered does not exist. Please try again.");
            updateItem(inventory);

        } else if(modOpt == 3){
            // retrieve page
            retrieveItemsOptions(inventory);
        } else if(modOpt == 4) {
            //homepage
            homepage(inventory);
        } else if(modOpt == 5) {
            // exit
            exitApplication();
        } else {
            System.out.println("ERROR: Please enter a valid input.");
            updateItem(inventory);
        }

    }

    public static void retrieveItemsOptions(ArrayList<Inventory> inventory) {
        Scanner input = new Scanner(System.in);
        // offer options for viewing
        System.out.println("1. View item(s) in ascending order. ");
        System.out.println("2. View item(s) in descending order. ");
        System.out.println("3. View all item(s). ");
        System.out.println("4. Exit Application. ");
        int viewOpt = input.nextInt();
        //conditional statement
        switch (viewOpt) {
            case 1 -> ascendingView(inventory);
            case 2 -> descendingView(inventory);
            case 3 -> showAllItems(inventory);
            case 4 -> exitApplication();
            default -> {
                System.out.println("ERROR: Please enter a valid input.");
                retrieveItemsOptions(inventory);
            }
        }
    }

    public static void ascendingView(ArrayList<Inventory> inventory) {
        System.out.println("\n----------------------------------------------------------");
        System.out.println("MINI SHOP STOP INVENTORY ITEMS LIST - ASCENDING ORDER");
        System.out.println("-----------------------------------------------------------\n");
        inventory.sort(Inventory.AscendingNo);
        System.out.println(
                "\t\t\t\t\t" +
                        "ITEM NUMBER\t\t\t\t\t" +
                        "ITEM NAME\t\t\t\t\t" +
                        "ITEM PRICE\t\t\t\t\t" +
                        "DATE ADDED\t\t\t\t\t" +
                        "DATE MODIFIED");

        for(Inventory item : inventory) {
            int numberSystem = inventory.indexOf(item) + 1;
            System.out.println(
                    "No. "+numberSystem+ "\t\t\t\t\t"+item.SKU+
                            "\t\t\t\t\t" +item.itemName+
                            "\t\t\t\t\t" +item.itemPrice+
                            "\t\t\t\t\t" +item.dateAdded+
                            "\t\t\t\t\t" +item.dateModified);
        }

        System.out.println("\nASCENDING VIEW BY ITEM NUMBERS\n");
        reOption2(inventory);

    }

    public static void descendingView(ArrayList<Inventory> inventory) {
        System.out.println("\n----------------------------------------------------------");
        System.out.println("MINI SHOP STOP INVENTORY ITEMS LIST - DESCENDING ORDER");
        System.out.println("-----------------------------------------------------------\n");

        inventory.sort(Inventory.DescendingNo);
        System.out.println(
                "\t\t\t\t\t" +
                        "ITEM NUMBER\t\t\t\t\t" +
                        "ITEM NAME\t\t\t\t\t" +
                        "ITEM PRICE\t\t\t\t\t" +
                        "DATE ADDED\t\t\t\t\t" +
                        "DATE MODIFIED");

        for(Inventory item : inventory) {
            int numberSystem = inventory.indexOf(item) + 1;
            System.out.println(
                    "No. "+numberSystem+ "\t\t\t\t\t"+item.SKU+
                            "\t\t\t\t\t" +item.itemName+
                            "\t\t\t\t\t" +item.itemPrice+
                            "\t\t\t\t\t" +item.dateAdded+
                            "\t\t\t\t\t" +item.dateModified);
        }
        System.out.println("\nDESCENDING VIEW BY ITEM NUMBERS\n");
        reOption2(inventory);

    }

    public static void showAllItems(ArrayList<Inventory> inventory) {
        System.out.println("\n-------------------------------------");
        System.out.println("MINI SHOP STOP INVENTORY ITEMS LIST");
        System.out.println("-------------------------------------\n");

        System.out.println(
                "\t\t\t\t\t" +
                "ITEM NUMBER\t\t\t\t\t" +
                "ITEM NAME\t\t\t\t\t" +
                "ITEM PRICE\t\t\t\t\t" +
                "DATE ADDED\t\t\t\t\t" +
                "DATE MODIFIED");

        // loop through the ArrayList values and display it
            for (Inventory item : inventory) {

                int numberSystem = inventory.indexOf(item) + 1;
                System.out.println(
                        "No. "+numberSystem+ "\t\t\t\t\t"+item.SKU+
                        "\t\t\t\t\t" +item.itemName+
                        "\t\t\t\t\t" +item.itemPrice+
                        "\t\t\t\t\t" +item.dateAdded+
                        "\t\t\t\t\t" +item.dateModified);
                }
                // after showing the items, provide options to the user
                reOption2(inventory);
    }


    // DELETE ITEM METHOD

    public static void deleteItem(ArrayList<Inventory> inventory) {
        Scanner input = new Scanner(System.in);
        System.out.println("-----------------------------------");
        System.out.println("\t\t\tDELETE AN ITEM.");
        System.out.println("-----------------------------------");
        System.out.println("1. Proceed to delete item.");
        System.out.println("2. Go back to retrieve item page.");
        System.out.println("3. Go back to homepage.");
        System.out.println("4. Exit Application.");
        System.out.println("What would you like to do?<1-4>: ");
        int modOpt = input.nextInt();

        if (modOpt == 1) {
            // delete item
            System.out.println("Enter the item number you want to update: ");
            double itemNum = input.nextDouble();
            input.nextLine();
            // find the item with the corresponding item number and update the item name
            for (Inventory item : inventory) {
                if (item.SKU == itemNum) {
                    System.out.println("Do you really want to remove that specific item?<Y-N>: ");
                    String bol = input.nextLine();

                    if (bol.equalsIgnoreCase("Y")) {
                        inventory.remove(item);
                        System.out.println("\n-------------------------");
                        System.out.println("ITEM SUCCESSFULLY DELETED!");
                    }else {
                        System.out.println("\n-------------------------");
                        System.out.println("ITEM WAS NOT DELETED!");
                    }
                    System.out.println("---------------------------\n");
                    showAllItems(inventory);
                }
            }
            System.out.println("The item number you have entered does not exist. Please try again.");
            deleteItem(inventory);
        }else if(modOpt == 2) {
            retrieveItemsOptions(inventory);
        }else if (modOpt == 3) {
            homepage(inventory);
        }else if (modOpt == 4) {
            exitApplication();
        } else {
            System.out.println("ERROR: Please enter a valid input.");
            deleteItem(inventory);
        }
    }



    // EXIT APPLICATION
    public static void exitApplication() {
        System.out.println("Goodbye!");
        System.exit(0);
    }


    public static void reOption2(ArrayList<Inventory> inventory) {
        // current page variable
        Scanner input = new Scanner(System.in);
        System.out.println("\n1. Add an Item.");
        System.out.println("2. Update an Item.");
        System.out.println("3. Delete an Item.");
        System.out.println("4. Go back to retrieve item menu.");
        System.out.println("5. Go back to the homepage.");
        System.out.println("6. Exit Application");

        System.out.println("\nWhat would you like to do?<1-5>: ");
        int reOption = input.nextInt();

        switch (reOption) {
            case 1 -> addItemPage(inventory);
            case 2 -> updateItem(inventory);
            case 3 -> deleteItem(inventory);
            case 4 -> retrieveItemsOptions(inventory);
            case 5 -> homepage(inventory);
            case 6 -> exitApplication();
            default -> {
                System.out.println("ERROR: Please enter a valid input.");
                // go back to the retrieve item page
                reOption2(inventory);
            }
        }
    }
    // Re-Option
    public static void reOption(ArrayList<Inventory> inventory) {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Add another item. ");
        System.out.println("2. Go to retrieve item page. ");
        System.out.println("3. Go back to the homepage. ");
        System.out.println("4. Exit application. \n");
        System.out.println("What would you like to do?<1-4>: ");
        int option = input.nextInt();

        switch (option) {
            case 1 -> addItemPage(inventory);
            case 2 -> retrieveItemsOptions(inventory);
            case 3 -> homepage(inventory);
            case 4 -> exitApplication();
            default -> {
                System.out.println("ERROR: Please enter a valid input. ");
                reOption(inventory);
            }
        }
    }

}









class Inventory {
    int SKU;
    String itemName;
    double itemPrice;
    String dateAdded;
    String dateModified;

    //constructor
    Inventory(int SKU, String itemName, double itemPrice, String dateAdded, String dateModified){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.SKU = SKU;
    }
    public int getItemNo() { return SKU; }

    public static Comparator<Inventory> AscendingNo = (item1, item2) -> {
        int itemNo1 = item1.getItemNo();
        int itemNo2 = item2.getItemNo();
        // Ascending
        return itemNo1 - itemNo2;
    };

    public static Comparator<Inventory> DescendingNo = (item1, item2) -> {
        int itemNo1 = item1.getItemNo();
        int itemNo2 = item2.getItemNo();
        // Descending
        return itemNo2 - itemNo1;
    };

}


