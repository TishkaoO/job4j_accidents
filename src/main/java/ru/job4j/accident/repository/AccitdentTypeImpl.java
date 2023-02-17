package ru.job4j.accident.repository;

import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class AccitdentTypeImpl implements AccidentTypeMem{
    private AtomicInteger nextId = new AtomicInteger(0);
    private Map<Integer, AccidentType> accidentTypeMap = new HashMap<>();

    @Override
    public AccidentType create(AccidentType accidentType) {
        var typeId = nextId.incrementAndGet();
        accidentType.setId(typeId);
        return accidentTypeMap.put(accidentType.getId(), accidentType);
    }

    @Override
    public Collection<AccidentType> findAll() {
        return accidentTypeMap.values();
    }

    @Override
    public Optional<AccidentType> findById(int id) {
        return Optional.ofNullable(accidentTypeMap.get(id));
    }

    @Override
    public boolean deleteAccitdentTypeById(int id) {
        return accidentTypeMap.remove(id) != null;
    }

    @Override
    public boolean updateAccidentType(AccidentType accidentType) {
        return accidentTypeMap.computeIfPresent(
                accidentType.getId(), (accidentTypeId, oldAccidenType) -> new AccidentType(
                      accidentType.getId(), accidentType.getName())) != null;
    }
}
