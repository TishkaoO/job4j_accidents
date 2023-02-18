package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.model.Rule;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
@Repository
public class AccidentMemImpl implements AccidentMem {
    private AtomicInteger nextId = new AtomicInteger(0);
    private Map<Integer, Accident> accidentMap = new HashMap<>();

    private final Map<Integer, AccidentType> accidentTypes = new HashMap<>(Map.of(
            1, new AccidentType(1, "Две машины"),
            2, new AccidentType(2, "Машина и человек"),
            3, new AccidentType(3, "Машина и велосипед")
    ));

    public AccidentMemImpl() {
        create(new Accident(1, "Какая-то авария", "2 машины столкнулись лоб в лоб, но никто из водителей не пострадал",
               "ул. Пушкина, дом Калатушкино", new AccidentType(1, "Две машины"),
                Set.of(new Rule(1, "Статья"))));
        create(new Accident(2, "Жесткая авария", "водитель снес светофор и скрылся с места дтп",
                "ул. Петрова Кузима 17 дом 3", new AccidentType(2, "Машина и светофор"),
                Set.of(new Rule(2, "Статья"))));
    }

    @Override
    public Accident create(Accident accident) {
        var taskId = nextId.incrementAndGet();
        accident.setId(taskId);
        accidentMap.put(accident.getId(), accident);
        return accident;
    }

    @Override
    public boolean deleteById(int accidentId) {
        return accidentMap.remove(accidentId) != null;
    }

    @Override
    public boolean update(Accident accident) {
        return accidentMap.computeIfPresent(
                accident.getId(), (accidentId, OldAccident) -> new Accident(
                        accidentId, accident.getName(), accident.getText(), accident.getAddress(),
                        new AccidentType(), Set.of(new Rule()))) != null;
    }

    @Override
    public Optional<Accident> findById(int accidentId) {
        return Optional.ofNullable(accidentMap.get(accidentId));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentMap.values();
    }

    @Override
    public Collection<AccidentType> findAllAccidentTypes() {
        return accidentTypes.values();
    }
}
