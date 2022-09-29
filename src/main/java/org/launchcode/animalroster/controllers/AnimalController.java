package org.launchcode.animalroster.controllers;

import org.launchcode.animalroster.data.AnimalRepository;
import org.launchcode.animalroster.data.HandlerRepository;
import org.launchcode.animalroster.models.Animal;
import org.launchcode.animalroster.models.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("animals")
public class AnimalController {

    @Autowired
    AnimalRepository animalRepository;

    @Autowired
    HandlerRepository handlerRepository;

    // localhost:8080/animals?handlerId=1
    @GetMapping
    public String displayAllAnimals(@RequestParam(required = false) Integer handlerId,
                                    Model model) {

        if (handlerId == null) {
            model.addAttribute("title", "All Animals");
            model.addAttribute("animals", animalRepository.findAll());
        } else {
            Optional<Handler> result = handlerRepository.findById(handlerId);
            if (result.isEmpty()) {
                model.addAttribute("title", "Invalid Handler ID: " + handlerId);
            } else {
                Handler handler = result.get();
                model.addAttribute("title", "Animals under the care of: " + handler.getName());
                model.addAttribute("animals", handler.getAnimals());
            }
        }

        return "animals/index";
    }

    @GetMapping("add")
    public String displayAddAnimalForm(Model model) {
        model.addAttribute("title", "Add Animal");
        model.addAttribute("animal", new Animal());
        model.addAttribute("handlers", handlerRepository.findAll());
        return "animals/add";
    }

    @PostMapping("add")
    public String processAddAnimalForm(@ModelAttribute @Valid Animal newAnimal,
                                       Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Animal");
            return "animals/add";
        }
        animalRepository.save(newAnimal);
        return "redirect:";
    }

    @GetMapping("remove")
    public String displayRemoveAnimalForm(Model model) {
        model.addAttribute("title", "Remove Animals");
        model.addAttribute("animals", animalRepository.findAll());
        return "animals/remove";
    }

    @PostMapping("remove")
    public String processRemoveAnimalForm(@RequestParam(required = false) int[] animalIds) {
        if (animalIds != null) {
            for (int id : animalIds) {
                animalRepository.deleteById(id);
            }
        }

        return "redirect:";
    }
}
