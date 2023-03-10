package ru.job4j.accident.service;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentRepository accidentRepository;

    public boolean createAccident(Accident accident, Optional<AccidentType> accidentType, Set<Rule> rules) {
        var type = accidentType.get();
        accident.setType(type);
        accident.setRules(rules);
        return accidentRepository.save(accident) != null;
    }

    public void deleteAccidentById(int id) {
        accidentRepository.deleteById(id);
    }

    public boolean update(Accident accident) {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        var list = accidentRepository.findAll();
        for (Accident tmp : list) {
            accidentMap.put(tmp.getId(), tmp);
        }
        return accidentMap.computeIfPresent(accident.getId(), (accidentId, oldAccident) -> {
            oldAccident.setName(accident.getName());
            oldAccident.setDescription(accident.getDescription());
            oldAccident.setAddress(accident.getAddress());
            oldAccident.setType(accident.getType());
            oldAccident.setRules(accident.getRules());
            return oldAccident;
        }) != null;
    }


    public Optional<Accident> getAccidentById(int id) {
        Accident accident = accidentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Инцидент с id = " + id + " не найден"));
        return Optional.ofNullable(accident);
    }

    public List<Accident> getAllAccidents() {
       return accidentRepository.findAll();
    }
}
