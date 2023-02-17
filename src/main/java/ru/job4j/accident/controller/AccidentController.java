package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;

@ThreadSafe
@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/formCreate")
    public String getPageFormUpdate(Model model) {
        model.addAttribute("accident", accidentService.findAll());
        return "accidents/formCreate";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/index";
    }

    @GetMapping("/formUpdate")
    public String getPageFormUpdate(@RequestParam("id") int id, Model model) {
        model.addAttribute("accident", accidentService.findById(id).get());
        return "accidents/formUpdate";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/index";
    }
}
