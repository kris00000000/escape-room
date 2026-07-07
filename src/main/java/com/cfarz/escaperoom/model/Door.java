package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Interactable;
import com.cfarz.escaperoom.interfaces.Inspectable;

import java.util.List;

public class Door implements Interactable, Inspectable {

    private final String name;
    private final String description;

    private final List<String> requiredStones;

    private boolean locked;

    public Door(String name, String description, List<String> requiredStones) {
        this.name = name;
        this.description = description;
        this.requiredStones = requiredStones;
        this.locked = true;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return locked;
    }


    public List<String> getRequiredStones() {
        return requiredStones;
    }

    @Override
    public String inspect() {

        if (locked) {
            return description + " The door is locked.";
        }

        return "The door is open.";
    }

    @Override
    public void interact(Player player) {

        if (!locked) {
            System.out.println("The door is already open.");
            return;
        }

        if (canOpen(player)) {

            locked = false;

            System.out.println(
                "The " + name + " unlocks!"
            );

        } else {

            System.out.println(
                "The door is locked. You need more Time Stones."
            );
        }
    }

    public boolean canOpen(Player player) {

        for (String stone : requiredStones) {

            if (!player.getInventory().hasItem(stone)) {
                return false;
            }

        }

        return true;
    }

}