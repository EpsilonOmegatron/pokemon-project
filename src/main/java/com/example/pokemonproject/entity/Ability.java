package com.example.pokemonproject.entity;

import com.example.pokemonproject.common.utils.SlugUtils;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "abilities", indexes = @Index(name = "idx_ability_slug", columnList = "slug"))
public class Ability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(length = 100, nullable = false)
    private String description;

    @PrePersist
    @PreUpdate
    private void ensureSlug() {
        this.slug = SlugUtils.toSlug(this.name);
    }
}