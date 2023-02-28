package ru.job4j.accident.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import javax.persistence.EntityNotFoundException;

import java.util.*;

@Service
@AllArgsConstructor
public class RuleServise {
    private final RuleRepository repository;

    public Rule create(Rule rule) {
        return repository.save(rule);
    }

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

    public void deleteRuleById(int id) {
        Rule rule = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Инцидент с id = " + id + " не найден"));
        repository.delete(rule);
    }

    public boolean updateRule(Rule rule) {
        Map<Integer, Rule> accidentMap = new HashMap<>();
        var rules = getAllRules();
        for (Rule tmp : rules) {
            accidentMap.put(rule.getId(), tmp);
        }
        return accidentMap.computeIfPresent(rule.getId(), (ruleId, oldRule) -> new Rule(
                ruleId, rule.getName())) != null;
    }
}
