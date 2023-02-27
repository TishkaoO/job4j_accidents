package ru.job4j.accident.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ru.job4j.accident.model.Rule;

import java.util.List;
import java.util.Set;

public interface RuleRepository extends CrudRepository<Rule, Integer> {
    List<Rule> findAll();
}
