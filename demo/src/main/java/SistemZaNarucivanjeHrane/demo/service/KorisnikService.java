package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Korisnik;
import SistemZaNarucivanjeHrane.demo.model.Restoran;
import SistemZaNarucivanjeHrane.demo.model.TipPola;
import SistemZaNarucivanjeHrane.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Autowired
    DostavljacService dostavljacService;

    @Autowired
    KupacService kupacService;

    @Autowired
    MenadzerService menadzerService;

    @Autowired
    RestoranService restoranService;

    public Korisnik findByKorisnickoIme(String korisnickoIme){
        return korisnikRepository.findByKorisnickoIme(korisnickoIme);
    }

    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public Korisnik login(String korisnickoIme, String lozinka) {
        
        Korisnik ulogovaniKorisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme);
            if (ulogovaniKorisnik == null || !ulogovaniKorisnik.getLozinka().equals(lozinka))
                return null;
            return ulogovaniKorisnik;

    }

    public Korisnik registracija(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, LocalDate datumRodjenja) {
        return kupacService.registracija(korisnickoIme, lozinka, ime, prezime, tipPola, datumRodjenja);
    }

    public List<Restoran> findAllRestorani() {
        return restoranService.findAll();
    }
}
