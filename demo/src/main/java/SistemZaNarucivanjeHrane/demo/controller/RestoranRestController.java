package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.ArtikalDto;
import SistemZaNarucivanjeHrane.demo.dto.RestoranDetaljno;
import SistemZaNarucivanjeHrane.demo.dto.RestoranDto;
import SistemZaNarucivanjeHrane.demo.dto.RestoranIzlazniDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class RestoranRestController {

    @Autowired
    RestoranService restoranService;

    @PostMapping("moj_restoran/dodaj_artikal")
    public ResponseEntity<String> addArtikal(@RequestParam ArtikalDto artikalDto, HttpSession session) throws IOException {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER)
            return new ResponseEntity<>("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);


        Artikal artikal = new Artikal(artikalDto.getNaziv(), artikalDto.getCena(), artikalDto.getTip(), artikalDto.getKolicina(), artikalDto.getOpis());
        restoranService.saveArtikal(artikal);

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        Restoran restoran = ulogovaniMenadzer.getRestoran();

        restoran.getJelovnik().add(artikal);
        restoranService.save(restoran);

        return ResponseEntity.ok("Uspesno dodat artikal");

    }

    @DeleteMapping("moj_restoran/ukloni_artikal/{id}")
    public ResponseEntity<String> removeArtikal(@PathVariable(name = "id") Long id, HttpSession session) {
        return restoranService.removeArtikal(id, session);
    }

    @PutMapping("moj_restoran/izmeni_artikal/{id}")
    public ResponseEntity<String> changeArtikal(@PathVariable(name = "id") Long id, @RequestBody ArtikalDto artikalDto, HttpSession session) {
        return restoranService.changeArtikal(id, artikalDto, session);
    }

    @GetMapping("moj_restoran")
    public ResponseEntity<Restoran> getRestoran(HttpSession session) {
        return restoranService.getRestoran(session);
    }

    /* ova funkcionalnost je povezana sa "pretrazi", jer je tamo prikazan uzi izbor restorana, a ovo je bas detaljan prikaz jednog
    restorana koji ce biti izabran. Stavio sam da ga trazi po id-ju, sto znaci da ce u frontendu biti potrebnu proslediti id ovoj
    funkciji iz funkcije pretrazi (npr klikom na sliku)*/
    @GetMapping("restoran/{id}")
    public ResponseEntity<RestoranDetaljno> prikazOdabranogRestorana(@PathVariable(name = "id") Long id, HttpSession session) {
        return restoranService.prikazOdabranogRestorana(id, session);
    }

    @PostMapping("restoran/kreiraj")
    public ResponseEntity<String> kreirajRestoran(@RequestBody RestoranDto restoranDto, HttpSession session) {
        return restoranService.kreirajRestoran(restoranDto, session);
    }

    @GetMapping("restorani")    // ovo ide kao pocetna strana gde se vide svi restorani
    public ResponseEntity<List<RestoranIzlazniDto>> getRestorani() {
        return restoranService.getRestorani();
    }

    @GetMapping("restoran/pretrazi") //path ili kveri
    public ResponseEntity<List<Restoran>> getRestoranPoNazivu(@RequestBody RestoranDto restoranDto) {
        return restoranService.getRestoranPoNazivu(restoranDto);
    }

    @DeleteMapping("obrisi_restoran/{id}") //id restorana koji se brise
    public ResponseEntity<String> deleteRestoran(@PathVariable Long id, HttpSession session) {
        return restoranService.obrisiRestoran(session, id);
    }



}