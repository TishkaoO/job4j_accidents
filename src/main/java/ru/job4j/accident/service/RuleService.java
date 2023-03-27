package ru.job4j.accident.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import java.util.*;

@Service
@AllArgsConstructor
public class RuleService {
    private final RuleRepository repository;

    public List<Rule> getAllRules() {
        return repository.findAll();
    }

    public Set<Rule> getRulesById(List<Integer> ids) {
        Set<Rule> rules = new HashSet<>();
        for (Integer rId : ids) {
            Optional<Rule> rule = repository.findById(rId);
            rule.ifPresent(rules::add);
        }
        return rules;
    }
}
