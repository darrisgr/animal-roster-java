package org.launchcode.animalroster.controllers;

import org.launchcode.animalroster.data.HandlerRepository;
import org.launchcode.animalroster.models.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("handlers")
public class HandlerController {

    @Autowired
    HandlerRepository handlerRepository;

    @GetMapping
    public String displayAllHandlers(Model model) {
        model.addAttribute("title", "All Handlers");
        model.addAttribute("handlers", handlerRepository.findAll());
        return "handlers/index";
    }

    @GetMapping("add")
    public String displayAddHandlerForm(Model model) {
        model.addAttribute("title", "Add Handler");
        model.addAttribute("handler", new Handler());
        return "handlers/add";
    }

    @PostMapping("add")
    public String processAddHandlerForm(@ModelAttribute @Valid Handler newHandler,
                                        Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Handler");
            return "handlers/add";
        }

        handlerRepository.save(newHandler);
        return "redirect:";
    }
}
