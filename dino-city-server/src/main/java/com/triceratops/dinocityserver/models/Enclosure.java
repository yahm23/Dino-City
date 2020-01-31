package com.triceratops.dinocityserver.models;


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







}
