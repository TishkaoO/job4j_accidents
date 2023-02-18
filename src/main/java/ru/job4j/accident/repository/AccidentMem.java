package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentMem {

    Accident create(Accident accident);

    boolean deleteById(int id);

    boolean update(Accident accident);

    Optional<Accident> findById(int id);

    Collection<Accident> findAll();

    Collection<AccidentType> findAllAccidentTypes();
}
