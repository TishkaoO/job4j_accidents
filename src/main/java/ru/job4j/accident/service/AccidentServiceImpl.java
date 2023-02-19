package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentServiceImpl {
    private final AccidentRepository accidentRepository;

    public Accident create(Accident accident) {
        return accidentRepository.save(accident);
    }

    public void deleteById(int id) {
        Accident accident = accidentRepository.findById(id)
                .orElseThrow(() ->  new EntityNotFoundException("Accident with id " + id + " not found"));
        accidentRepository.delete(accident);
    }

    public Accident update(Accident accident) {
        return accidentRepository.save(accident);
    }

    public Accident findById(int id) {
        Accident accident = accidentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Accident with id " + id + " not found"));
        return accident;
    }

    public Iterable<Accident> findAll() {
        return accidentRepository.findAll();
    }
}
