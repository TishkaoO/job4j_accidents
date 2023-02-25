package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;
import ru.job4j.accident.repository.AccidentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentService {
    private final AccidentRepository accidentRepository;

    public Accident saveAccident(Accident accident) {
        return accidentRepository.save(accident);
    }

    public void deleteAccidentById(int id) {
        Accident accident = accidentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Инцидент с id = " + id + " не найден"));
        accidentRepository.delete(accident);
    }

    public boolean update(Accident accident) {
        Map<Integer, Accident> accidentMap = new HashMap<>();
        var iterable = accidentRepository.findAll();
        for (Accident tmp : iterable) {
            accidentMap.put(accident.getId(), tmp);
        }
        return accidentMap.computeIfPresent(accident.getId(), (accidentId, oldAccident) -> new Accident(
                accidentId, accident.getName(), accident.getDescription(), accident.getAddress(),
                new AccidentType(), Set.of(new Rule()))) != null;
    }

    public Optional<Accident> getAccidentById(int id) {
        Accident accident = accidentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Инцидент с id = " + id + " не найден"));
        return Optional.ofNullable(accident);
    }

    public Collection<Accident> getAllAccidents() {
        List<Accident> list = new ArrayList<>();
        var iterable = accidentRepository.findAll();
        for (Accident tmp : iterable) {
            list.add(tmp);
        }
        return list;
    }

}
