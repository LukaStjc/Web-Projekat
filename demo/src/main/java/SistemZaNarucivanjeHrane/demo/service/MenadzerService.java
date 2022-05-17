package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Menadzer;
import SistemZaNarucivanjeHrane.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;

    public Menadzer findByKorisnickoIme(String korisnickoIme) { return menadzerRepository.findByKorisnickoIme(korisnickoIme); }

    public Menadzer save(Menadzer menadzer) {
        return  menadzerRepository.save(menadzer);
    }
}
