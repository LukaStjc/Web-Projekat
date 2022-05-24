package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.*;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/")
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    //TODO dodati sliku kao parametar metode
    @Value("${file.upload-dir}")
    String FILE_DIRECTORY;

    @PostMapping("dodaj_artikal")
    public ResponseEntity<String> addArtikal(@RequestParam ArtikalSaSlikomDto artikalDto, HttpSession session) throws IOException {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER)
            return new ResponseEntity<>("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);

        File myFile = new File(FILE_DIRECTORY+ artikalDto.getSlika().getOriginalFilename());
        myFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(myFile);
        fos.write(artikalDto.getSlika().getBytes());
        Artikal artikal = new Artikal(artikalDto.getNaziv(), artikalDto.getCena(), artikalDto.getTip(), artikalDto.getKolicina(), artikalDto.getOpis());
        artikal.setSlika(myFile);
        restoranService.saveArtikal(artikal);
        fos.close();

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        Restoran restoran = ulogovaniMenadzer.getRestoran();

        restoran.getJelovnik().add(artikal);
        restoranService.save(restoran);

        return ResponseEntity.ok("Uspesno dodat artikal");

    }

    @DeleteMapping("ukloni_artikal/{id}")
    public ResponseEntity<String> removeArtikal(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER)
            return new ResponseEntity<>("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        Restoran restoran = ulogovaniMenadzer.getRestoran();

        for (Artikal artikal : restoran.getJelovnik()) {
            if (artikal.getID().equals(id)) {
                restoran.getJelovnik().remove(artikal);
                restoranService.deleteArtikal(artikal);
                restoranService.save(restoran);
                return ResponseEntity.ok("Uspesno obrisan artikal");
            }
        }

        return new ResponseEntity<>("Ne postoji artikal sa tim id-jem za restoran za koji je zaduzen ulogovani menadzer", HttpStatus.NOT_FOUND);

    }

    @PutMapping("izmeni_artikal/{id}")
    public ResponseEntity<String> changeArtikal(@PathVariable(name = "id") Long id, @RequestBody ArtikalDto artikalDto, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER)
            return new ResponseEntity<>("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        Restoran restoran = ulogovaniMenadzer.getRestoran();

        for (Artikal artikal : restoran.getJelovnik()) {
            if (artikal.getID().equals(id)) {
                if (artikalDto.getCena() != 0) {
                    artikal.setCena(artikalDto.getCena());
                }
                if (artikalDto.getKolicina() != 0) {
                    artikal.setKolicina(artikalDto.getKolicina());
                }
                if (artikalDto.getOpis() != null) {
                    artikal.setOpis(artikalDto.getOpis());
                }
                if (artikalDto.getTip() != null) {
                    artikal.setTip(artikalDto.getTip());
                }
                if (artikalDto.getNaziv() != null) {
                    artikal.setNaziv(artikalDto.getNaziv());
                }

                restoranService.saveArtikal(artikal);
                return ResponseEntity.ok("Uspesno izmenjen artikal");
            }
        }

        return new ResponseEntity<>("Nije pronadjen artikal sa tim id-jem u jelovniku restorana za koji ste zaduzeni", HttpStatus.NOT_FOUND);


    }

    @GetMapping("moj_restoran")
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

    /* ova funkcionalnost je povezana sa "pretrazi", jer je tamo prikazan uzi izbor restorana, a ovo je bas detaljan prikaz jednog
    restorana koji ce biti izabran. Stavio sam da ga trazi po id-ju, sto znaci da ce u frontendu biti potrebnu proslediti id ovoj
    funkciji iz funkcije pretrazi (npr klikom na sliku)*/
    @GetMapping("restoran/{id}")
    public ResponseEntity<RestoranDetaljno> prikazOdabranogRestorana(@PathVariable(name = "id") Long id, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);

        Restoran restoran = restoranService.findRestoranById(id);
        List<Komentar> komentari = new ArrayList<>();

        komentari = restoranService.findAllKomentari();
        // ovde ide provera da li je odredjeni komentar vezan za restoran, ako nije, brise se iz liste komentara i tako tamo ostaju
        // samo komentari koji su vezani bas za restoran koji treba da se prikaze
        komentari.removeIf(k -> !(k.getRestoran().getID().equals(restoran.getID())));
        List<KomentarDto> komentariDto = new ArrayList<>();

        double prosecnaOcena = 0.0;
        int brojKomentara = 0;
        for (Komentar k : komentari) {  // u ovoj petlji racunam prosecnu ocenu iz komentara a i pravim listu komentara Dto
            prosecnaOcena += k.getOcena();
            brojKomentara++;
            KomentarDto tmp = new KomentarDto(k.getTekst(), k.getOcena());
            komentariDto.add(tmp);
        }
        prosecnaOcena /= brojKomentara;

        RestoranDetaljno restoranDetaljno = new RestoranDetaljno(restoran.getNaziv(), restoran.getTip(), restoran.isRadi(), restoran.getLokacija(), komentariDto, restoran.getJelovnik(), prosecnaOcena);
        return ResponseEntity.ok(restoranDetaljno);
    }

    @PostMapping("kreiraj_restoran")
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

    @GetMapping("restorani")    // ovo ide kao pocetna strana gde se vide svi restorani
    public ResponseEntity<List<RestoranIzlazniDto>> getRestorani() {
        List<Restoran> restorani = restoranService.findAll();
        List<RestoranIzlazniDto> izlazniRestorani = new ArrayList<>();
        for (Restoran restoran : restorani) {
            izlazniRestorani.add(new RestoranIzlazniDto(restoran.getNaziv(), restoran.getTip(), restoran.getLokacija().getAdresa()));
        }

        return ResponseEntity.ok(izlazniRestorani);

    }

    @GetMapping("pretrazi")
    public ResponseEntity<List<Restoran>> getRestoranPoNazivu(@RequestBody RestoranDto restoranDto) {

        List<Restoran> restoranList = restoranService.findAll();
        List<Restoran> trazeniRestorani = new ArrayList<>();

        if (restoranDto.getNaziv() != null) {
            for (Restoran restoran : restoranList) {
                if (restoran.getNaziv().contains(restoranDto.getNaziv())) {
                    trazeniRestorani.add(restoran);
                }
            }
        }

        if (restoranDto.getTip() != null) {
            for (Restoran restoran : restoranList) {
                if (restoran.getTip().contains(restoranDto.getTip())) {
                    trazeniRestorani.add(restoran);
                }
            }
        }

        if (restoranDto.getAdresa() != null) {
            for (Restoran restoran : restoranList) {
                if (restoran.getLokacija().getAdresa().contains(restoranDto.getAdresa())) {
                    trazeniRestorani.add(restoran);
                }
            }
        }


        if (trazeniRestorani.isEmpty())
            return new ResponseEntity("Ne postoji restoran sa unetim nazivom", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(trazeniRestorani);
    }
}