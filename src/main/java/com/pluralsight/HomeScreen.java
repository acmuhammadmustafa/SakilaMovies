package com.pluralsight;

public class HomeScreen {
    DisplayData displayData = new DisplayData();

    public void homeScreen(){
        System.out.printf("Select an option:\n1) Display all products\n2) Display all customers\n3) Display all categories\n0) Exit");
        System.out.println();
        int command = ConsoleHelper.promptForInt("Selection");
        switch (command){
            case 1 -> displayData.showProducts();
            case 2 -> displayData.showCustomers();
            case 3 -> displayData.showCategories();
            default -> {
                return;
            }
        }
    }
}
