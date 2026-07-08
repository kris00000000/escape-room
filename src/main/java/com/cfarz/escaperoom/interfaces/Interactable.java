package com.cfarz.escaperoom.interfaces;

import com.cfarz.escaperoom.model.Player;

/**
 * Represents an object that can be interacted with by a player.
 */
public interface Interactable {
    /**
     * Performs an interaction with the object.
     *
     * @param player the player.
     */
    void interact(Player player);
}
