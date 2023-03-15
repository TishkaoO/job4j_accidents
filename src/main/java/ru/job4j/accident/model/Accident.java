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

    @ManyToOne(fetch = FetchType.EAGER)
    /*
     аннотация, которая указывает на имя столбца в таблице аварий,
     который содержит идентификатор типа аварии.
     */
    @JoinColumn(name = "type_id")
    private AccidentType type;
    /*
    fetch = FetchType.EAGER указывает, что при запросе сущности Accident
    из базы данных, все связанные сущности AccidentType
    и Rule также будут выбраны из базы данных и загружены в память вместе с сущностью Accident

    CascadeType - Каскадный тип -
    это механизм в JPA/Hibernate,
    который определяет, какие операции, такие как сохранение, удаление,
    объединение, обновление и персистирование, будут распространяться на связанные сущности.

    Merge - слияние если мы вызываем merge на Accident которая
    имеет с ней связанный сущности в данном случае Rule
    все изменени в Accident будут отображены в связанной Rule
    без Merge нам бы пришлось отдельно вносить изменения
    Итог все изменения в основной сущности отображается в связанной
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    /*
    Join table аннотация, которая указывает на имя таблицы, которая будет использоваться для хранения связей
     "многие-ко-многим" между таблицами аварий и правил.
      Она также указывает на имена столбцов, которые будут использоваться для связи двух таблиц.
     */
    @JoinTable(
            name = "accident_rule",
            joinColumns = {@JoinColumn(name = "accident_id")},
            inverseJoinColumns = {@JoinColumn(name = "rule_id")}
    )
    private Set<Rule> rules;
}
