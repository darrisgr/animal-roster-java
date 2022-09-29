package org.launchcode.animalroster.models;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Animal extends AbstractEntity {

    @NotNull(message="Name required!")
    @NotBlank(message="Do not leave blank!")
    @Size(min = 3, max = 50)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private AnimalDetails details;

    @ManyToOne
    @NotNull(message = "Give your animal a handler! :)")
    private Handler handler;


    public Animal(String name, Handler handler) {
        this.name = name;
        this.handler = handler;
    }

    public Animal() { }

    public String getName() {
        return name;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalDetails getDetails() {
        return details;
    }

    public void setDetails(AnimalDetails details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return name;
    }
}
