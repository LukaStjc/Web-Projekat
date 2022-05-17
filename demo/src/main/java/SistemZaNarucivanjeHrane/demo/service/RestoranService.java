package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Lokacija;
import SistemZaNarucivanjeHrane.demo.model.Menadzer;
import SistemZaNarucivanjeHrane.demo.model.Restoran;
import SistemZaNarucivanjeHrane.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private LokacijaService lokacijaService;

    public Restoran findByNaziv(String naziv) {
        return restoranRepository.findByNaziv(naziv);
    }

    public Restoran findByTip(String tip) {
        return restoranRepository.findByTip(tip);
    }


    public List<Restoran> findAll() {
        return restoranRepository.findAll();
    }

    public Restoran save(Restoran restoran) { return restoranRepository.save(restoran); }

    public Menadzer findMenadzerByKorisnickoIme(String korisnickoIme) { return menadzerService.findByKorisnickoIme(korisnickoIme); }

    public Lokacija saveLokacija(Lokacija lokacija) {
        return lokacijaService.save(lokacija);
    }

    public Menadzer saveMenadzer(Menadzer menadzer) { return menadzerService.save(menadzer); }
}
