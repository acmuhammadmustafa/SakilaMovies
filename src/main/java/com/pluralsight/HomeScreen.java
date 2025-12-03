package com.pluralsight;

public class HomeScreen {
    DisplayData displayData = new DisplayData();

    public void homeScreen(){
        System.out.print("Welcome!" +
                "Enter the first and last name of the actor you'd like to list: ");
        System.out.println();
        String command = ConsoleHelper.promptForString("Actor's Name");
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
