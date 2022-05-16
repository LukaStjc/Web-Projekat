package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenadzerRestController {

    @Autowired
    private MenadzerService menadzerService;
}
