package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.RuleMem;

import java.util.Collection;
import java.util.Optional;
@ThreadSafe
@Service
public class RuleServiseImpl implements RuleService {
    private final RuleMem ruleMem;

    public RuleServiseImpl(RuleMem ruleMem) {
        this.ruleMem = ruleMem;
    }

    @Override
    public Rule create(Rule rule) {
        return ruleMem.create(rule);
    }

    @Override
    public Collection<Rule> findAll() {
        return ruleMem.findAll();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return ruleMem.findById(id);
    }

    @Override
    public boolean deleteRuleTypeById(int id) {
        return ruleMem.deleteRuleTypeById(id);
    }

    @Override
    public boolean updateRule(Rule rule) {
        return ruleMem.updateRule(rule);
    }
}
