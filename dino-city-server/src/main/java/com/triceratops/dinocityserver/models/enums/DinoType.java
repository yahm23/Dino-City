package com.triceratops.dinocityserver.models.enums;

public enum DinoType {
    TYRANNOSAURUS("Tyrannosaurus",20, DietType.CARNIVORE, 3000.0, ThreatLevel.HIGH),
    ALBERTOSAURUS("Albertosaurus", 15, DietType.CARNIVORE,, 2500.0, ThreatLevel.HIGH),
    CARNOTAURUS("Carnotaurus", 15, DietType.CARNIVORE,, 2200.0, ThreatLevel.HIGH),
    BARYONYX("Baryonyx", 10, DietType.CARNIVORE,, 1500.0, ThreatLevel.MEDIUM),
    MEGALOSAURUS("Megalosaurus", 10, DietType.CARNIVORE,, 1700.0, ThreatLevel.MEDIUM),
    VELOCIRAPTOR("Velociraptor", 5, DietType.CARNIVORE,, 1000.0, ThreatLevel.MEDIUM),
    DEINONYCHUS("Deinonychus", 6, DietType.CARNIVORE,, 1200.0, ThreatLevel.MEDIUM),
    COMPSOGNATHUS("Compsognathus",2, DietType.CARNIVORE,, 300.0, ThreatLevel.MEDIUM),
    BRACHIOSAURUS("Brachiosaurus", 20, DietType.HERBIVORE, 3000.0, ThreatLevel.LOW),
    IGUANODON("Iguanodon", 20, DietType.HERBIVORE, 2500.0, ThreatLevel.MEDIUM),
    TRICERATOPS("Triceratops", 18, DietType.HERBIVORE, 2200.0, ThreatLevel.MEDIUM),
    ANKYLOSAURUS("Ankylosaurus", 15, DietType.HERBIVORE, 2000.0, ThreatLevel.MEDIUM),
    EDMONTOSAURUS("Edmontosaurus", 15, DietType.HERBIVORE, 2000.0, ThreatLevel.MEDIUM),
    STEGOSAURUS("Stegosaurus", 15, DietType.HERBIVORE, 1800.0, ThreatLevel.MEDIUM),
    STEGOCERAS("Stegoceras", 10, DietType.HERBIVORE, 1500.0, ThreatLevel.LOW),
    HOMALOCEPHALE("Homalocephale", 4, DietType.HERBIVORE, 1000.0, ThreatLevel.LOW);


    DinoType(String tyrannosaurus, int i, DietType carnivore, double v, ThreatLevel high) {
    }
}
