package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Interactable;
import com.cfarz.escaperoom.interfaces.Inspectable;

public class Item implements Interactable, Inspectable {

    private final String name;
    private String description;
    private boolean visibleInDark;

    /**
     * Creates a new item.
     *
     * @param name the name of the item.
     * @param description the description of the item.
     */
    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        this.visibleInDark = false;
    }

    /**
     * Getter for the name of the item.
     *
     * @return the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Checks if the item is visible in the dark.
     *
     * @return true if the item is visible in the dark.
     */
    public boolean isVisibleInDark() {
        return visibleInDark;
    }

    /**
     * Setter if the item is visible in the dark.
     *
     * @param visibleInDark if the item is seen in the dark.
     */
    public void setVisibleInDark(boolean visibleInDark) {
        this.visibleInDark = visibleInDark;
    }

    /**
     * Inspects the item.
     *
     * @return the description
     */
    @Override
    public String inspect() {
        return description;
    }

    /**
     * Interacts with the item.
     *
     * @param player the player.
     */
    @Override
    public void interact(Player player) {
        System.out.println("You use the " + name + ".");
    }
}
