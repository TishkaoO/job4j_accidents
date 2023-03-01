package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
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
        model.addAttribute("accidents", accidentService.getAllAccidents());
        return "accidents/listAccident";
    }

    @GetMapping("/formCreate")
    public String getPageFormUpdate(Model model) {
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
        }
        return "redirect:/allAccidents";
    }

    @GetMapping("/formUpdate/{id}")
    public String getPageFormUpdate(@RequestParam("id") int id, Model model) {
        Optional<Accident> accidentOptional = accidentService.getAccidentById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message", "Инцидент с указанным идентификатором не найден");
            return "errors/404";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("types", accidentTypeService.getAllAccidentTypes());
        model.addAttribute("rules", ruleService.getAllRules());
        return "accidents/formUpdate";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model) {
        var isUpdate = accidentService.update(accident);
        if (!isUpdate) {
            model.addAttribute("message", "Не удалось обновить данные");
            return "errors/404";
        }
        return "redirect:/allAccidents";
    }
}
