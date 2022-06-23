package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Menadzer;
import SistemZaNarucivanjeHrane.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenadzerService {

    @Autowired
    MenadzerRepository menadzerRepository;

    public Menadzer findByKorisnickoIme(String korisnickoIme) { return menadzerRepository.findByKorisnickoIme(korisnickoIme); }

    public Menadzer save(Menadzer menadzer) {
        return  menadzerRepository.save(menadzer);
    }

    public List<Menadzer> findAll() { return menadzerRepository.findAll(); }
}
