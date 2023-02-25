package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Rule;
@ThreadSafe
@Repository
public interface RuleRepository extends CrudRepository<Rule, Integer> {
}
