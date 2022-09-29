package org.launchcode.animalroster.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Handler extends AbstractEntity {

    @NotNull(message="Name required!")
    @NotBlank(message="Do not leave blank!")
    @Size(min = 3, max = 50)
    private String name;

    @OneToMany
    @JoinColumn(name="handler_id")
    private final List<Animal> animals = new ArrayList<>();

    public Handler(String name) {
        this.name = name;
    }

    public Handler() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    @Override
    public String toString() {
        return name;
    }
}
