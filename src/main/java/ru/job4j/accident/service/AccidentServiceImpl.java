package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentMem;

import java.util.Collection;
import java.util.Optional;
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentServiceImpl implements AccidentService {
    private final AccidentMem accidentMem;

    @Override
    public Accident create(Accident accident) {
        return accidentMem.create(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentMem.deleteById(id);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentMem.update(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentMem.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentMem.findAll();
    }
}
