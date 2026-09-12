package com.devshowcase.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "technologies")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    // Lado inverso do N:N (mapeado por Project.technologies)
    @JsonIgnore
    @ManyToMany(mappedBy = "technologies")
    private Set<Project> projects = new HashSet<>();
}

