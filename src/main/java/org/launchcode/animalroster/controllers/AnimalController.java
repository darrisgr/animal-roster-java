package org.launchcode.animalroster.controllers;

import org.launchcode.animalroster.data.AnimalData;
import org.launchcode.animalroster.models.Animal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("title", "Create Animal");
        return "animals/add";
    }

    @PostMapping("add")
    public String processAddAnimalForm(@ModelAttribute Animal newAnimal) {
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
