package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Dostavljac;
import SistemZaNarucivanjeHrane.demo.repository.DostavljacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DostavljacService {

    @Autowired
    private DostavljacRepository dostavljacRepository;

    public Dostavljac findByKorisnickoIme(String korisnickoIme) {
        return dostavljacRepository.findByKorisnickoIme(korisnickoIme);
    }

    public Dostavljac save(Dostavljac dostavljac) {
        return  dostavljacRepository.save(dostavljac);
    }
}
