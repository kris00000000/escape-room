package com.cfarz.escaperoom.model;

public class Player {

    private final String name;
    private Room currentRoom;
    private final Inventory inventory;

    /**
     * Creates a  player with a name and starting room.
     *
     * @param name the player's name.
     * @param startingRoom the room where the player starts.
     */
    public Player(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.inventory = new Inventory();
    }

    /**
     * Getter for the player's name.
     *
     * @return the player's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the room where the player is currently located.
     *
     * @return the player's current room.
     */
    public Room getCurrentRoom() {
        return currentRoom;
    }

    /**
     * Getter for the player's inventory.
     *
     * @return the player's inventory.
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Moves the player to a new room.
     *
     * @param newRoom the room.
     * @return true if the player moved.
     */
    public boolean move(Room newRoom) {
        if (newRoom == null) {
            System.out.println("You cannot go there.");
            return false;
        }

        this.currentRoom = newRoom;

        System.out.println("You entered the " + newRoom.getName() + " room.");
        return true;
    }

    /**
     * Adds item to the player's inventory.
     *
     * @param itemName the name of the item to take
     */
    public void takeItem(String itemName) {
        Item item = currentRoom.removeItem(itemName);

        if (item != null) {
            inventory.addItem(item);
            System.out.println("You picked up " + item.getName());
        } else {
            System.out.println("That item isn't here.");
        }
    }

    /**
     * Drops an item from the player's inventory.
     *
     * @param itemName the item.
     */
    public void dropItem(String itemName) {

        Item item = inventory.removeItem(itemName);

        if (item != null) {
            currentRoom.addItem(item);

            System.out.println("You dropped " + item.getName());
        } else {
            System.out.println("You don't have that item.");
        }
    }

    /**
     * Uses an item from the player's inventory.
     *
     * @param itemName the item.
     */
    public void useItem(String itemName) {
        Item item = inventory.getItem(itemName);

        if (item != null) {
            item.interact(this);
        } else {
            System.out.println("You don't have that item.");
        }
    }

    /**
     * Checks if the player has a specific item.
     *
     * @param itemName the name of the item.
     * @return true if the player has the item.
     */
    public boolean hasItem(String itemName) {
        return inventory.hasItem(itemName);
    }

    /**
     * Inspects an object.
     *
     * @param objectName the object.
     */
    public void inspect(String objectName) {
        for (Item item : currentRoom.getItems()) {
            if (item.getName().equalsIgnoreCase(objectName)) {
                System.out.println(item.inspect());
                return;
            }
        }

        if (currentRoom.getMachine() != null && currentRoom.getMachine().getName().equalsIgnoreCase(objectName)) {
            System.out.println(currentRoom.getMachine().inspect());
            return;
        }

        if (currentRoom.getDoor() != null && currentRoom.getDoor().getName().equalsIgnoreCase(objectName)) {
            System.out.println(currentRoom.getDoor().inspect());
            return;
        }

        System.out.println("You cannot inspect that.");
    }
}
