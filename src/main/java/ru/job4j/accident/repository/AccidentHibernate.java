package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentHibernate implements AccidentHibernateRepository {
    private final SessionFactory sf;

    @Override
    public Accident create(Accident accident) {
        var session = sf.openSession();
        try {
            session.beginTransaction();
            session.save(accident);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return accident;
    }

    @Override
    public boolean deleteById(int id) {
        var session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            int count = session.createQuery("delete Accident where id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            return count > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean update(Accident accident) {
        var session = sf.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            int count = session.createQuery(
                            "update Accident set name = :fName, description = :fDescription" +
                                    ", address = :fAddress, type = :fType, rules = :fRules")
                    .setParameter(":fName", accident.getName())
                    .setParameter(":fDescription", accident.getDescription())
                    .setParameter(":fAddress", accident.getAddress())
                    .setParameter(":fType", accident.getType())
                    .setParameter(":fRules", accident.getRules())
                    .executeUpdate();
            session.getTransaction().commit();
            return count > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Optional<Accident> findById(int id) {
        Session session = sf.openSession();
        try {
            session.beginTransaction();
            var queryAccident = session.createQuery(
                    "from User as a where a.id = :fId", Accident.class);
            queryAccident.setParameter("fId", id);
            var rsl = queryAccident.uniqueResultOptional();
            session.getTransaction().commit();
            return rsl;
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return Optional.empty();
    }


    @Override
    public List<Accident> findAll() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery("from Accident", Accident.class)
                    .list();
        }
    }
}
