package ru.job4j.accident.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentMem;
import ru.job4j.accident.repository.AccidentTypeMem;

import java.util.Collection;
import java.util.Optional;
@ThreadSafe
@Service
public class AccidentTypeServiceImpl implements AccidentTypeService {
    private final AccidentTypeMem accidentTypeMem;

    public AccidentTypeServiceImpl(AccidentTypeMem accidentTypeMem) {
        this.accidentTypeMem = accidentTypeMem;
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypeMem.findAll();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return accidentTypeMem.findById(id);
    }

    @Override
    public boolean deleteAccitdentTypeById(int id) {
        return accidentTypeMem.deleteAccitdentTypeById(id);
    }

}
