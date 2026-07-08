package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Interactable;
import com.cfarz.escaperoom.interfaces.Inspectable;

import java.util.List;

public class Door implements Interactable, Inspectable {

    private final String name;
    private final String description;

    private final List<String> requiredStones;
    private boolean locked;

    /**
     * Creates a new locked door.
     *
     * @param name the name of the door.
     * @param description the description of the door.
     * @param requiredStones the list of stones needed to unlock the door.
     */
    public Door(String name, String description, List<String> requiredStones) {
        this.name = name;
        this.description = description;
        this.requiredStones = requiredStones;
        this.locked = true;
    }

    /**
     * Getter for the name of the door.
     *
     * @return the door name.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the door is locked.
     *
     * @return true if the door is locked.
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Getterr for the stones required to unlock the door.
     *
     * @return the list of needed stones.
     */
    public List<String> getRequiredStones() {
        return requiredStones;
    }

    /**
     * Inspects the door and returns its state.
     *
     * @return a description of the door and if it is locked.
     */
    @Override
    public String inspect() {

        if (locked) {
            return description + " The door is locked.";
        }

        return "The door is open.";
    }

    /**
     * Tries  to unlock the door.
     *
     * @param player the player.
     */
    @Override
    public void interact(Player player) {

        if (!locked) {
            System.out.println("The door is already open.");
            return;
        }

        if (canOpen(player)) {
            locked = false;
            System.out.println("The " + name + " unlocks!");
        } else {
            System.out.println("The door is locked. You need more Time Stones.");
        }
    }

    /**
     * Checks if the player has all items needed to open the door.
     *
     * @param player the player.
     * @return true if the player has all needed stones.
     */
    public boolean canOpen(Player player) {
        for (String stone : requiredStones) {
            if (!player.getInventory().hasItem(stone)) {
                return false;
            }
        }
        return true;
    }
}
