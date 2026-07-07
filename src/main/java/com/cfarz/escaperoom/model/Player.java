package com.cfarz.escaperoom.model;

public class Player {

    private final String name;

    private Room currentRoom;
    private final Inventory inventory;

    public Player(String name, Room startingRoom) {
        this.name = name;
        this.currentRoom = startingRoom;
        this.inventory = new Inventory();
    }

    public String getName() {
        return name;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void move(Room newRoom) {
        this.currentRoom = newRoom;
    }

    public void takeItem(String itemName) {
        Item item = currentRoom.removeItem(itemName);

        if (item != null) {
            inventory.addItem(item);
            System.out.println("You picked up " + item.getName());
        } else {
            System.out.println("That item isn't here.");
        }
    }

    public void dropItem(String itemName) {
        Item item = inventory.removeItem(itemName);

        if (item != null) {
            currentRoom.addItem(item);
            System.out.println("You dropped " + item.getName());
        } else {
            System.out.println("You don't have that item.");
        }
    }

    public void useItem(String itemName) {
        Item item = inventory.getItem(itemName);

        if (item != null) {
            item.interact(this);
        } else {
            System.out.println("You don't have that item.");
        }
    }
}