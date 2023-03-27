package ru.job4j.accident.service;

import lombok.AllArgsConstructor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AccidentService {
    private final AccidentRepository accidentRepository;

    public List<Accident> findAll() {
        return accidentRepository.findAll();
    }

    public Optional<Accident> findById(int id) {
        return accidentRepository.findById(id);
    }

    public boolean save(Accident accident) {
        return accidentRepository.save(accident) != null;
    }

    public void deleteAccident(int id) {
        accidentRepository.deleteById(id);
    }

    public boolean update(Accident accident) {
        return accidentRepository.findById(accident.getId())
                .map(a -> {
                    a.setName(accident.getName());
                    a.setDescription(accident.getDescription());
                    a.setAddress(accident.getAddress());
                    a.setAccidentType(accident.getAccidentType());
                    a.setRules(accident.getRules());
                    return accidentRepository.save(a) != null;
                })
                .orElseThrow(() -> new EntityNotFoundException("Entity not found"));
    }
}
