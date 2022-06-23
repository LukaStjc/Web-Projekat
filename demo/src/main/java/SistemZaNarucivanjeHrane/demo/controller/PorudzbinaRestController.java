package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.*;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping(value = "/api/")
public class PorudzbinaRestController {

    @Autowired
    PorudzbinaService porudzbinaService;

    @GetMapping("menadzer/porudzbine") //RADI
    public ResponseEntity<List<PorudzbinaMenadzerDto>> getPorudzbineForMenadzer(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if(!ulogovaniKorisnik.getTipUloge().equals(TipUloge.MENADZER))
            return new ResponseEntity("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);

        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;

        return ResponseEntity.ok(porudzbinaService.getPorudzbineFromMenadzer(ulogovaniMenadzer));

    }

    @GetMapping("kupac/porudzbine") //RADI
    public ResponseEntity<List<PorudzbineDto>> getPorudzbineForKupac(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if(!ulogovaniKorisnik.getTipUloge().equals(TipUloge.KUPAC))
            return new ResponseEntity("Ova funkcionalnost je dostupna samo menadzerima", HttpStatus.BAD_REQUEST);

        Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;

        return ResponseEntity.ok(porudzbinaService.getPorudzbineFromKupac(ulogovaniKupac));

    }

    @GetMapping("dostavljac/porudzbine") //TODO testiraj
    public ResponseEntity<List<PorudzbinaMenadzerDto>> getPorudzbineForDostavljac(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if(!ulogovaniKorisnik.getTipUloge().equals(TipUloge.DOSTAVLJAC))
            return new ResponseEntity("Ova funkcionalnost je dostupna samo dostavljacima", HttpStatus.BAD_REQUEST);

        Dostavljac ulogovaniDostavljac = (Dostavljac) ulogovaniKorisnik;

        return ResponseEntity.ok(porudzbinaService.getPorudzbineFromDostavljac(ulogovaniDostavljac));
    }

    @PostMapping("dodaj_u_korpu/{id2}/{id1}") // id1 je za artikal, a id2 za restoran, videti da li radi ako korpa ne postoji
    public ResponseEntity<String> dodajArtikalUKorpu(@PathVariable(name = "id1") Long id1, @PathVariable(name = "id2") Long id2, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.KUPAC)
            return new ResponseEntity<>("Ova funkcionalnost je dozvoljena samo kupcima", HttpStatus.BAD_REQUEST);

        Artikal artikal = porudzbinaService.findArtikalById(id1);
        if (artikal == null)
            return new ResponseEntity<>("Nije pronadjen taj artikal", HttpStatus.NOT_FOUND);
        if (!porudzbinaService.isArtikalURestoranu(id1, id2))
            return new ResponseEntity<>("Taj restoran nema izabrani artikal u ponudi", HttpStatus.NOT_FOUND);

        Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;
        Set<Porudzbina> uKPorudzbine = ulogovaniKupac.getPorudzbine();
        Porudzbina porudzbina = new Porudzbina();
        porudzbina.setRestoran(porudzbinaService.findRestoranById(id2));
        porudzbina.setKupac(ulogovaniKupac);
        porudzbina.setStatus(Status.U_KORPI);
        porudzbinaService.save(porudzbina);

        for (Porudzbina p : uKPorudzbine) {
            if (p.getStatus().equals(Status.U_KORPI)) {
                porudzbina = p;
                break;
            }
        }

        // ovo radim ako je novokreirana porudzbina, i za nju se samo zna da je "u korpi", jos uvek se ne zna za restoran i kupca
        if (porudzbina.getRestoran() == null) {
            porudzbina.setRestoran(porudzbinaService.findRestoranById(id2));
            porudzbinaService.save(porudzbina);
        }
        if (porudzbina.getKupac() == null) {
            porudzbina.setKupac(ulogovaniKupac);
            porudzbinaService.save(porudzbina);
        }

        for (PorucenArtikal pa : porudzbina.getPoruceniArtikli()) {    // proveravam da li vec postoji taj artikal u porudzbini, ako da, povecavam kolicinu
            if (pa.getArtikal().getID().equals(id1)) {
                pa.setKolicina(pa.getKolicina() + 1);     // povecavam kolicinu
                porudzbinaService.savePorucenArtikal(pa);
                porudzbina.setCena(porudzbina.getCena() + artikal.getCena()); // povecavam cenu
                porudzbinaService.save(porudzbina);
                porudzbinaService.saveKupac(ulogovaniKupac);

                return ResponseEntity.ok("Uspesno dodat artikal u porudzbinu");
            }
        }

        PorucenArtikal porucenArtikal = new PorucenArtikal(artikal, 1);
        porudzbinaService.savePorucenArtikal(porucenArtikal);
        porudzbina.getPoruceniArtikli().add(porucenArtikal);
        porudzbina.setCena(porudzbina.getCena() + artikal.getCena());
        porudzbinaService.save(porudzbina);
        ulogovaniKupac.dodajPorudzbinu(porudzbina);
        porudzbinaService.saveKupac(ulogovaniKupac);

        return ResponseEntity.ok("Uspesno dodat artikal u porudzbinu");
    }

