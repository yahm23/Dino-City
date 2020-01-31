package com.triceratops.dinocityserver.models.buildings;


import com.triceratops.dinocityserver.models.Dinosaur;

import javax.persistence.*;

import javax.persistence.Entity;
import java.util.ArrayList;

@Entity
@Table(name = "enclosure")
public class Enclosure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="dinosaurs")
    private ArrayList<Dinosaur> dinosaurs;

    @Column(name="")







}
