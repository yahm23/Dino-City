package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.Enclosure;
import com.triceratops.dinocityserver.repositories.EnclosureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnclosureService {

    private EnclosureRepository enclosureRepository;

    @Autowired
    public EnclosureService(EnclosureRepository enclosureRepository) {
        this.enclosureRepository = enclosureRepository;
    }


}
