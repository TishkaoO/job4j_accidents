package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentJdbcMem;

import java.util.Collection;
import java.util.Optional;
@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentServiceImpl implements AccidentService {

    private final AccidentJdbcMem accidentJdbcMem;

    @Override
    public Accident create(Accident accident) {
        return accidentJdbcMem.create(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentJdbcMem.deleteById(id);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentJdbcMem.update(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentJdbcMem.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentJdbcMem.findAll();
    }
}
