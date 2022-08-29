package org.launchcode.animalroster.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("animals")
public class AnimalController {

    private static List<String> animals = new ArrayList<>();

    @GetMapping
    public String displayAllAnimals(Model model) {
//        List<String> animals = new ArrayList<>();
//        animals.add("Banana Cat");
//        animals.add("Apple Dog");
//        animals.add("Zebra that I always forget the fruit that matches with it");
//        animals.add("Your teacher");
        model.addAttribute("animals", animals);
        return "animals/index";
    }

    @GetMapping("add")
    public String displayAddAnimalForm(Model model) {
        model.addAttribute("title", "Create Animal");
        return "animals/add";
    }

    @PostMapping("add")
    public String processAddAnimalForm(@RequestParam String animalName) {
        animals.add(animalName);
        return "redirect:";
    }
}
