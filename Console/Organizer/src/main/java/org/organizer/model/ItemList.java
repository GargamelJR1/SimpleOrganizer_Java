package org.organizer.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a list (internally represented as map) of items in Organizer.
 * It provides methods to add, remove, get and change items in the list.
 *
 * @version 1.3
 */
public class ItemList implements Serializable
{

    /**
     * This class represents an exception that is thrown when an item is not found in the map.
     */
    public class NoItemException extends Exception
    {
        /**
         * Constructs a new NoItemException with the specified error message.
         *
         * @param errorMessage The error message.
         */
        public NoItemException(String errorMessage) {
            super(errorMessage);
        }
    }

    /**
     * The list of items, stored in a map where the key is the item's id.
     */
    private final Map<Integer, Item> listItems = new HashMap<>();

    /**
     * Adds an item to the map.
     *
     * @param item The item to add.
     */
    public void addItem(Item item) {
        listItems.put(item.getId(), item);
    }

    /**
     * Removes an item from the map.
     *
     * @param itemId The id of the item to remove.
     * @throws NoItemException If no item with the given id is found.
     */
    public void removeItem(int itemId) throws NoItemException {
        // If the item is not found, throw an exception
        if (listItems.remove(itemId) == null) {
            throw new NoItemException("No such item");
        }
    }

    /**
     * Returns the list of items.
     *
     * @return The list of items.
     */
    public ArrayList<Item> getList() {
        // Convert the map to an ArrayList
        return listItems.values().stream().collect(ArrayList<Item>::new, ArrayList<Item>::add, ArrayList<Item>::addAll);
    }

    /**
     * Returns an item with the given id.
     *
     * @param itemId The id of the item to return.
     * @return The item with the given id.
     * @throws NoItemException If no item with the given id is found.
     */
    public Item getItem(int itemId) throws NoItemException {
        Item item = listItems.get(itemId);
        // If the item is not found, throw an exception
        if (item == null) {
            throw new NoItemException("No such item");
        }
        return item;
    }

    /**
     * Changes the name of an item in the map.
     *
     * @param itemId  The id of the item to change.
     * @param newName The new name for the item.
     * @throws NoItemException If no item with the given id is found.
     */
    public void changeItemName(int itemId, String newName) throws NoItemException {
        Item itemToEdit = listItems.get(itemId);
        // If the item is not found, throw an exception
        if (itemToEdit == null) {
            throw new NoItemException("No such item");
        }
        itemToEdit.setName(newName);
    }

    /**
     * Changes the description of an item in the map.
     *
     * @param itemId         The id of the item to change.
     * @param newDescription The new description for the item.
     * @throws NoItemException If no item with the given id is found.
     */
    public void changeItemDescription(int itemId, String newDescription) throws NoItemException {
        Item itemToEdit = listItems.get(itemId);
        if (itemToEdit == null) {
            throw new NoItemException("No such item");
        }
        itemToEdit.setDescription(newDescription);
    }

    /**
     * Removes all items from the map.
     */
    public void clearList() {
        listItems.clear();
    }

    /**
     * Returns true if the map is empty.
     *
     * @return True if the map is empty.
     */
    public boolean isEmpty() {
        return listItems.isEmpty();
    }
}