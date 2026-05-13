package com.example.pokemonproject.entity;

import com.example.pokemonproject.enums.Type;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Type> types;

    @ManyToMany
    @JoinTable(
            name = "pokemon_abilities",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private Set<Ability> abilities;

    @ManyToMany
    @JoinTable(
            name = "pokemon_moves",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id")
    )
    private Set<Move> moves;

    @Column(nullable = false)
    private Integer hp;

    @Column(nullable = false)
    private Integer atk;

    @Column(nullable = false)
    private Integer spAtk;

    @Column(nullable = false)
    private Integer def;

    @Column(nullable = false)
    private Integer spDef;

    @Column(nullable = false)
    private Integer spe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evolves_from")
    private Pokemon evolvesFrom;

    @Column(length = 50)
    private String evolutionTrigger;
}

