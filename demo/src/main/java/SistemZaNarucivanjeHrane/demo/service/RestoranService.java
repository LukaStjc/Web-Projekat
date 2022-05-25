package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.dto.*;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class RestoranService {

    @Autowired
    RestoranRepository restoranRepository;

    @Autowired
    MenadzerService menadzerService;

    @Autowired
    LokacijaService lokacijaService;

    @Autowired
    ArtikalService artikalService;

    @Autowired
    KomentarService komentarService;

    public Restoran findRestoranById(Long id) {
        List<Restoran> restorani = restoranRepository.findAll();

        for (Restoran r : restorani) {
            if (r.getID().equals(id)) {
                return r;
            }
        }

        return null;
    }

    public List<Restoran> findAll() {
        return restoranRepository.findAll();
    }

    public Restoran save(Restoran restoran) {
        return restoranRepository.save(restoran);
    }

    public Menadzer findMenadzerByKorisnickoIme(String korisnickoIme) {
        return menadzerService.findByKorisnickoIme(korisnickoIme);
    }

    public Lokacija saveLokacija(Lokacija lokacija) {
        return lokacijaService.save(lokacija);
    }

    public Menadzer saveMenadzer(Menadzer menadzer) {
        return menadzerService.save(menadzer);
    }

    public void saveArtikal(Artikal artikal) {
        artikalService.save(artikal);
    }

    public List<Komentar> findAllKomentari() {
        return komentarService.findAll();
    }

    public void deleteArtikal(Artikal artikal) {
        artikalService.delete(artikal);
    }

    public boolean isArtikalURestoranu(Long id1, Long id2) { // id1 za artikal, 2 za restoran
        Restoran restoran = findRestoranById(id2);

        for (Artikal a : restoran.getJelovnik()) {
            if(a.getID().equals(id1)){
                return true;
            }
        }
        return false;
    }

    public ResponseEntity<String> removeArtikal(Long id, HttpSession session) {
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
                deleteArtikal(artikal);
                save(restoran);
                return ResponseEntity.ok("Uspesno obrisan artikal");
            }
        }

        return new ResponseEntity<>("Ne postoji artikal sa tim id-jem za restoran za koji je zaduzen ulogovani menadzer", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> changeArtikal( Long id, ArtikalDto artikalDto, HttpSession session) {
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

                saveArtikal(artikal);
                return ResponseEntity.ok("Uspesno izmenjen artikal");
            }
        }

        return new ResponseEntity<>("Nije pronadjen artikal sa tim id-jem u jelovniku restorana za koji ste zaduzeni", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Restoran> getRestoran(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        Restoran restoran = ulogovaniMenadzer.getRestoran();
        return ResponseEntity.ok(restoran);
    }

    public ResponseEntity<RestoranDetaljno> prikazOdabranogRestorana(Long id, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);

        Restoran restoran = findRestoranById(id);
        List<Komentar> komentari = new ArrayList<>();

        komentari = findAllKomentari();
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

    public ResponseEntity<String> kreirajRestoran(RestoranDto restoranDto, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.ADMIN)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo administratorima", HttpStatus.BAD_REQUEST);
        if (findMenadzerByKorisnickoIme(restoranDto.getMenadzer()) == null)
            return new ResponseEntity("Uneti menadzer ne postoji", HttpStatus.BAD_REQUEST);

        Lokacija lokacija = new Lokacija(restoranDto.getGeografskaDuzina(), restoranDto.getGeografskaSirina(), restoranDto.getAdresa());
        saveLokacija(lokacija);

        Restoran restoran = new Restoran(restoranDto.getNaziv(), restoranDto.getTip(), lokacija);
        save(restoran);

        Menadzer menadzer = findMenadzerByKorisnickoIme(restoranDto.getMenadzer());
        menadzer.setRestoran(restoran);
        saveMenadzer(menadzer);

        return ResponseEntity.ok("Uspesno dodat restoran");
    }

    public ResponseEntity<List<RestoranIzlazniDto>> getRestorani() {
        List<Restoran> restorani = findAll();
        List<RestoranIzlazniDto> izlazniRestorani = new ArrayList<>();
        for (Restoran restoran : restorani) {
            izlazniRestorani.add(new RestoranIzlazniDto(restoran.getNaziv(), restoran.getTip(), restoran.getLokacija().getAdresa()));
        }

        return ResponseEntity.ok(izlazniRestorani);
    }

    public ResponseEntity<List<Restoran>> getRestoranPoNazivu(@RequestBody RestoranDto restoranDto) {
        List<Restoran> restoranList = findAll();
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
