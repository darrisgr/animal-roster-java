package org.launchcode.animalroster.controllers;

import org.launchcode.animalroster.data.AnimalRepository;
import org.launchcode.animalroster.data.HandlerRepository;
import org.launchcode.animalroster.data.TagRepository;
import org.launchcode.animalroster.models.Animal;
import org.launchcode.animalroster.models.Handler;
import org.launchcode.animalroster.models.Tag;
import org.launchcode.animalroster.models.dto.AnimalTagDTO;
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

    @Autowired
    TagRepository tagRepository;

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

    @GetMapping("detail")
    public String displayAnimalDetails(@RequestParam Integer animalId, Model model) {

        Optional<Animal> result = animalRepository.findById(animalId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Animal ID: " + animalId);
        } else {
            Animal animal = result.get();
            model.addAttribute("title", animal.getName() + " Details");
            model.addAttribute("tags", animal.getTags());
            model.addAttribute("animal", animal);
        }

        return "animals/detail";
    }

    // /animals/add-tag?animalId=69
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer animalId,
                                    Model model) {
        Optional<Animal> result = animalRepository.findById(animalId);
        Animal animal = result.get();

        model.addAttribute("title", "Add Tag to: " + animal.getName());
        model.addAttribute("tags", tagRepository.findAll());
        AnimalTagDTO animalTag = new AnimalTagDTO();
        animalTag.setAnimal(animal);
        model.addAttribute("animalTag", animalTag);
        return "animals/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid AnimalTagDTO animalTag,
                                    Errors errors,
                                    Model model) {
        if (!errors.hasErrors()) {
            Animal animal = animalTag.getAnimal();
            Tag tag = animalTag.getTag();
            if (!animal.getTags().contains(tag)) {
                animal.addTag(tag);
                animalRepository.save(animal);
            }
            return "redirect:detail?animalId=" + animal.getId();
        }
        return "redirect:add-tag";
    }
}
