package org.organizerweb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a list (internally represented as map) of items in Organizer.
 * It provides methods to add, remove, get and change items in the list.
 *
 * @version 1.6
 */
public class ItemList implements Serializable
{

    private static ItemList instance;

    private ItemList() {
    }

    public static ItemList getInstance() {
        if (instance == null)
            instance = new ItemList();
        return instance;
    }

    /**
     * Logger for this class.
     */
    private static final Logger logger = new Logger();

    /**
     * Get the logger for this class.
     */
    public static Logger getLogger() {
        return logger;
    }

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
        if (item != null) {
            listItems.put(item.getId(), item);
            logger.log("Item added with id: " + item.getId() + ", name: " + item.getName() + ", description: " + item.getDescription());
        }
        else {
            logger.log("Item not added: null");
        }
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
            logger.log("Item with id: " + itemId + " not found");
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
        logger.log("List returned");
        return listItems.values().stream().collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
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
            logger.log("Item with id: " + itemId + " not found");
            throw new NoItemException("No such item");
        }
        logger.log("Item id: " + item.getId() + " returned");
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
            logger.log("Item with id: " + itemId + " not found");
            throw new NoItemException("No such item");
        }
        itemToEdit.setName(newName);
        logger.log("Item id: " + itemToEdit.getId() + " changed name: " + itemToEdit.getName());
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
            logger.log("Item with id: " + itemId + " not found");
            throw new NoItemException("No such item");
        }
        itemToEdit.setDescription(newDescription);
        logger.log("Item id: " + itemToEdit.getId() + " changed description: " + itemToEdit.getDescription());
    }

    /**
     * Removes all items from the map.
     */
    public void clearList() {
        listItems.clear();
        logger.log("List cleared");
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