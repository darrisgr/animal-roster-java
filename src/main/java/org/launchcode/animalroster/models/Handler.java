package org.launchcode.animalroster.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
public class Handler extends AbstractEntity {

    @NotNull(message="Name required!")
    @NotBlank(message="Do not leave blank!")
    @Size(min = 3, max = 50)
    private String name;

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

    @Override
    public String toString() {
        return name;
    }
}
