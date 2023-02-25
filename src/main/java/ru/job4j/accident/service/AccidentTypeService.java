package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.AccidentType;
import ru.job4j.accident.repository.AccidentTypeRepository;

import java.util.*;

@ThreadSafe
@Service
@AllArgsConstructor
public class AccidentTypeService {
    private final AccidentTypeRepository repository;

    public Collection<AccidentType> getAllAccidentTypes() {
       List<AccidentType> list = new ArrayList<>();
       Iterable<AccidentType> iter = repository.findAll();
        for (AccidentType tmp : iter) {
            list.add(tmp);
        }
        return list;
    }

    public Optional<AccidentType> getAccidentTypeById(int id) {
        return Optional.ofNullable(repository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Тип нарушения с id = " + id + "не найден")));
    }
}
