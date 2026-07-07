package com.cfarz.escaperoom.model;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Item> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public Item removeItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                items.remove(item);
                return item;
            }
        }
        return null;
    }
    
    public boolean hasItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return true;
            }
        }
        return false;
    }

   
    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    
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


    public List<Item> getItems() {
        return items;
    }
}