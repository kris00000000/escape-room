package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Inspectable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Room implements Inspectable {

    private String name;
    private String description;

    private List<Item> items;
    private List<Puzzle> puzzles;

    private Machine machine;
    private Door door;

    private boolean dark;
    private Map<String, Room> exits;

    /**
     * Construct to create a room with a name and a description.
     *
     * @param name the name of the room.
     * @param description the description of the room.
     */
    public Room(String name, String description) {

        this.name = name;
        this.description = description;
        this.items = new ArrayList<>();
        this.puzzles = new ArrayList<>();
        this.exits = new HashMap<>();
        this.dark = false;

    }

    /**
     * Getter for the name of the room.
     *
     * @return the name of the room.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the description of the room.
     *
     * @return the description of the room.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Adds an item to the room.
     *
     * @param item the item we want to add.
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Removes an item from the room.
     *
     * @param itemName the name of the item we wanna remove.
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
     * Checks if the room has an item.
     *
     * @param itemName the name of the item.
     * @return true if the item exists in the room.
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
     * Adds a puzzle to the room.
     *
     * @param puzzle the puzzle to add.
     */
    public void addPuzzle(Puzzle puzzle) {
        puzzles.add(puzzle);
    }

    /**
     * Getter for all the puzzles in the room.
     *
     * @return the list of puzzles in the room
     */
    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    /**
     * Setter for the machine in the room.
     *
     * @param machine the machine we want to set in the room.
     */
    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    /**
     * Setter for the door in the room.
     *
     * @param door the door we want to assign to the room.
     */
    public void setDoor(Door door) {
        this.door = door;
    }

    /**
     * Getter for the machine in the room.
     *
     * @return the machine.
     */
    public Machine getMachine() {
        return machine;
    }

    /**
     * Getter for the door in the room.
     *
     * @return the door.
     */
    public Door getDoor() {
        return door;
    }

    /**
     * Getter for all of items in the room.
     *
     * @return the list of items.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Adds an exit from a room.
     *
     * @param direction the direction of the exit.
     * @param room the room connected to this exit.
     */
    public void addExit(String direction, Room room) {
        exits.put(direction.toLowerCase(), room);
    }

    /**
     * Getter for the room connected.
     *
     * @param direction the direction of the exit.
     * @return the connected room.
     */
    public Room getExit(String direction) {

        if (direction == null) {
            return null;
        }

        return exits.get(direction.toLowerCase());
    }

    /**
     * Getter for all exits from this room.
     *
     * @return a map containing exits and connected rooms.
     */
    public Map<String, Room> getExits() {
        return exits;
    }

    /**
     * Checks if the room is dark.
     *
     * @return true if the room is dark.
     */
    public boolean isDark() {
        return dark;
    }

    /**
     * Setter for the darkness state of the room.
     *
     * @param dark the new state.
     */
    public void setDark(boolean dark) {
        this.dark = dark;
    }

    /**
     * Inspects the room and returns the description.
     *
     * @return the room description, or a message if the room is too dark.
     */
    @Override
    public String inspect() {

        if (dark) {
            return "It is too darkª You can't see anything. Maybe try lighting up something.";
        }

        return description;
    }

    /**
     * Shows all visible items in the room.
     * Items hidden if the room is dark are not shown.
     */
    public void displayItems() {

        if (items.isEmpty()) {
            System.out.println("There are no items here.");
            return;
        }

        System.out.println("The items in the room are:");

        for (Item item : items) {
            if (dark) {
                if (item.isVisibleInDark()) {
                    System.out.println("- " + item.getName());
                }
            } else {
                System.out.println("- " + item.getName());
            }
        }
    }
}
