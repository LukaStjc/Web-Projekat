package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.ArtikalDto;
import SistemZaNarucivanjeHrane.demo.dto.PorudzbinaDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/")
public class PorudzbinaRestController {

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping("porudzbine")
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbine(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

        List<Porudzbina> porudzbine;
        List<PorudzbinaDto> porudzbineDto = new ArrayList<>();
        Set<ArtikalDto> artikliDto = new HashSet<>();

        if (ulogovaniKorisnik.getTipUloge() == TipUloge.MENADZER) {
            Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
            Restoran restoran = ulogovaniMenadzer.getRestoran();

            porudzbine = porudzbinaService.findAll();
            for (Porudzbina p : porudzbine) {
                if (p.getRestoran().getID().equals(restoran.getID())) {   // ako je ta porudzbina vezana za restoran menadzera
                    for (Artikal a : p.getPoruceniArtikli()) {
                        ArtikalDto tmp1 = new ArtikalDto(a.getNaziv(), a.getCena(), a.getTip(), a.getKolicina(), a.getOpis());
                        artikliDto.add(tmp1);
                    }
                    PorudzbinaDto tmp2 = new PorudzbinaDto(artikliDto, p.getDatumIVreme(), p.getCena(), p.getStatus());
                    porudzbineDto.add(tmp2);
                }
            }

            if (porudzbineDto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(porudzbineDto);
            }

        } else if (ulogovaniKorisnik.getTipUloge() == TipUloge.KUPAC) {
            Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;

            for (Porudzbina p : ulogovaniKupac.getPorudzbine()) {
                for (Artikal a : p.getPoruceniArtikli()) {
                    ArtikalDto tmp = new ArtikalDto(a.getNaziv(), a.getCena(), a.getTip(), a.getKolicina(), a.getOpis());
                    artikliDto.add(tmp);
                }
                PorudzbinaDto tmp = new PorudzbinaDto(artikliDto, p.getDatumIVreme(), p.getCena(), p.getStatus());
                porudzbineDto.add(tmp);
            }

            if (porudzbineDto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(porudzbineDto);
            }

        } else if (ulogovaniKorisnik.getTipUloge() == TipUloge.DOSTAVLJAC) {    //TODO msm da nije zavrseno
            Dostavljac ulogovaniDostavljac = (Dostavljac) ulogovaniKorisnik;

            for (Porudzbina p : ulogovaniDostavljac.getPorudzbine()) {
                for (Artikal a : p.getPoruceniArtikli()) {
                    ArtikalDto tmp = new ArtikalDto(a.getNaziv(), a.getCena(), a.getTip(), a.getKolicina(), a.getOpis());
                    artikliDto.add(tmp);
                }
                PorudzbinaDto tmp = new PorudzbinaDto(artikliDto, p.getDatumIVreme(), p.getCena(), p.getStatus());
                porudzbineDto.add(tmp);
            }

            if (porudzbineDto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(porudzbineDto);
            }
        }
        return new ResponseEntity("TMP", HttpStatus.OK);    // dodao sam za sada da bih mogao da proverim program, inace kad se zavrsi ova funkcionalnost, izbrisacu
    }

    @GetMapping("porudzbine_na_cekanju")
    // ovu funkcionalnost ne mogu trenutno proveriti da li radi jer nisam iskucao kreiranje porudzbine kod korisnika
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbineNaCekanju(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.DOSTAVLJAC)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo dostavljacima", HttpStatus.BAD_REQUEST);

        List<Porudzbina> porudzbine = porudzbinaService.findAllByStatus(Status.CEKA_DOSTAVLJACA);
        List<PorudzbinaDto> porudzbineDto = new ArrayList<>();
        Set<ArtikalDto> artikliDto = new HashSet<>();

        for (Porudzbina p : porudzbine) {
            for (Artikal a : p.getPoruceniArtikli()) {
                ArtikalDto tmp = new ArtikalDto(a.getNaziv(), a.getCena(), a.getTip(), a.getKolicina(), a.getOpis());
                artikliDto.add(tmp);
            }
            PorudzbinaDto tmp = new PorudzbinaDto(artikliDto, p.getDatumIVreme(), p.getCena(), p.getStatus());
            porudzbineDto.add(tmp);
        }

        if (porudzbineDto.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return ResponseEntity.ok(porudzbineDto);
        }
    }

    @PostMapping("poruci_artikal_iz_restorana/{id1}/{id2}") // id1 je za artikal, a id2 za restoran
    public ResponseEntity<String> dodajArtikalUPorudzbinu(@PathVariable(name = "id1") Long id1, @PathVariable(name = "id2") Long id2, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.KUPAC)
            return new ResponseEntity<>("Ova funkcionalnost je dozvoljena samo kupcima", HttpStatus.BAD_REQUEST);

        if(!porudzbinaService.isArtikalURestoranu(id1, id2)){
            return new ResponseEntity<>("Taj restoran nema izabrani artikal u ponudi", HttpStatus.NOT_FOUND);
        }

        Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;
        Set<Porudzbina> uKPorudzbine = ulogovaniKupac.getPorudzbine();

        boolean novaPorudzbina = false;
        while (true) {
            if (uKPorudzbine.isEmpty() || novaPorudzbina) { // ako kupac trenutno nema porudzbine, moram da dodam porudzbinu
                Artikal tmp = porudzbinaService.findArtikalById(id1);
                if(tmp==null){
                    return new ResponseEntity<>("Nije pronadjen taj artikal", HttpStatus.NOT_FOUND);
                }

                Set<Artikal> poruceniArtikli = new HashSet<>();
                poruceniArtikli.add(tmp);

                Porudzbina nova = new Porudzbina(poruceniArtikli, porudzbinaService.findRestoranById(id2), LocalDateTime.now(), tmp.getCena(), ulogovaniKupac, Status.OBRADA);
                uKPorudzbine.add(nova);
                porudzbinaService.saveKupac(ulogovaniKupac);

                return new ResponseEntity<>("Uspesno dodat artikal u porudzbinu", HttpStatus.OK);
            }

        /* u ovom foru trazim onu porudzbinu koja je u stanju obrada jer to znaci da nije jos zavrsena i da na nju treba da se dodaju artikli
        takodje, porudzbinu ciji je id restorana vezan za restoran koji je izabran, jer se moze desiti da kupac ne zavrsi porudzbinu, nego da
        se prebaci na drugu porudzbinu, vezanu za drugi restoran, a onda ne smem upisati artikal u prethodnu porudzbinu, nego u novu
         */
            for (Porudzbina p : uKPorudzbine) {
                if (p.getStatus() == Status.OBRADA && p.getRestoran().getID().equals(id2)) {
                    Artikal tmp = porudzbinaService.findArtikalById(id1);
                    if(tmp==null){
                        return new ResponseEntity<>("Nije pronadjen taj artikal", HttpStatus.NOT_FOUND);
                    }

                    p.getPoruceniArtikli().add(tmp);
                    porudzbinaService.saveKupac(ulogovaniKupac);
                }
            }
            novaPorudzbina = true;
        }
    }
}
