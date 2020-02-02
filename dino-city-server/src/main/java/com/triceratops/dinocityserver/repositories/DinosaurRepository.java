package com.triceratops.dinocityserver.repositories;

import com.triceratops.dinocityserver.models.Dinosaur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinosaurRepository extends JpaRepository<Dinosaur,Long> {

}
