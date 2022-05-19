package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.ArtikalDto;
import SistemZaNarucivanjeHrane.demo.model.Restoran;
import SistemZaNarucivanjeHrane.demo.dto.RestoranDto;
import SistemZaNarucivanjeHrane.demo.dto.RestoranIzlazniDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.ArtikalService;
import SistemZaNarucivanjeHrane.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private ArtikalService artikalService;

    //TODO dodati sliku kao parametar metode
    @PostMapping("/api/dodaj_artikal")
    public ResponseEntity<String> addArtikal(@RequestBody ArtikalDto artikalDto, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null) {
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER) {
            return new ResponseEntity<>("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);
        }

        Artikal artikal =new Artikal(artikalDto.getNaziv(), artikalDto.getCena(), artikalDto.getTip(), artikalDto.getKolicina(), artikalDto.getOpis());
        artikalService.save(artikal);

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        Restoran restoran = ulogovaniMenadzer.getRestoran();

        restoran.getJelovnik().add(artikal);
        restoranService.save(restoran);

        return ResponseEntity.ok("Uspesno dodat artikal");

    }

    @DeleteMapping("/api/ukloni_artikal/{id}")
    public ResponseEntity<String> obrisi_artikal(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null) {
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        }
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER) {
            return new ResponseEntity<>("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);
        }

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        Restoran restoran = ulogovaniMenadzer.getRestoran();

        for(Artikal artikal : restoran.getJelovnik()){
            if(artikal.getID().equals(id)){
                restoran.getJelovnik().remove(artikal);
                restoranService.save(restoran);
                return  ResponseEntity.ok("Uspesno obrisan artikal");
            }
        }

        return new ResponseEntity<>("Ne postoji artikal sa tim id-jem za restoran za koji je zaduzen ulogovani menadzer", HttpStatus.NOT_FOUND);

    }

    //TODO videti da li ovako staviti mapiranje ili dodati neki menadzer id ali po meni nema potrebe kad vec uzimamo ulogovanog menadzera
    //mozda da mapiranje bude /api/{menadzerId}/restoran
    //TODO srediti da ima i prikaz svih porudzbina za restoran
    @GetMapping("/api/restoran")
    public ResponseEntity<Restoran> getRestorani(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        Restoran restoran = ulogovaniMenadzer.getRestoran();
        return ResponseEntity.ok(restoran);
    }

    @PostMapping("/api/kreiraj_restoran")
    public ResponseEntity<String> kreirajRestoran(@RequestBody RestoranDto restoranDto, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.ADMIN)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo administratorima", HttpStatus.BAD_REQUEST);
        if (restoranService.findMenadzerByKorisnickoIme(restoranDto.getMenadzer()) == null)
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
        for (Restoran restoran : restorani) {
            izlazniRestorani.add(new RestoranIzlazniDto(restoran.getNaziv(), restoran.getTip(), restoran.getLokacija().getAdresa()));
        }

        return ResponseEntity.ok(izlazniRestorani);

    }

    @PostMapping("/api/pretrazi")
    public ResponseEntity<List<Restoran>> getRestoranPoNazivu(@RequestBody RestoranDto restoranDto) {

        List<Restoran> restoranList = restoranService.findAll();
        List<Restoran> trazeniRestorani = new ArrayList<>();

        if(restoranDto.getNaziv() != null) {
            for (Restoran restoran : restoranList) {
                if (restoran.getNaziv().contains(restoranDto.getNaziv())) {
                    trazeniRestorani.add(restoran);
                }
            }
        }

        if(restoranDto.getTip() != null) {
            for (Restoran restoran : restoranList) {
                if (restoran.getTip().contains(restoranDto.getTip())) {
                    trazeniRestorani.add(restoran);
                }
            }
        }

        if(restoranDto.getAdresa() != null) {
            for (Restoran restoran : restoranList) {
                if (restoran.getLokacija().getAdresa().contains(restoranDto.getAdresa())) {
                    trazeniRestorani.add(restoran);
                }
            }
        }


        if(trazeniRestorani.isEmpty())
            return new ResponseEntity("Ne postoji restoran sa unetim nazivom", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(trazeniRestorani);
    }
}
