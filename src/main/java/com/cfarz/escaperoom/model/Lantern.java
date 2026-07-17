package com.cfarz.escaperoom.model;

public class Lantern extends Item {

    private boolean lit;

    /**
     * Creates a new lantern.
     */
    public Lantern() {
        super("Lantern", "An old lantern. It is currently unlit.");
        this.lit = false;
    }

    /**
     * Checks if the light is lit.
     *
     * @return true if the lantern is lit.
     */
    public boolean isLit() {
        return lit;
    }

    /**
     * Lights the lantern and makes the room visible.
     *
     * @param room the room.
     */
    public void light(Room room) {
        if (!lit) {
            lit = true;
            room.setDark(false);
            System.out.println("The lantern made everything visible! You can now see what's in the room.");
        } else {
            System.out.println("The lantern is already lit.");
        }
    }

    /**
     * Interacts with the lantern .
     *
     * @param player the player.
     */
    @Override
    public void interact(Player player) {
        if (lit) {
            System.out.println("The lantern is already lit.");
        } else {
            System.out.println("The lantern needs matches to light it.");
        }
    }
}
