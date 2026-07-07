package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Inspectable;

import java.util.ArrayList;
import java.util.List;

public class Room implements Inspectable {

    private String name;
    private String description;

    private List<Item> items;
    private List<Puzzle> puzzles;

    private Machine machine;
    private Door door;

    private boolean dark;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;

        this.items = new ArrayList<>();
        this.puzzles = new ArrayList<>();

        this.dark = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
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

    public void addPuzzle(Puzzle puzzle) {
        puzzles.add(puzzle);
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Machine getMachine() {
        return machine;
    }

    public Door getDoor() {
        return door;
    }

    public List<Item> getItems() {
        return items;
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    @Override
    public String inspect() {

        if (dark) {
            return "It is too dark to see anything.";
        }

        return description;
    }

    public void displayItems() {

        if (items.isEmpty()) {
            System.out.println("There are no visible items here.");
            return;
        }

        System.out.println("Items in the room:");

        for (Item item : items) {
            System.out.println("- " + item.getName());
        }
    }
}