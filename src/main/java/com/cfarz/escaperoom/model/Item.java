package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Interactable;
import com.cfarz.escaperoom.interfaces.Inspectable;

public class Item implements Interactable, Inspectable {

    private String name;
    private String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    @Override
    public String inspect() {
        return description;
    }

    @Override
    public void interact(Player player) {
        System.out.println("You use the " + name + ".");
    }
}