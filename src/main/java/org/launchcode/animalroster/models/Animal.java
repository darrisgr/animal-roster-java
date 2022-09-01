package org.launchcode.animalroster.models;

public class Animal {

    private int id;
    private static int nextId = 1;
    private String name;
    private String species;
    private String description;

    public Animal(String name, String species, String description) {
        this.id = nextId;
        this.name = name;
        this.species = species;
        this.description = description;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
