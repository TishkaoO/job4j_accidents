package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.service.AccidentService;
import ru.job4j.accident.service.AccidentTypeService;
import ru.job4j.accident.service.RuleService;

import javax.servlet.http.HttpServletRequest;

@ThreadSafe
@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidentService;
    private final AccidentTypeService accidentTypeService;
    private final RuleService ruleService;

    @GetMapping("/formCreate")
    public String getPageFormUpdate(Model model) {
        try {
            model.addAttribute("types", accidentTypeService.findAll());
            model.addAttribute("rules", ruleService.findAll());
            return "accidents/formCreate";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Accident accident, HttpServletRequest request, Model model) {
        try {
            String[] ids = request.getParameterValues("rIds");
            accidentService.create(accident);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @GetMapping("/formUpdate")
    public String getPageFormUpdate(@RequestParam("id") int id, Model model) {
        try {
            model.addAttribute("accident", accidentService.findById(id).get());
            return "accidents/formUpdate";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Accident accident, Model model) {
        try {
            accidentService.update(accident);
            return "redirect:/index";
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
            return "errors/404";
        }
    }
}
