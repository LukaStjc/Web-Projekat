package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Lokacija;
import SistemZaNarucivanjeHrane.demo.repository.LokacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LokacijaService {

    @Autowired
    LokacijaRepository lokacijaRepository;

    public Lokacija save(Lokacija lokacija) {
        return  lokacijaRepository.save(lokacija);
    }
}
