package com.triceratops.dinocityserver.repositories;

import com.triceratops.dinocityserver.models.Enclosure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnclosureRepository extends JpaRepository<Enclosure,Long> {
}
