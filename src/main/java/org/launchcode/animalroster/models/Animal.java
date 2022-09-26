package org.launchcode.animalroster.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Animal extends AbstractEntity {

    @NotNull(message="Name required!")
    @NotBlank(message="Do not leave blank!")
    @Size(min = 3, max = 50)
    private String name;

    @NotNull(message="Name required!")
    @NotBlank(message="Do not leave blank!")
    @Size(min = 3, max = 50)
    private String species;

    @Size(max = 250)
    private String description;

    private AnimalHandler handler;

    public Animal(String name, String species, String description, AnimalHandler handler) {
        this.name = name;
        this.species = species;
        this.description = description;
        this.handler = handler;
    }

    public Animal() { }

    public String getName() {
        return name;
    }

    public AnimalHandler getHandler() {
        return handler;
    }

    public void setHandler(AnimalHandler handler) {
        this.handler = handler;
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

    @Override
    public String toString() {
        return name;
    }
}
