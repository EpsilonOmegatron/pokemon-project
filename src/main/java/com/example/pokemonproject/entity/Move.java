package com.example.pokemonproject.entity;

import com.example.pokemonproject.common.enums.DamageCategory;
import com.example.pokemonproject.common.enums.Type;
import com.example.pokemonproject.common.utils.SlugUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "moves", indexes = @Index(name = "idx_move_slug", columnList = "slug"))
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(length = 100, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DamageCategory damageCategory;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private Integer basePower;

    @Column(nullable = false)
    private Integer powerPoints;

    @Column(nullable = false)
    private Integer accuracy;

    @PrePersist
    @PreUpdate
    private void ensureSlug() {
        this.slug = SlugUtils.toSlug(this.name);
    }
}
