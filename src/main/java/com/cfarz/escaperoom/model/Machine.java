package com.cfarz.escaperoom.model;

import com.cfarz.escaperoom.interfaces.Interactable;
import com.cfarz.escaperoom.interfaces.Inspectable;

public class Machine implements Interactable, Inspectable {

    private final String name;
    private final String description;
    private final String code;

    private boolean repaired;
    private Item rewardStone;


    public Machine(String name, String description, String code, Item rewardStone) {
        this.name = name;
        this.description = description;
        this.code = code;
        this.rewardStone = rewardStone;
        this.repaired = false;
    }

    public String getName() {
        return name;
    }

    public boolean isRepaired() {
        return repaired;
    }

    public Item getRewardStone() {
        return rewardStone;
    }

    @Override
    public String inspect() {

        if (repaired) {
            return name + " has been repaired and is working again.";
        }

        return description;
    }

    @Override
    public void interact(Player player) {

        if (repaired) {
            System.out.println("The machine is already repaired.");
            return;
        }

        System.out.println("The " + name + " requires a 4 digit code.");
    }

    public boolean repair(String enteredCode, Player player) {

        if (repaired) {
            System.out.println("The machine is already repaired.");
            return false;
        }

        if (enteredCode.equals(code)) {

            repaired = true;

            System.out.println(
                "The " + name + " starts working again!"
            );

            if (rewardStone != null) {
                player.getInventory().addItem(rewardStone);

                System.out.println(
                    "You received the " + rewardStone.getName()
                );
            }

            return true;
        }

        System.out.println("Incorrect code.");
        return false;
    }
}