package ru.job4j.accident.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userName;

    private String password;

    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;

    private boolean enabled;
}
