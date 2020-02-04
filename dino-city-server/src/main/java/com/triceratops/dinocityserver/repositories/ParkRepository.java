package com.triceratops.dinocityserver.repositories;

import com.triceratops.dinocityserver.models.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<Park, Long> {

    Park findParkByName(String name);
    List<Park> findAll();
}
