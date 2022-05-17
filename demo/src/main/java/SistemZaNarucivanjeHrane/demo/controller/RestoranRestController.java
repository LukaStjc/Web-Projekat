package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.model.Restoran;
import SistemZaNarucivanjeHrane.demo.dto.RestoranDto;
import SistemZaNarucivanjeHrane.demo.dto.RestoranIzlazniDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @GetMapping("/api/restoran/{naziv}")
    public ResponseEntity<Restoran> getRestoranPoNazivu(String naziv) {
        Restoran restoran = restoranService.findByNaziv(naziv);
        if (restoran == null) {
            return new ResponseEntity("Ne postoji restoran sa tim nazivom", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(restoran, HttpStatus.OK);
    }

    @GetMapping("/api/restoran/{tip}")
    public ResponseEntity<Restoran> getRestoranPoTipu(String tip) {
        Restoran restoran = restoranService.findByTip(tip);
        if (restoran == null) {
            return new ResponseEntity("Ne postoji restoran sa tim tipom", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(restoran, HttpStatus.OK);
    }


    //TODO videti da li ovako staviti mapiranje ili dodati neki menadzer id ali po meni nema potrebe kad vec uzimamo ulogovanog menadzera
    //mozda da mapiranje bude /api/{menadzerId}/restoran
    //TODO srediti da ima i prikaz svih porudzbina za restoran
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

    @PostMapping("/api/kreiraj_restoran")
    public ResponseEntity<String> kreirajRestoran(@RequestBody RestoranDto restoranDto, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovaniKorisnik.getTipUloge() != TipUloge.ADMIN)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo administratorima", HttpStatus.BAD_REQUEST);
        if(restoranService.findMenadzerByKorisnickoIme(restoranDto.getMenadzer()) == null)
            return new ResponseEntity("Uneti menadzer ne postoji", HttpStatus.BAD_REQUEST);

        Lokacija lokacija = new Lokacija(restoranDto.getGeografskaDuzina(), restoranDto.getGeografskaSirina(), restoranDto.getAdresa());
        restoranService.saveLokacija(lokacija);

        Restoran restoran = new Restoran(restoranDto.getNaziv(), restoranDto.getTip(), lokacija);
        restoranService.save(restoran);

        Menadzer menadzer = restoranService.findMenadzerByKorisnickoIme(restoranDto.getMenadzer());
        menadzer.setRestoran(restoran);
        restoranService.saveMenadzer(menadzer);

        return ResponseEntity.ok("Uspesno dodat restoran");

    }

    //TODO da budu samo neke osnovne informacije o restoranima ne bas sve
    @GetMapping("api/restorani")
    public ResponseEntity<List<RestoranIzlazniDto>> getRestorani() {
        List<Restoran> restorani = restoranService.findAll();
        List<RestoranIzlazniDto> izlazniRestorani = new ArrayList<>();
        for(Restoran restoran : restorani) {
            izlazniRestorani.add(new RestoranIzlazniDto(restoran.getNaziv(), restoran.getTip(), restoran.getLokacija().getAdresa()));
        }

        return ResponseEntity.ok(izlazniRestorani);

    }
}
