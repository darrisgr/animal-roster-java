package org.launchcode.animalroster.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class AnimalDetails extends AbstractEntity {
    @NotNull(message="Name required!")
    @NotBlank(message="Do not leave blank!")
    @Size(min = 3, max = 50)
    private String species;

    @Size(max = 250)
    private String description;

    public AnimalDetails(String species, String description) {
        this.species = species;
        this.description = description;
    }
    public AnimalDetails() {}

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
}
