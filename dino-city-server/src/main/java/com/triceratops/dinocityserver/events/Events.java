package com.triceratops.dinocityserver.events;

public enum Events {
    SMALL_RAMPAGE("One of your animal got wild and attacked your staff, you may have killed him", 600, 0.001, 0.2),
    HUGE_RAMPAGE("Most of your animals escaped, it's a carnage, you'll lose money and will have to kill some", 4000, 0.1, 0.001),
    LOST_ITEM("Someone has misplaced the key of the laboratory, we will have to replace the door", 200, 0.0, 0.2),
    BROKEN_ITEM("Incompetent people, someone just broke one of the baby machines, it's going to be expensive", 2000, 0.0, 0.002);

    private String message;
    private double amount;
    private double killPercentage;
    private double eventChance;

    Events(String message, double amount, double killPercentage, double eventChance) {
        this.message = message;
        this.amount = amount;
        this.killPercentage = killPercentage;
        this.eventChance = eventChance;
    }

    public String getMessage() {
        return message;
    }

    public double getAmount() {
        return amount;
    }

    public double getKillPercentage() {
        return killPercentage;
    }

    public double getEventChance() {
        return eventChance;
    }
}
