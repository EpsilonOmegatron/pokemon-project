package com.example.pokemonproject.entity;

import com.example.pokemonproject.common.enums.Type;
import com.example.pokemonproject.common.utils.SlugUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(indexes = @Index(name = "idx_pokemon_slug", columnList = "slug"))
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Type> types;

    @ManyToMany
    @JoinTable(
            name = "pokemon_abilities",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "ability_id")
    )
    private Set<Ability> abilities = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "pokemon_moves",
            joinColumns = @JoinColumn(name = "pokemon_id"),
            inverseJoinColumns = @JoinColumn(name = "move_id")
    )
    private Set<Move> moves = new HashSet<>();

    @Column(nullable = false)
    private Integer hp = 1;

    @Column(nullable = false)
    private Integer atk = 1;

    @Column(nullable = false)
    private Integer spAtk = 1;

    @Column(nullable = false)
    private Integer def = 1;

    @Column(nullable = false)
    private Integer spDef = 1;

    @Column(nullable = false)
    private Integer spe = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "evolves_from")
    private Pokemon evolvesFrom;

    @Column(length = 50)
    private String evolutionTrigger;

    @PrePersist
    @PreUpdate
    private void ensureSlug() {
        this.slug = SlugUtils.toSlug(this.name);
    }
}

