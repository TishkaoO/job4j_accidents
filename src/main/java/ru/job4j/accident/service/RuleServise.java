package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@ThreadSafe
@Service
@AllArgsConstructor
public class RuleServise {
    private final RuleRepository repository;

    public Rule create(Rule rule) {
        return repository.save(rule);
    }

    public Collection<Rule> getAllRules() {
        List<Rule> list = new ArrayList<>();
        var iterable = repository.findAll();
        for (Rule tmp : iterable) {
            list.add(tmp);
        }
        return list;
    }

    public Optional<Rule> getRuleById(int id) {
        var rules = getAllRules();
        for (Rule tmp : rules) {
            if (tmp.getId() == id) {
                return Optional.ofNullable(tmp);
            }
        }
        return Optional.empty();
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
        return accidentMap.computeIfPresent(rule.getId(), (ruleId, oldRulet) -> new Rule(
                ruleId, rule.getName())) != null;
    }
}