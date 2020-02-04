package com.triceratops.dinocityserver.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.triceratops.dinocityserver.models.enums.DinoType;

import javax.persistence.*;

@Entity
@Table(name = "dinosaurs")
public class Dinosaur {

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "species")
    private DinoType species;

    @JsonIgnoreProperties("dinosaurs")
    @ManyToOne
    @JoinColumn(name="enclosure_id",nullable = false)
    private Enclosure enclosure;

    public Dinosaur(){}

    public Dinosaur(DinoType species) {
        this.species = species;
    }

    public Long getId() {
        return id;
    }

    public Enclosure getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(Enclosure enclosure) {
        this.enclosure = enclosure;
    }


    public DinoType getSpecies() {
        return species;
    }

    public void setSpecies(DinoType species) {
        this.species = species;
    }




}
