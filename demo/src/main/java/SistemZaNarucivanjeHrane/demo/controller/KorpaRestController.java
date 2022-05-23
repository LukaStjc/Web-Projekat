package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.KorpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class KorpaRestController {

    @Autowired
    KorpaService korpaService;

    @PostMapping ("/api/dodaj_u_korpu/{id_restorana}/{id_artikla}")
    public ResponseEntity<String> dodajUKorpu(@PathVariable Long id_restorana, @PathVariable Long id_artikla, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.KUPAC)
            return new ResponseEntity<>("Ova funkcionalnost je dozvoljena samo kupcima", HttpStatus.BAD_REQUEST);

        Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;

        Artikal artikal = korpaService.findArtikalById(id_artikla);

        if(korpaService.findByKupac(ulogovaniKupac) == null) {

            Korpa korpa = new Korpa();
            korpa.setKupac(ulogovaniKupac);
            korpa.getArtikli().add(artikal);
            korpaService.save(korpa);
            return ResponseEntity.ok("Uspesno ste dodali artikal u korpu");

        }
        else {
            Korpa korpa = korpaService.findByKupac(ulogovaniKupac);
            korpa.getArtikli().add(artikal);
            korpaService.save(korpa);
            return ResponseEntity.ok("Uspesno ste dodali artikal u korpu");
        }

    }
}
