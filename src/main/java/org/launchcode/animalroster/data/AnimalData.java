package org.launchcode.animalroster.data;

import org.launchcode.animalroster.models.Animal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AnimalData {
    private static final Map<Integer, Animal> animals = new HashMap<>();

    // obtain all animals
    public static Collection<Animal> getAll() {
        return animals.values();
    }

    // obtain ONE animal
    public static Animal getById(Integer id) {
        return animals.get(id);
    }

    // add an animal to the roster
    public static void add(Animal animal) {
        animals.put(animal.getId(), animal);
    }

    // remove an animal from the roster
    public static void remove(Integer id) {
        if (animals.containsKey(id)) {
            animals.remove(id);
        }
    }

}
