package ru.job4j.accident.service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentService {

    Accident create(Accident accident);

    boolean deleteById(int id);

    boolean update(Accident accident);

    Optional<Accident> findById(int id);

    Collection<Accident> findAll();

    Collection<AccidentType> findAllAccidentType();
}
