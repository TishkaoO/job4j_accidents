package ru.job4j.accident.repository;

import org.springframework.data.repository.CrudRepository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.Rule;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
    @Override
    List<Rule> findAll();
}
