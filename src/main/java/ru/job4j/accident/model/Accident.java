package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "accident")
public class Accident {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String address;
    /*
    аннотация, которая указывает на связь многие-к-одному
    (множество сущностей Accident относится к одной сущности AccidentType).
     */
    @ManyToOne(fetch = FetchType.LAZY)
    /*
    аннотация, которая указывает на столбец в таблице accidents,
который связывает сущность Accident с сущностью AccidentType.
     */
    @JoinColumn(name = "type_id")
    private AccidentType type;
    /*
    аннотация, которая указывает на связь многие-ко-многим
(множество сущностей Accident связано с множеством сущностей Rule).

    fetch = FetchType.LAZY - Это атрибут, который указывает на то,
     что данные поля должны извлекаться лениво, только при запросе (только при необходимости).

     cascade = CascadeType.MERGE - Это атрибут, который указывает на то,
     что все операции сохранения, обновления и удаления, п
     рименяемые к сущности Accident, также должны применяться к связанным с ней сущностям Rule.
     */

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    /*
    аннотация, которая связывает таблицу accidents с таблицей rule,
    чтобы создать таблицу-связь между этими двумя таблицами.
     Это необходимо, чтобы хранить информацию о связи между сущностями Accident и Rule.
     */
    @JoinTable(
            /*
            атрибут, который указывает на имя таблицы-связи между сущностями Accident и Rule.
             */
            name = "accident_rule",
            /*
            Это атрибут, который указывает на столбец в таблице-связи,
            который связывает сущность Accident с таблицей-связью.
             */
            joinColumns = {@JoinColumn(name = "accident_id")},
            /*
            атрибут, который указывает на столбец в таблице-связи,
            который связывает сущность Rule с таблицей-связью.
             */
            inverseJoinColumns = {@JoinColumn(name = "rule_id")}
    )
    private Set<Rule> rules;
}
