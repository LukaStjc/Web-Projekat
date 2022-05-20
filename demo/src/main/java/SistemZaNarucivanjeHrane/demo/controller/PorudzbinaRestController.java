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

        List<Porudzbina> porudzbine = new ArrayList<>();
        List<PorudzbinaDto> porudzbineDto = new ArrayList<>();
        Set<ArtikalDto> artikliDto = new HashSet<>();

        if (ulogovaniKorisnik.getTipUloge() == TipUloge.MENADZER) {
            Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
            Restoran restoran = ulogovaniMenadzer.getRestoran();

            porudzbine = porudzbinaService.findAll();
            for (Porudzbina p : porudzbine) {
                if (p.getRestoran().getID().equals(restoran.getID())) {   // ako je ta porudzbina vezana za restoran menadzera
                    for (Artikal a : p.getPoruceniArtikli()) {
                        ArtikalDto tmp = new ArtikalDto(a.getNaziv(), a.getCena(), a.getTip(), a.getKolicina(), a.getOpis());
                        artikliDto.add(tmp);
                    }
                    PorudzbinaDto tmp = new PorudzbinaDto(artikliDto, p.getDatumIVreme(), p.getCena(), p.getStatus());
                    porudzbineDto.add(tmp);
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

        } else if (ulogovaniKorisnik.getTipUloge() == TipUloge.DOSTAVLJAC) {
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

    @PutMapping("poruci_artikal_iz_restorana/{id}") // id je za artikal
    public ResponseEntity<String> dodajArtikalUPorudzbinu(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if(ulogovaniKorisnik==null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if(ulogovaniKorisnik.getTipUloge()!=TipUloge.KUPAC)
            return new ResponseEntity<>("Ova funkcionalnost je dozvoljena samo kupcima", HttpStatus.BAD_REQUEST);


    }
}
