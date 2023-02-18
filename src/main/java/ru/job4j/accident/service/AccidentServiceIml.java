package ru.job4j.accident.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accident.model.Accident;
import ru.job4j.accident.repository.AccidentHibernateRepository;

import java.util.Collection;
import java.util.Optional;
@Service
@AllArgsConstructor
public class AccidentServiceIml implements AccidentService {
    private final AccidentHibernateRepository accidentHibernateRepository;

    @Override
    public Accident create(Accident accident) {
        return accidentHibernateRepository.create(accident);
    }

    @Override
    public boolean deleteById(int id) {
        return accidentHibernateRepository.deleteById(id);
    }

    @Override
    public boolean update(Accident accident) {
        return accidentHibernateRepository.update(accident);
    }

    @Override
    public Optional<Accident> findById(int id) {
        return accidentHibernateRepository.findById(id);
    }

    @Override
    public Collection<Accident> findAll() {
        return accidentHibernateRepository.findAll();
    }
}
