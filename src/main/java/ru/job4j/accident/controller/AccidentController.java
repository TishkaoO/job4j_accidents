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
        model.addAttribute("accidents", accidentService.getAllAccidents());
        return "accidents/listAccident";
    }

    @GetMapping("/formCreate")
    public String getPageFormCreate(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("types", accidentTypeService.getAllAccidentTypes());
        model.addAttribute("rules", ruleService.getAllRules());
        return "accidents/formCreate";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId,
                       @RequestParam("rIds") List<Integer> rIds, Model model) {
        var accidentType = accidentTypeService.getAccidentTypeById(typeId);
        var rules = ruleService.getRulesById(rIds);
        var isCreate = accidentService.createAccident(accident, accidentType, rules);
        if (!isCreate) {
            model.addAttribute("message",
                    "Тип нарушения или статья указанным идентификатором не найдены");
            return "errors/404";
        }
        return "redirect:/allAccidents";
    }

    @GetMapping("/formUpdate")
    public String formUpdate(@RequestParam("id") int id, Model model) {
        Optional<Accident> accident = accidentService.getAccidentById(id);
        if (accident.isEmpty()) {
            model.addAttribute("message", "не удалось найти нужный инцидент");
            return "errors/404";
        }
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("accident", accident.get());
        model.addAttribute("types", accidentTypeService.getAllAccidentTypes());
        model.addAttribute("rules", ruleService.getAllRules());
        return "accidents/formUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, @RequestParam("type.id") int typeId,
                         @RequestParam("rIds") List<Integer> rIds, Model model) {
        accident.setType(accidentTypeService.getAccidentTypeById(typeId).get());
        accident.setRules(ruleService.getRulesById(rIds));
        var isUpdate = accidentService.update(accident);
        if (!isUpdate) {
            model.addAttribute("message", "Не удалось обновить данные");
            return "errors/404";
        }
        return "redirect:/allAccidents";
    }
}
