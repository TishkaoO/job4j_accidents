package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.service.AccidentService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ThreadSafe
@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/formCreate")
    public String getPageFormUpdate(Model model) {
        List<AccidentType> types = new ArrayList<>();
        types.add(new AccidentType(1, "Две машины"));
        types.add(new AccidentType(2, "Машина и человек"));
        types.add(new AccidentType(3, "Машина и велосипед"));
        model.addAttribute("types", types);
        List<Rule> rules = List.of(
                new Rule(1, "Статья. 1"),
                new Rule(2, "Статья. 2"),
                new Rule(3, "Статья. 3")
        );
        model.addAttribute("rules", rules);
        return "accidents/formCreate";
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
