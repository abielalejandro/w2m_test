package com.rgarcia.w2m.infrastructure.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "superheroes")
public class SuperHeroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "superheroes_seq")
    @SequenceGenerator(sequenceName="superheroes_seq", name="superheroes_seq", allocationSize=1)
    private Long id;

    @Column(unique=true, nullable = false)
    String name;

    @Column(nullable = false)
    String realName;

    @Column(nullable = false)
    String power;
}
