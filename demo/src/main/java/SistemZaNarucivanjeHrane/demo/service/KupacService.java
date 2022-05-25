package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Kupac;
import SistemZaNarucivanjeHrane.demo.model.TipKupca;
import SistemZaNarucivanjeHrane.demo.model.TipPola;
import SistemZaNarucivanjeHrane.demo.repository.KupacRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class KupacService {

    @Autowired
    KupacRepository kupacRepository;

    public Kupac registracija(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, LocalDate datumRodjenja) {
        if(kupacRepository.findByKorisnickoIme(korisnickoIme) != null ) {
            return null; //ako vec postoji takav kupac sa tim korisnickim imenom vrati null
        }
        TipKupca tipKupca = new TipKupca("Bronzani", 0, 0);
        Kupac noviKupac = new Kupac(korisnickoIme, lozinka, ime, prezime, tipPola, datumRodjenja, 0, tipKupca);
        kupacRepository.save(noviKupac);
        return noviKupac;
    }

    public void save(Kupac kupac){
        kupacRepository.save(kupac);
    }
}
