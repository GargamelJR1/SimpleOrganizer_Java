package org.organizerweb.model;

import java.io.Serializable;

/**
 * Represents an item in the Organizer.
 * Each item has an id, a name, and a description.
 *
 * @version 1.1
 */
public class Item implements Serializable
{
    /**
     * The unique identifier for this item.
     */
    private final int id;

    /**
     * The name of this item.
     */
    private String name;

    /**
     * A description of this item.
     */
    private String description;

    /**
     * The next id to be assigned.
     */
    static int nextId = 0;

    /**
     * Constructs a new Item with the specified name and description.
     * The id of the new item is automatically set.
     *
     * @param name        The name of the new item.
     * @param description The description of the new item.
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        id = nextId++;
    }

    /**
     * Returns the name of this item.
     *
     * @return The name of this item.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns the description of this item.
     *
     * @return The description of this item.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the name of this item to the specified value.
     *
     * @param newName The new name for this item.
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Changes the description of this item to the specified value.
     *
     * @param newDescription The new description for this item.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Returns the id of this item.
     *
     * @return The id of this item.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Sets the next id to the specified value.
     *
     * @param newNextId The new next id.
     */
    static public void setNextId(int newNextId) {
        nextId = newNextId;
    }
}
