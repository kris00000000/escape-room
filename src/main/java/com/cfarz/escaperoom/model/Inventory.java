package com.cfarz.escaperoom.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Item> items;

    /**
     * Creates an empty inventory.
     */
    public Inventory() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item the item.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes an item from the inventory.
     *
     * @param itemName the name of the item.
     * @return the removed item.
     */
    public Item removeItem(String itemName) {
        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);

            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(i);
                return item;
            }
        }
        return null;
    }

    /**
     * Checks if the inventory has an item.
     *
     * @param itemName the name of the item.
     * @return true if the item exists in the inventory.
     */
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for an item from the inventory.
     *
     * @param itemName the name of the item.
     * @return the item if it exists.
     */
    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Shows all items stored in the inventory.
     */
    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
            return;
        }

        System.out.println("Inventory:");

        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
    }

    /**
     * Getter for all items in the inventory.
     *
     * @return the list of items.
     */
    public List<Item> getItems() {
        return items;
    }
}
