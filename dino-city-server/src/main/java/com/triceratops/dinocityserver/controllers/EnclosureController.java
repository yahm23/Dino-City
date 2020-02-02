package com.triceratops.dinocityserver.controllers;

import com.triceratops.dinocityserver.services.EnclosureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enclosures")
public class EnclosureController {

    private EnclosureService enclosureService;


}
