package org.organizerguipro.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the functionality of the ItemList class.
 */
class ItemListTest
{
    /**
     * The list of items to be tested.
     */
    private ItemList itemList;

    /**
     * This method sets up the testing environment before each test.
     */
    @BeforeEach
    public void setUp() {
        itemList = new ItemList();
    }

    /**
     * This parametrized test checks the addItem method of the ItemList class.
     * Parameters are provided by the @MethodSource annotation and the provideItems method.
     * Test check also null values provided by the @NullSource annotation.
     *
     * @param item The item to add to the list.
     */
    @ParameterizedTest
    @MethodSource("provideItems")
    @NullSource
    public void testAddItem(Item item) {
        if (item != null) {
            itemList.addItem(item);
            assertEquals(item, itemList.getList().get(0), "Item should be added to the list at index 0");
        }
        else {
            itemList.addItem(null);
            assertTrue(itemList.isEmpty(), "Null item should not be added to the list");
        }
    }

    /**
     * This parametrized test checks the removeItem method of the ItemList class.
     * Parameters are provided by the @MethodSource annotation and the provideItems method.
     *
     * @param item The item to add to the list and then remove.
     */
    @ParameterizedTest
    @MethodSource("provideItems")
    public void testRemoveItem(Item item) {
        itemList.addItem(item);
        try {
            itemList.removeItem(item.getId());
        } catch (ItemList.NoItemException e) {
            fail("NoItemException should not be thrown when item with given Id is found and removed");
        }
    }

    /**
     * This parametrized test checks the removeItem method of the ItemList class.
     * Parameters are provided by the @MethodSource annotation and the provideItems method.
     *
     * @param items The list of items to add to the itemList and then compare with the list returned by getList method.
     */
    @ParameterizedTest
    @MethodSource("provideListOfItems")
    public void testGetList(ArrayList<Item> items) {
        for (Item item : items) {
            itemList.addItem(item);
        }
        assertTrue(items.size() == itemList.getList().size() && items.containsAll(itemList.getList()) && itemList.getList().containsAll(items),
                "List should contains same elements as arraylist with items added to the list");
    }

    /**
     * This parametrized test checks the getItem method of the ItemList class.
     * Parameters are provided by the @MethodSource annotation and the provideItems method.
     * Method checks also if NoItemException is thrown when item with given Id is not found.
     *
     * @param item The item to add to the list and then compare with the item returned by getItem method.
     */
    @ParameterizedTest
    @MethodSource("provideItems")
    public void testGetItem(Item item) {
        itemList.addItem(item);
        try {
            assertEquals(item, itemList.getItem(item.getId()), "Item should be returned");
        } catch (ItemList.NoItemException e) {
            fail("NoItemException should not be thrown when item with given Id is found");
        }

        try {
            itemList.getItem(item.getId() + 1);
            fail("NoItemException should be thrown when item with given Id is not found");
        } catch (ItemList.NoItemException e) {
        }
    }

    /**
     * This parametrized test checks the changeItemName method of the ItemList class.
     * Parameters are provided by the @ValueSource annotation.
     * Test check also null and empty values provided by the @NullAndEmptySource annotation.
     *
     * @param name The new name to set for the item.
     */
    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "newName"})
    @NullAndEmptySource
    public void testChangeItemName(String name) {
        Item item = new Item("itemName", "itemDescription");
        itemList.addItem(item);
        try {
            itemList.changeItemName(item.getId(), name);
        } catch (ItemList.NoItemException e) {
            fail("NoItemException should not be thrown when item with given Id exists in list");
        }
        Item itemFromList = null;
        try {
            itemFromList = itemList.getItem(item.getId());
        } catch (ItemList.NoItemException e) {
            fail("NoItemException should not be thrown when item with given Id exists in list");
        }
        assertEquals(itemFromList.getName(), name, "Item after changed should be equal to " + name);
    }

    /**
     * This parametrized test checks the changeItemDescription method of the ItemList class.
     * Parameters are provided by the @ValueSource annotation.
     * Test check also null and empty values provided by the @NullAndEmptySource annotation.
     *
     * @param description The new description to set for the item.
     */
    @ParameterizedTest
    @ValueSource(strings = {" ", "  ", "newDescription"})
    @NullAndEmptySource
    public void testChangeItemDescription(String description) {
        Item item = new Item("itemName", "itemDescription");
        itemList.addItem(item);
        try {
            itemList.changeItemDescription(item.getId(), description);
        } catch (ItemList.NoItemException e) {
            fail("NoItemException should not be thrown when item with given Id exists in list");
        }
        Item itemFromList = null;
        try {
            itemFromList = itemList.getItem(item.getId());
        } catch (ItemList.NoItemException e) {
            fail("NoItemException should not be thrown when item with given Id exists in list");
        }
        assertEquals(itemFromList.getDescription(), description, "Item after changed should be equal to " + description);
    }

    /**
     * This test checks the clearList method of the ItemList class.
     */
    @Test
    public void testClearList() {
        Item item = new Item("itemName", "itemDescription");
        itemList.addItem(item);
        itemList.clearList();
        assertTrue(itemList.isEmpty(), "List should be empty after clearing");
    }

    /**
     * This test checks the isEmpty method of the ItemList class.
     * Method checks isEmpty method when list is empty and when it is not empty.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(itemList.isEmpty(), "List should be empty when initialized");
        Item item = new Item("itemName", "itemDescription");
        itemList.addItem(item);
        assertFalse(itemList.isEmpty(), "List should not be empty when item is added");
    }

    /**
     * This method provides a stream of items to be used in parametrized tests.
     *
     * @return A stream of items.
     */
    private static Stream<Item> provideItems() {
        return Stream.of(
                new Item("itemName1", "itemDescription1"),
                new Item("itemName2", "itemDescription2"),
                new Item("itemName3", "itemDescription3")
        );
    }

    /**
     * This method provides a stream of Arraylists of items to be used in parametrized tests.
     *
     * @return A stream of Arraylists of items.
     */
    private static Stream<ArrayList<Item>> provideListOfItems() {
        return Stream.of(new ArrayList<>(List.of(
                        new Item("itemName1", "itemDescription1"),
                        new Item("itemName2", "itemDescription2"),
                        new Item("itemName3", "itemDescription3"),
                        new Item("Janusz tel", "123456789"),
                        new Item("my phone number", "123-456-789")
                )),
                new ArrayList<>(List.of(
                        new Item("itemName1", "itemDescription1")
                )));
    }
}