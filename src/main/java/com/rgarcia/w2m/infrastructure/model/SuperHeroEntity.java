package com.rgarcia.w2m.infrastructure.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@Entity
@Table(name = "superheroes")
public class SuperHeroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "superheroes_seq")
    @SequenceGenerator(sequenceName="superheroes_seq", name="superheroes_seq", allocationSize=1)
    private Long id;

    String name;
    String realName;
    String power;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;
}
