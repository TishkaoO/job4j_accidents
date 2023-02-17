package ru.job4j.accident.repository;

import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.Optional;

public interface RuleMem {

    Rule create(Rule rule);

    Collection<Rule> findAll();

    Optional<Rule> findById(int id);

    boolean deleteRuleTypeById(int id);

    boolean updateRule(Rule rule);
}
