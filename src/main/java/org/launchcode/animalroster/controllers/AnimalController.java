package org.launchcode.animalroster.controllers;

import org.launchcode.animalroster.data.AnimalData;
import org.launchcode.animalroster.models.Animal;
import org.launchcode.animalroster.models.AnimalHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("animals")
public class AnimalController {

    @GetMapping
    public String displayAllAnimals(Model model) {
        model.addAttribute("title", "All Animals");
        model.addAttribute("animals", AnimalData.getAll());
        return "animals/index";
    }

    @GetMapping("add")
    public String displayAddAnimalForm(Model model) {
        model.addAttribute("title", "Add Animal");
        model.addAttribute("animal", new Animal());
        model.addAttribute("handlers", AnimalHandler.values());
        return "animals/add";
    }

    @PostMapping("add")
    public String processAddAnimalForm(@ModelAttribute @Valid Animal newAnimal,
                                       Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Animal");
            return "animals/add";
        }
        AnimalData.add(newAnimal);
        return "redirect:";
    }

    @GetMapping("remove")
    public String displayRemoveAnimalForm(Model model) {
        model.addAttribute("title", "Remove Animals");
        model.addAttribute("animals", AnimalData.getAll());
        return "animals/remove";
    }

    @PostMapping("remove")
    public String processRemoveAnimalForm(@RequestParam(required = false) int[] animalIds) {
        if (animalIds != null) {
            for (int id : animalIds) {
                AnimalData.remove(id);
            }
        }

        return "redirect:";
    }
}
