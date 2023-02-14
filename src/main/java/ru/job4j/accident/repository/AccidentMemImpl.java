package ru.job4j.accident.repository;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@ThreadSafe
@Repository
public class AccidentMemImpl implements AccidentMem {
    private int nextId = 1;
    private Map<Integer, Accident> accidentMap = new HashMap<>();

    public AccidentMemImpl() {
        create(new Accident(0, "Какая-то авария", "2 машины столкнулись лоб в лоб, но никто из водителей не пострадал",
                "ул. Пушкина, дом Калатушкино"));
        create(new Accident(0, "Жесткая авария", "водитель снес светофор и скрылся с места дтп",
                "ул. Петрова Кузима 17 дом 3"));
    }

    @Override
    public Accident create(Accident accident) {
        accident.setId(nextId++);
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
                        accidentId, accident.getName(), accident.getText(), accident.getAddress())) != null;
    }

    @Override
    public Optional<Accident> findById(int accidentId) {
        return Optional.ofNullable(accidentMap.get(accidentId));
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentMap.values();
    }
}
