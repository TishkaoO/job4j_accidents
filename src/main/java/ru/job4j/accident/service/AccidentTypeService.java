package ru.job4j.accident.service;

import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.Optional;

public interface AccidentTypeService {

    Collection<AccidentType> findAll();

    Optional<AccidentType> findById(int id);

    boolean deleteAccitdentTypeById(int id);
}
