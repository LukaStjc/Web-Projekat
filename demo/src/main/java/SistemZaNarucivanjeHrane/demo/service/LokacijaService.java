package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LokacijaService {

    @Autowired
    private LokacijaRepository lokacijaRepository;
}
