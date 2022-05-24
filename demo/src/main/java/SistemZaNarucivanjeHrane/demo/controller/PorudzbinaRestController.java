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

        //TODO staviti i ovde new ArrayList<>() da se napravi objekat sa null da bude lepse
        List<Porudzbina> porudzbine = new ArrayList<>();
        List<PorudzbinaDto> porudzbineDto = new ArrayList<>();
        Set<ArtikalDto> artikliDto = new HashSet<>();

        if (ulogovaniKorisnik.getTipUloge().equals(TipUloge.MENADZER)) {
            Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;

            porudzbine = porudzbinaService.findAll();
            for (Porudzbina p : porudzbine) {
                if (p.getRestoran().getID().equals(ulogovaniMenadzer.getRestoran().getID())) {   // ako je ta porudzbina vezana za restoran menadzera
                    for (PorucenArtikal a : p.getPoruceniArtikli()) {
                        //TODO staviti ovo new u zagradu da bude cistije
                        ArtikalDto tmp1 = new ArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getArtikal().getTip(), a.getKolicina(), a.getArtikal().getOpis());
                        artikliDto.add(tmp1);
                    }
                    //TODO isto
                    PorudzbinaDto tmp2 = new PorudzbinaDto(artikliDto, p.getDatumIVreme(), p.getCena(), p.getStatus());
                    porudzbineDto.add(tmp2);
                }
            }
            return ResponseEntity.ok(porudzbineDto);
        }
        if (ulogovaniKorisnik.getTipUloge().equals(TipUloge.KUPAC)) {
            Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;

            for (Porudzbina p : ulogovaniKupac.getPorudzbine()) {
                for (PorucenArtikal a : p.getPoruceniArtikli()) {
                    artikliDto.add(new ArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getArtikal().getTip(), a.getKolicina(), a.getArtikal().getOpis()));
                }
                porudzbineDto.add(new PorudzbinaDto(artikliDto, p.getDatumIVreme(), p.getCena(), p.getStatus()));
            }
            return ResponseEntity.ok(porudzbineDto);
        }

        if (ulogovaniKorisnik.getTipUloge().equals(TipUloge.DOSTAVLJAC)) {    //TODO msm da nije zavrseno
            Dostavljac ulogovaniDostavljac = (Dostavljac) ulogovaniKorisnik;

            for (Porudzbina p : ulogovaniDostavljac.getPorudzbine()) {
                for (PorucenArtikal a : p.getPoruceniArtikli()) {
                    ArtikalDto tmp = new ArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getArtikal().getTip(), a.getKolicina(), a.getArtikal().getOpis());
                    artikliDto.add(tmp);
                }
                PorudzbinaDto tmp = new PorudzbinaDto(artikliDto, p.getDatumIVreme(), p.getCena(), p.getStatus());
                porudzbineDto.add(tmp);
            }
            return ResponseEntity.ok(porudzbineDto);
        }
        else
        {

            return new ResponseEntity("Ova funkcionalnost nije dozvoljena adminima", HttpStatus.BAD_REQUEST);
        }

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
        //zasto hash set
        Set<ArtikalDto> artikliDto = new HashSet<>();

        for (Porudzbina p : porudzbine) {
            for (PorucenArtikal a : p.getPoruceniArtikli()) {
                ArtikalDto tmp = new ArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getArtikal().getTip(), a.getKolicina(), a.getArtikal().getOpis());
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

   /*@PostMapping("poruci_artikal_iz_restorana/{id1}/{id2}") // id1 je za artikal, a id2 za restoran
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
       /* if (porudzbina.getRestoran() == null) {
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
    }*/

    @PostMapping("dodaj_u_korpu/{id_restorana}/{id_artikla}")
    public ResponseEntity<String> dodajUKorpu (@PathVariable Long id_artikla, @PathVariable Long id_restorana, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.KUPAC)
            return new ResponseEntity<>("Ova funkcionalnost je dozvoljena samo kupcima", HttpStatus.BAD_REQUEST);

        Artikal artikal = porudzbinaService.findArtikalById(id_artikla);
        if (artikal == null)
            return new ResponseEntity<>("Nije pronadjen taj artikal", HttpStatus.NOT_FOUND);
        if (!porudzbinaService.isArtikalURestoranu(id_artikla, id_restorana))
            return new ResponseEntity<>("Taj restoran nema izabrani artikal u ponudi", HttpStatus.NOT_FOUND);

        Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;
        Porudzbina porudzbina = new Porudzbina();
        boolean contains = false;
        //provera da li kupac vec ima korpu sa nekim artiklima
        for(Porudzbina p : ulogovaniKupac.getPorudzbine()) {
            if(p.getStatus().equals(Status.U_KORPI)) {
                porudzbina = p;
                contains = true;
                break;
            }
        }

        //ako nema dodajemo je
        if(!contains) {
            PorucenArtikal porucenArtikal = new PorucenArtikal(artikal,1);
            porudzbinaService.savePorucenArtikal(porucenArtikal);
            porudzbina.setStatus(Status.U_KORPI);
            porudzbina.setKupac(ulogovaniKupac);
            porudzbina.setRestoran(porudzbinaService.findRestoranById(id_restorana));
            porudzbinaService.save(porudzbina);
            porudzbina.dodajPoruceniArtikal(porucenArtikal);
            porudzbinaService.save(porudzbina);
            ulogovaniKupac.dodajPorudzbinu(porudzbina);
            porudzbinaService.saveKupac(ulogovaniKupac);
            return ResponseEntity.ok("Uspesno ste dodali artikal u korpu");
        }

        //postoji korpa sa nekim artiklima, ali ne znamo jel to taj artikal

        for(PorucenArtikal porucenArtikal : porudzbina.getPoruceniArtikli()) {
            if (porucenArtikal.getArtikal().getID().equals(artikal.getID())) {
                    porucenArtikal.setKolicina(porucenArtikal.getKolicina() + 1);
                    porudzbinaService.savePorucenArtikal(porucenArtikal);
                    porudzbinaService.save(porudzbina);
                    porudzbinaService.saveKupac(ulogovaniKupac);
                    return ResponseEntity.ok("Uspesno ste dodali artikal u korpu");
                }
            }

        PorucenArtikal porucenArtikal = new PorucenArtikal(artikal,1);
        porudzbinaService.savePorucenArtikal(porucenArtikal);
        porudzbina.dodajPoruceniArtikal(porucenArtikal);
        porudzbinaService.save(porudzbina);
       // ulogovaniKupac.dodajPorudzbinu(porudzbina);
        porudzbinaService.saveKupac(ulogovaniKupac);
        return ResponseEntity.ok("Uspesno ste dodali artikal u korpu");



    }

    @PostMapping("submit_korpe")
    public ResponseEntity<String> zavrsiSaKorpom(HttpSession session) {


        return ResponseEntity.ok("cao");
    }
}
