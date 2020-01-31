package com.triceratops.dinocityserver.models.buildings;


import com.triceratops.dinocityserver.models.Dinosaur;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
@Table(name = "enclosure")
public class Enclosure extends Building{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dinosaurs")
    private ArrayList<Dinosaur> dinosaurs;

    @Column(name="size")
    private SizeType size;

    @Column(name = "securityLevel")
    private SecurityLevel securityLevel;

    public Enclosure(){}

    public Enclosure(ArrayList<Dinosaur> dinosaurs, SizeType size, SecurityLevel securityLevel) {
        this.dinosaurs = dinosaurs;
        this.size = size;
        this.securityLevel = securityLevel;
    }

    public Long getId() {
        return id;
    }


    public ArrayList<Dinosaur> getDinosaurs() {
        return dinosaurs;
    }

    public void setDinosaurs(ArrayList<Dinosaur> dinosaurs) {
        this.dinosaurs = dinosaurs;
    }

    public SizeType getSize() {
        return size;
    }

    public void setSize(SizeType size) {
        this.size = size;
    }

    public SecurityLevel getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(SecurityLevel securityLevel) {
        this.securityLevel = securityLevel;
    }
}