    @DeleteMapping("izbaci_iz_korpe/{id}") //TODO greska foreign key
    public ResponseEntity<String> izbaciIzKorpe(@PathVariable Long id ,HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.KUPAC)
            return new ResponseEntity<>("Ova funkcionalnost je dozvoljena samo kupcima", HttpStatus.BAD_REQUEST);
        Artikal artikal = porudzbinaService.findArtikalById(id);
        if(artikal == null)
            return new ResponseEntity<>("Artikal sa unetim id-om ne postoji", HttpStatus.BAD_REQUEST);

        Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;
        //provera da li artikal postoji u korpi
        if(!porudzbinaService.daLiJeArtikalUKorpi(ulogovaniKupac, id))
            return new ResponseEntity<>("Artikal ne postoji u korpi", HttpStatus.BAD_REQUEST);

        porudzbinaService.deleteFromKorpa(ulogovaniKupac, id);

        return ResponseEntity.ok("Uspesno ste izbacili artikal iz korpe");
    }

    //TODO dodaj menjanje kolicine u korpu

    @GetMapping("pregled_korpe") //RADI
    public ResponseEntity<KorpaDto> pregledKorpe(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.KUPAC)
            return new ResponseEntity("Ova funkcionalnost je dozvoljena samo kupcima", HttpStatus.BAD_REQUEST);

        Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;

        return ResponseEntity.ok(porudzbinaService.getKorpa(ulogovaniKupac));

    }

    @PutMapping("submit_korpe") //stavila sam put mapping jer se menja status korpe
    public ResponseEntity<String> zavrsiSaKorpom(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.KUPAC)
            return new ResponseEntity("Ova funkcionalnost je dozvoljena samo kupcima", HttpStatus.BAD_REQUEST);
        Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;

        return ResponseEntity.ok(porudzbinaService.submitKorpe(ulogovaniKupac));
    }

    @PutMapping("poruzbina_u_pripremi/{id}") //RADI
    public ResponseEntity<String> porudzbinaUPripremi(@PathVariable UUID id, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER)
            return new ResponseEntity("Ova funkcionalnost je dozvoljena samo menadzerima", HttpStatus.BAD_REQUEST);
        if(porudzbinaService.findById(id) == null)
            return new ResponseEntity("Porudzbina sa datim UUID-om ne postoji", HttpStatus.BAD_REQUEST);
        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        if(!porudzbinaService.isMenadzerOfPorudzbina(ulogovaniMenadzer,id)) {
            return new ResponseEntity("Mozete da upravljate samo svojim porudzbinama", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(porudzbinaService.changeToUPripremi(id));
    }

    //TODO istestiraj
    @PutMapping("poruzbina_ceka_dostavljaca/{id1}/{id2}") //id1 id porudzbine, id2 id dostavljaca
    public ResponseEntity<String> porudzbinaCekaDostavljaca(@PathVariable UUID id, @PathVariable String id_d, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.MENADZER)
            return new ResponseEntity("Ova funkcionalnost je dozvoljena samo menadzerima", HttpStatus.BAD_REQUEST);
        if(porudzbinaService.findById(id) == null)
            return new ResponseEntity("Porudzbina sa datim UUID-om ne postoji", HttpStatus.BAD_REQUEST);
        Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
        if(!porudzbinaService.isMenadzerOfPorudzbina(ulogovaniMenadzer,id)) {
            return new ResponseEntity("Mozete da upravljate samo svojim porudzbinama", HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(porudzbinaService.changeToCekaDostavljaca(id, id_d));
    }



    @PutMapping("porudzbina_u_transportu/{id}") //TODO testiraj
    public ResponseEntity<String> porudzbinaUTransportu(@PathVariable UUID id, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.DOSTAVLJAC)
            return new ResponseEntity("Ova funkcionalnost je dozvoljena samo dostavljacima", HttpStatus.BAD_REQUEST);
        if(porudzbinaService.findById(id) == null)
            return new ResponseEntity("Porudzbina sa datim UUID-om ne postoji", HttpStatus.BAD_REQUEST);
        Dostavljac ulogovaniDostavljac = (Dostavljac) ulogovaniKorisnik;
        /*if(!porudzbinaService.isDostavljacOfPorudzbina(ulogovaniDostavljac,id)) {
            return new ResponseEntity("Mozete da upravljate samo svojim porudzbinama", HttpStatus.BAD_REQUEST);
        }*/

        return ResponseEntity.ok(porudzbinaService.changeToUTransportu(id));
    }

    @PutMapping("porudzbina_dostavljena/{id}") //TODO testiraj + vidi koliko korisnik ima bodova
    public ResponseEntity<String> porudzbinaDostavljena(@PathVariable UUID id, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.DOSTAVLJAC)
            return new ResponseEntity("Ova funkcionalnost je dozvoljena samo dostavljacima", HttpStatus.BAD_REQUEST);
        if(porudzbinaService.findById(id) == null)
            return new ResponseEntity("Porudzbina sa datim UUID-om ne postoji", HttpStatus.BAD_REQUEST);
        Dostavljac ulogovaniDostavljac = (Dostavljac) ulogovaniKorisnik;
        /*if(!porudzbinaService.isDostavljacOfPorudzbina(ulogovaniDostavljac,id)) {
            return new ResponseEntity("Mozete da upravljate samo svojim porudzbinama", HttpStatus.BAD_REQUEST);
        }*/

        return ResponseEntity.ok(porudzbinaService.changeToDostavljena(id));
    }


}
