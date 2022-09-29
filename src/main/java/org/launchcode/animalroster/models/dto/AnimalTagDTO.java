package org.launchcode.animalroster.models.dto;

import org.launchcode.animalroster.models.Animal;
import org.launchcode.animalroster.models.Tag;

import javax.validation.constraints.NotNull;

public class AnimalTagDTO {

    @NotNull
    private Animal animal;

    @NotNull
    private Tag tag;

    public AnimalTagDTO() {}

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
