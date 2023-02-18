package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class RuleMemImpl implements RuleMem {
    private AtomicInteger atomicId = new AtomicInteger(0);
    private Map<Integer, Rule> ruleMap = new HashMap<>();

    public RuleMemImpl() {
        create(new Rule(1, "Статья. 1"));
        create(new Rule(2, "Статья. 2"));
        create(new Rule(3, "Статья. 3"));
    }

    @Override
    public Rule create(Rule rule) {
        var nextId = atomicId.incrementAndGet();
        rule.setId(nextId);
        return ruleMap.put(rule.getId(), rule);
    }

    @Override
    public Collection<Rule> findAll() {
        return ruleMap.values();
    }

    @Override
    public Optional<Rule> findById(int id) {
        return Optional.ofNullable(ruleMap.get(id));
    }

    @Override
    public boolean deleteRuleTypeById(int id) {
        return ruleMap.remove(id) != null;
    }

    @Override
    public boolean updateRule(Rule rule) {
        return ruleMap.computeIfPresent(rule.getId(), (ruleId, OldRule) -> new Rule(
                rule.getId(), rule.getName())) != null;
    }
}
