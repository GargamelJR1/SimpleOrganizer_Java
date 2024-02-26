package org.organizer.view;

import org.organizer.model.Item;
import org.organizer.model.ItemList;

/**
 * This class represents a view for the Organizer application.
 * It provides methods to display the application's menu and items.
 *
 * @version 1.5
 */
public class OrganizerView
{

    /**
     * Displays a list of items.
     *
     * @param itemList The list of items to be displayed.
     */
    public void showItemList(ItemList itemList) {
        // If the list is not empty, print all items to the console.
        if (!itemList.isEmpty()) {
            System.out.println("Items:\n");
            for (Item item : itemList.getList()) {
                System.out.println("id: " + item.getId() + "\t" + "name: " + item.getName() + "\n"
                        + "description: " + item.getDescription() + "\n=======================");
            }
            System.out.println();
        }
        else {
            System.out.println("List is empty");
            System.out.println();
        }
    }

    /**
     * Displays a single item.
     *
     * @param item The item to be displayed.
     */
    public void showItem(Item item) {
        System.out.println("Item:");
        System.out.println("Name: " + item.getName());
        System.out.println("Description: " + item.getDescription());
        System.out.println();
    }

    /**
     * Displays the main menu.
     */
    public void showMenu() {
        System.out.println("Menu:");
        System.out.println("1. Add item");
        System.out.println("2. Remove item");
        System.out.println("3. Show item");
        System.out.println("4. Show all items");
        System.out.println("5. Edit item");
        System.out.println("6. Save to file");
        System.out.println("7. Load from file");
        System.out.println("8. Clear list");
        System.out.println("9. Exit");
    }

    /**
     * Displays the menu for adding an item.
     */
    public void showAddItemMenu() {
        System.out.println("Add item");
        System.out.println("Enter name and description in separate lines:");
    }

    /**
     * Displays the menu for removing an item.
     */
    public void showRemoveItemMenu() {
        System.out.println("Remove item");
        System.out.println("Enter item id:");
    }

    /**
     * Displays the menu for showing an item.
     */
    public void showItemMenu() {
        System.out.println("Show item");
        System.out.println("Enter item id:");
    }

    /**
     * Displays the menu for saving to a file.
     */
    public void showSaveMenu() {
        System.out.println("Save to file");
        System.out.println("Enter file name:");
    }

    /**
     * Displays the menu for loading from a file.
     */
    public void showLoadMenu() {
        System.out.println("Load from file");
        System.out.println("Enter file name:");
    }

    /**
     * Displays the menu for editing an item.
     */
    public void showEditMenu() {
        System.out.println("Edit item");
        System.out.println("1. Edit name");
        System.out.println("2. Edit description");
    }

    /**
     * Displays the menu for editing the name of an item.
     */
    public void showEditNameMenu() {
        System.out.println("Edit name");
        System.out.println("Enter item id and new name in separate lines:");
    }

    /**
     * Displays the menu for editing the description of an item.
     */
    public void showEditDescriptionMenu() {
        System.out.println("Edit description");
        System.out.println("Enter item id and new description in separate lines:");
    }

    /**
     * Displays a success message.
     */
    public void showSuccessMessage() {
        System.out.println("Success");
        System.out.println();
    }

    /**
     * Displays an exception message.
     *
     * @param e The exception to display the message from.
     */
    public void showExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
        System.out.println();
    }

    /**
     * Displays an invalid input error message.
     */
    public void showErrorMessageInvalidInput() {
        ShowErrorMessage("Invalid input");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    private void ShowErrorMessage(String message) {
        System.out.println("Error: " + message);
        System.out.println();
    }
}
