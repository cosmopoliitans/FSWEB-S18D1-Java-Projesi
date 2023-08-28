package com.workintech.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name="burger", schema = "burger_project")
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Positive
    private int id;

    @Column(name = "burger_name")
    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Column(name="burger_price")
    @Min(value = 100)
    private double price;

    @Column(name = "is_vegan")
    private boolean isVegan;

    @Enumerated(EnumType.STRING)
    private BreadType breadType;

    @ElementCollection
    @CollectionTable(name="burger_contents", joinColumns = @JoinColumn(name = "burger_id"))
    @Column(name = "content")
    private List<String> contents;

}

