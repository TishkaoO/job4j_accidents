package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "accidents")
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String address;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accident_type_id")
    private AccidentType type;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "accident_rule",
            joinColumns = {@JoinColumn(name = "accident_id")},
            inverseJoinColumns = {@JoinColumn(name = "rule_id")}
    )
    private Set<Rule> rules;
}
