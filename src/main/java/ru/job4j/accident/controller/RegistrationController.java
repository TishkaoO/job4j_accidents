package ru.job4j.accident.controller;

import lombok.AllArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.job4j.accident.model.User;
import ru.job4j.accident.service.AuthorityService;
import ru.job4j.accident.service.UserService;

@Controller
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final AuthorityService authorityService;

    @GetMapping("/formRegistration")
    public String regPage(Model model) {
        return "users/registration";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        try {
            userService.saveUser(user);
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("message", "такой пользователь уже существует");
            return "errors/registerError";
        }
        return "redirect:/formLogin";
    }
}
