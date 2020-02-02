package com.triceratops.dinocityserver.repositories;

import com.triceratops.dinocityserver.models.Park;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkRepository extends JpaRepository<Park, Long> {

    Park findParkByName(String name);
}
