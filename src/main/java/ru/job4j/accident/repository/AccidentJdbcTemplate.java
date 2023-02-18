package ru.job4j.accident.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accident.model.Accident;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccidentJdbcTemplate implements AccidentJdbcMem {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Accident create(Accident accident) {
        jdbcTemplate.update("insert into accident (name) values (?)",
                accident.getName());
        return accident;
    }

    @Override
    public boolean deleteById(int id) {
        int rows = jdbcTemplate.update("delete from accident where accident_id = ?", id);
       return rows > 0;
    }

    @Override
    public boolean update(Accident accident) {
        jdbcTemplate.update("update accident set name = ? where id = ?");
        return accident != null;
    }

    @Override
    public Optional<Accident> findById(int id) {
        String query = "select id, name from accident where id = ?";
        RowMapper<Accident> rowMapper = new BeanPropertyRowMapper<>(Accident.class);
        Accident accident = null;
        try {
            accident = jdbcTemplate.queryForObject(query, rowMapper, id);
        } catch (Exception e) {
            return Optional.empty();
        }
        return Optional.ofNullable(accident);
    }

    @Override
    public Collection<Accident> findAll() {
        return jdbcTemplate.query("select id, name from accident",
                (rs, row) -> {
                    Accident accident = new Accident();
                    accident.setId(rs.getInt("id"));
                    accident.setName(rs.getString("name"));
                    return accident;
                });
    }
}
