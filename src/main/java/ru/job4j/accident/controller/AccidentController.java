package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import java.util.*;

@Controller
@AllArgsConstructor
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/allAccidents")
    public String getPageAllAccidents(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents/listAccident";
    }

    @GetMapping("/create")
    public String getPageFormCreate(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("types", accidentTypeService.getAllAccidentTypes());
        model.addAttribute("rules", ruleService.getAllRules());
        return "accidents/formCreate";
    }

    @PostMapping("/create")
    public String createAccident(@ModelAttribute Accident accident) {
        accidentService.save(accident);
        return "redirect:/allAccidents";
    }

    @GetMapping("/update/{id}")
    public String refreshAccident(@PathVariable int id, Model model) {
        Optional<Accident> findAccident = accidentService.findById(id);
        if (findAccident.isEmpty()) {
            model.addAttribute("message", "не удалось найти нужный инцидент");
            return "errors/404";
        }
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accident", findAccident.get());
        model.addAttribute("types", accidentTypeService.getAllAccidentTypes());
        model.addAttribute("rules", ruleService.getAllRules());
        return "accidents/formUpdate";
    }

    @PostMapping("/update")
    public String updateAccident(@ModelAttribute Accident accident, Model model) {
        var isUpdate = accidentService.update(accident);
        if (!isUpdate) {
            model.addAttribute("message", "Не удалось обновить данные");
            return "errors/404";
        }
        return "redirect:/allAccidents";
    }

    @PostMapping("/delete/{id}")
    public String deleteAccident(@PathVariable int id) {
        accidentService.deleteAccident(id);
        return "redirect:/allAccidents";
    }
}
