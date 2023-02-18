package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
@ThreadSafe
@Repository
public class AccitdentTypeImpl implements AccidentTypeMem {
    private AtomicInteger nextId = new AtomicInteger(0);
    private Map<Integer, AccidentType> accidentTypeMap = new HashMap<>();

    public AccitdentTypeImpl() {
        create(new AccidentType(1, "Две машины"));
        create(new AccidentType(2, "Машина и человек"));
        create(new AccidentType(3, "Машина и велосипед"));
    }

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
}
