package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PorudzbinaRestController {

    @Autowired
    private PorudzbinaService porudzbinaService;
}
