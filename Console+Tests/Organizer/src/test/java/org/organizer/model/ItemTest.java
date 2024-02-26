package org.organizer.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class tests the functionality of the Item class.
 *
 * @version 1.0
 */
class ItemTest
{
    /**
     * The item to be tested.
     */
    private Item item;

    /**
     * This method sets up the testing environment before each test.
     */
    @BeforeEach
    public void setUp() {
        item = new Item("itemName", "itemDescription");
    }

    /**
     * This test checks the getName method of the Item class.
     */
    @Test
    public void testGetName() {
        assertEquals("itemName", item.getName(), "Item name should be itemName");
    }

    /**
     * This test checks the getDescription method of the Item class.
     */
    @Test
    public void testGetDescription() {
        assertEquals("itemDescription", item.getDescription(), "Item description should be itemDescription");
    }

    /**
     * This parametrized test checks the setName method of the Item class.
     * Parameters are provided by the @ValueSource annotation.
     * Test check also null and empty values provided by the @NullAndEmptySource annotation.
     *
     * @param newName The new name to set for the item.
     */
    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "newName"})
    @NullAndEmptySource
    public void testSetName(String newName) {
        item.setName(newName);
        assertEquals(newName, item.getName(), "Item name should be changed to " + newName);
    }

    /**
     * This parametrized test checks the setDescription method of the Item class.
     * Parameters are provided by the @ValueSource annotation.
     * Test check also null values provided by the @NullSource annotation.
     *
     * @param newDescription The new description to set for the item.
     */
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "newDescription"})
    @NullSource
    public void testSetDescription(String newDescription) {
        item.setDescription(newDescription);
        assertEquals(newDescription, item.getDescription(), "Item description should be changed to " + newDescription);
    }

    /**
     * This test checks the getId method of the Item class.
     */
    @Test
    public void testGetId() {
        assertEquals(0, item.getId(), "First created item Id should be 0");
        Item item2 = new Item("itemName", "itemDescription");
        assertEquals(1, item2.getId(), "Second created item Id should be 1");
    }

    /**
     * This parametrized test checks the setNextId method of the Item class.
     * Parameters are provided by the @ValueSource annotation.
     *
     * @param nextItemId The next item ID to set.
     */
    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1, 2, 3, 1500})
    public void testSetNextId(int nextItemId) {
        Item.setNextId(nextItemId);
        Item item2 = new Item("itemName", "itemDescription");
        assertEquals(nextItemId, item2.getId(), "New item Id should be equal to " + nextItemId);
    }
}