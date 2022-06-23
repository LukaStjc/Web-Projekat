package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.dto.KomentarDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Service
public class KomentarService {

    @Autowired
    KomentarRepository komentarRepository;

    @Autowired
    KupacService kupacService;

    @Autowired
    PorudzbinaService porudzbinaService;

    public List<Komentar> findAll(){
        return komentarRepository.findAll();
    }

    public ResponseEntity<String> dodajKomentar(UUID id, KomentarDto komentarDto, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if (ulogovaniKorisnik.getTipUloge() != TipUloge.KUPAC)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo kupcima", HttpStatus.BAD_REQUEST);
        if (porudzbinaService.findById(id) == null)
            return new ResponseEntity("Porudzbina sa unetim id-om ne postoji", HttpStatus.BAD_REQUEST);
        if (porudzbinaService.findById(id).getStatus() != Status.DOSTAVLJENA)
            return new ResponseEntity("Moguce je dodati komentar samo za porudzbinu koja je dostavljena", HttpStatus.BAD_REQUEST);

        Porudzbina porudzbina = porudzbinaService.findById(id);
        Kupac ulogovaniKupac = (Kupac) ulogovaniKorisnik;
        Komentar komentar = new Komentar(ulogovaniKupac, porudzbina.getRestoran(), komentarDto.getTekst(), komentarDto.getOcena());
        komentarRepository.save(komentar);
        return ResponseEntity.ok("Uspesno ste dodali komentar");

    }

    public Komentar save(Komentar komentar) { return komentarRepository.save(komentar); }
}
