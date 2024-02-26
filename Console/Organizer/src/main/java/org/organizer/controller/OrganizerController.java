package org.organizer.controller;

import org.organizer.model.Item;
import org.organizer.model.ItemList;
import org.organizer.view.OrganizerView;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class represents a controller for the Organizer application.
 * It handles user input and interacts with the model and view.
 *
 * @version 1.5
 */
public class OrganizerController
{
    /**
     * The view of the application.
     * It is not final because in the future there might be more than one view, and it will be possible to switch between them.
     */
    private OrganizerView view;

    /**
     * The model of the application.
     */
    private ItemList model;

    /**
     * A flag indicating whether the application should exit.
     */
    private boolean exitProgram = false;

    /**
     * A scanner for reading user input.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new OrganizerController with the specified view and model.
     *
     * @param organizerView The view of the application.
     * @param itemList      The model of the application.
     */
    public OrganizerController(OrganizerView organizerView, ItemList itemList) {
        this.view = organizerView;
        this.model = itemList;
    }

    /**
     * Runs the application. It displays the menu, handles user input and interacts with the model and view.
     *
     * @return A flag indicating whether the application should exit.
     */
    public boolean runApp() {
        view.showMenu();

        int choice = getIntPositiveBelowMax(9);

        if (choice == -1) {
            view.showErrorMessageInvalidInput();
            scanner.nextLine();
        }

        switch (choice) {
            // Add item
            case 1 -> {
                view.showAddItemMenu();
                addItem();
            }
            // Remove item
            case 2 -> {
                view.showRemoveItemMenu();
                try {
                    model.removeItem(getIntInput());
                    view.showSuccessMessage();
                } catch (ItemList.NoItemException e) {
                    view.showExceptionMessage(e);
                }
            }
            // Show one item
            case 3 -> {
                view.showItemMenu();
                try {
                    view.showItem(model.getItem(getIntInput()));
                } catch (Exception e) {
                    view.showExceptionMessage(e);
                }
            }
            // Show all items
            case 4 -> view.showItemList(model);
            // Edit item
            case 5 -> {
                view.showEditMenu();
                editItem();
            }
            // Save to file
            case 6 -> {
                view.showSaveMenu();
                saveDataToFile(getStringInput());
            }
            // Load from file
            case 7 -> {
                view.showLoadMenu();
                loadDataFromFile(getStringInput());
            }
            // Clear list
            case 8 -> model.clearList();
            // Exit
            case 9 -> exitProgram = true;
        }
        return !this.exitProgram;
    }

    /**
     * Edits an item in the model.
     * The user is prompted to choose whether to edit the name or the description.
     */
    private void editItem() {
        int editChoice;
        do {
            editChoice = getIntPositiveBelowMax(2);
            if (editChoice != -1) {
                try {
                    if (editChoice == 1) {
                        view.showEditNameMenu();
                        model.changeItemName(getIntInput(), getStringInput());
                        view.showSuccessMessage();
                    }
                    else if (editChoice == 2) {
                        view.showEditDescriptionMenu();
                        model.changeItemDescription(getIntInput(), getStringInput());
                        view.showSuccessMessage();
                    }
                } catch (ItemList.NoItemException e) {
                    view.showExceptionMessage(e);
                }
            }
            else {
                view.showErrorMessageInvalidInput();
            }
        } while (editChoice == -1);
    }

    /**
     * Adds an item to the model.
     */
    private void addItem() {
        scanner.nextLine();
        String name = scanner.nextLine();
        String description = scanner.nextLine();
        Item item = new Item(name, description);
        model.addItem(item);
    }

    /**
     * Gets an integer input from the user.
     *
     * @return The integer input from the user.
     */
    private int getIntInput() {
        int input;
        // If the input is not an integer, return -1
        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            return -1;
        }
        return input;
    }

    /**
     * This method retrieves an integer input from the user and validates if it is within a specified range (1 to max).
     * If the input is not within the range, it returns -1.
     *
     * @param max The maximum value of the range (inclusive).
     * @return The user's input if it is within the range, -1 otherwise.
     */
    private int getIntPositiveBelowMax(int max) {
        int input = getIntInput();
        if (input < 1 || input > max) {
            return -1;
        }
        return input;
    }

    /**
     * Gets a string input from the user.
     *
     * @return The string input from the user.
     */
    private String getStringInput() {
        scanner.nextLine();
        return scanner.nextLine();
    }

    /**
     * Loads data from a file into the model.
     *
     * @param inputPath The path of the file to load data from.
     * @return true if the data was successfully loaded, false otherwise.
     */
    public boolean loadDataFromFile(String inputPath) {
        try {
            FileInputStream readData = new FileInputStream(inputPath);
            ObjectInputStream readStream = new ObjectInputStream(readData);

            // Entire model is loaded from file
            model = (ItemList) readStream.readObject();
            readStream.close();
            Item.setNextId(model.getItem(model.getList().size() - 1).getId() + 1);
            view.showSuccessMessage();
            return true;
        } catch (IOException | ClassNotFoundException | ItemList.NoItemException e) {
            view.showExceptionMessage(e);
            return false;
        }
    }

    /**
     * Saves the data in the model to a file.
     *
     * @param outputPath The path of the file to save data to.
     * @return true if the data was successfully saved, false otherwise.
     */
    public boolean saveDataToFile(String outputPath) {
        try {
            FileOutputStream writeData = new FileOutputStream(outputPath);
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            // Entire model is saved to file
            writeStream.writeObject(model);
            writeStream.close();
            view.showSuccessMessage();
            return true;
        } catch (IOException e) {
            view.showExceptionMessage(e);
            return false;
        }
    }

    /**
     * Adds sample data to the model.
     */
    public void addSampleData() {
        model.addItem(new Item("name1", "description1"));
        model.addItem(new Item("contact1", "tel: 123456789"));
        model.addItem(new Item("note", "do not forget to buy milk"));
        model.addItem(new Item("name2", "description2"));
    }
}
