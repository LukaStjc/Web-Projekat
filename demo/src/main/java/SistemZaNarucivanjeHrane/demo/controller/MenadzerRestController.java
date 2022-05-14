package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.model.Korisnik;
import SistemZaNarucivanjeHrane.demo.model.Menadzer;
import SistemZaNarucivanjeHrane.demo.model.Restoran;
import SistemZaNarucivanjeHrane.demo.model.TipUloge;
import SistemZaNarucivanjeHrane.demo.service.MenadzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class MenadzerRestController {

    @Autowired
    private MenadzerService menadzerService;

    //TODO videti da li ovako staviti mapiranje ili dodati neki menadzer id ali po meni nema potrebe kad vec uzimamo ulogovanog menadzera
    @GetMapping("/api/restoran")
    public ResponseEntity<Restoran> getRestorani(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        Restoran restoran = ulogovaniMenadzer.getRestoran();
        return ResponseEntity.ok(restoran);
    }
}
