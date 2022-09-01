package org.launchcode.animalroster.models;

public enum AnimalHandler {
    GERARD("Gerard Darris"),
    LEAH("Leah Schere"),
    HANK("Hank Hantak"),
    TOM("Tom Hang");

    private final String displayName;

    public String getDisplayName() {
        return displayName;
    }

    AnimalHandler(String displayName) {
        this.displayName = displayName;
    }
}
