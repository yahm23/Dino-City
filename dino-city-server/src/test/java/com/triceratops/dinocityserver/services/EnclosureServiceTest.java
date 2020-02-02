package com.triceratops.dinocityserver.services;

import com.triceratops.dinocityserver.models.EnclosureTypeResponse;
import com.triceratops.dinocityserver.models.enums.SecurityLevel;
import com.triceratops.dinocityserver.models.enums.SizeType;
import com.triceratops.dinocityserver.repositories.EnclosureRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class EnclosureServiceTest {

    private EnclosureService service;

    @Before
    public void init() {
        service = new EnclosureService(mock(EnclosureRepository.class));
    }

    @Test
    public void getEnclosuresTypes() {
        EnclosureTypeResponse types = new EnclosureTypeResponse(SizeType.values(), SecurityLevel.values());
        assertEquals(types, service.getEnclosuresTypes());
    }
}