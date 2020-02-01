package com.triceratops.dinocityserver.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.triceratops.dinocityserver.behaviours.IPurchasable;
import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "enclosure")
public class Enclosure   {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties("enclosure")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    @OneToMany(mappedBy = "enclosure",fetch = FetchType.LAZY)
    private List<Dinosaur> dinosaurs;

    @Column(name="size")
    private SizeType size;

    @Column(name = "securityLevel")
    private SecurityLevel securitylevel;

    @JsonIgnoreProperties("enclosures")
    @ManyToOne
    @JoinColumn(name="park_id",nullable = false)
    private Park park;

    public Enclosure(){}

    public Enclosure (SizeType size, SecurityLevel securityLevel) {
        this.dinosaurs = new ArrayList<>();
        this.size = size;
        this.securitylevel = securityLevel;
    }

    public Long getId() {
        return id;
    }


    public List<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public void addDinosaur(Dinosaur dinosaur){
        dinosaurs.add(dinosaur);
    };

    public void setDinosaurs(List<Dinosaur> dinosaurs) {
        this.dinosaurs = dinosaurs;
    }

    public SizeType getSize() {
        return size;
    }

    public void setSize(SizeType size) {
        this.size = size;
    }

    public SecurityLevel getSecurityLevel() {
        return securitylevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securitylevel = securityLevel;
    }
}
