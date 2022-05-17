package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.LoginDto;
import SistemZaNarucivanjeHrane.demo.dto.NoviKorisnikDto;
import SistemZaNarucivanjeHrane.demo.model.Korisnik;
import SistemZaNarucivanjeHrane.demo.model.TipPola;
import SistemZaNarucivanjeHrane.demo.model.TipUloge;
import SistemZaNarucivanjeHrane.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class KorisnikRestController {

    @Autowired
    KorisnikService korisnikService;

    @PostMapping("/api/registracija")
    public ResponseEntity<String> registracija(@RequestBody NoviKorisnikDto noviKorisnikDto) {
        if(noviKorisnikDto.getKorisnickoIme().isEmpty() || noviKorisnikDto.getIme().isEmpty() || noviKorisnikDto.getDatumRodjenja().isEmpty() || noviKorisnikDto.getLozinka().isEmpty() || noviKorisnikDto.getPrezime().isEmpty() || noviKorisnikDto.getTipPola()==null) {
            return new ResponseEntity("Uneli ste nevalidne podatke", HttpStatus.BAD_REQUEST);
        }
        if(!(noviKorisnikDto.getTipPola().equals("MUSKI")) && !(noviKorisnikDto.getTipPola().equals("ZENSKI"))) {
            return new ResponseEntity("Uneli ste nevalidne podatke", HttpStatus.BAD_REQUEST);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(noviKorisnikDto.getDatumRodjenja(), formatter);
        TipPola pol = TipPola.valueOf(noviKorisnikDto.getTipPola());

        Korisnik registrovaniKupac = korisnikService.registracija(noviKorisnikDto.getKorisnickoIme(), noviKorisnikDto.getLozinka(), noviKorisnikDto.getIme(), noviKorisnikDto.getPrezime(), pol, datum);
        if(registrovaniKupac == null) {
            return new ResponseEntity("Uneli ste korisnicko ime koje vec postoji", HttpStatus.BAD_REQUEST);
        }
        else return ResponseEntity.ok("Uspesno ste se registrovali");
    }

    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty()) {
            return new ResponseEntity("Uneli ste nevalidne podatke", HttpStatus.BAD_REQUEST);
        }

        Korisnik ulogovaniKorisnik = korisnikService.login(loginDto.getKorisnickoIme(), loginDto.getLozinka());
        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Korisnik ne postoji", HttpStatus.NOT_FOUND);

        session.setAttribute("Korisnik",ulogovaniKorisnik);
        return ResponseEntity.ok("Korisnik uspesno ulogovan");


    }

    @PostMapping("api/logout")
    public ResponseEntity logout(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niko nije ulogovan", HttpStatus.FORBIDDEN);

        session.invalidate();
        return new ResponseEntity("Uspesno ste se izlogovali", HttpStatus.OK);
    }

    //TODO videti za ovaj endpoint da li uopste staviti da mozes da vidis preko korisnickog imena ili da bude nesto samo /mojipodaci
    @GetMapping("/api/{korisnickoIme}")
    public ResponseEntity<Korisnik> getUlogovaniKorisnik(@PathVariable(name = "korisnickoIme") String korisnickoIme, HttpSession session) {

        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

        if(!korisnickoIme.equals(ulogovaniKorisnik.getKorisnickoIme()))
            return new ResponseEntity("Mozete da pristupite samo svojim podacima", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(korisnikService.findByKorisnickoIme(ulogovaniKorisnik.getKorisnickoIme()));

    }

    //TODO dodaj da izbaci neku poruku ako pokusas da promenis korisnicko ime u neko koje vec postoji
    @PutMapping("/api/izmena")
    public ResponseEntity<String> izmeniPodatkeUlogovanogKorisnika(@RequestBody NoviKorisnikDto noviKorisnikDto, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(noviKorisnikDto.getDatumRodjenja(), formatter);
        TipPola pol = TipPola.valueOf(noviKorisnikDto.getTipPola());

        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

        ulogovaniKorisnik.setKorisnickoIme(noviKorisnikDto.getKorisnickoIme());
        ulogovaniKorisnik.setLozinka(noviKorisnikDto.getLozinka());
        ulogovaniKorisnik.setIme(noviKorisnikDto.getIme());
        ulogovaniKorisnik.setPrezime(noviKorisnikDto.getPrezime());
        ulogovaniKorisnik.setTipPola(pol);
        ulogovaniKorisnik.setDatumRodjenja(datum);

        session.setAttribute("Korisnik",ulogovaniKorisnik);

        korisnikService.save(ulogovaniKorisnik);
        return ResponseEntity.ok("Uspesno ste izmenili svoje podatke");

    }

    @GetMapping("/api/korisnici")
    public ResponseEntity<List<Korisnik>> getKorisnici(HttpSession session) {

        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovaniKorisnik.getTipUloge() != TipUloge.ADMIN)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo administratorima", HttpStatus.BAD_REQUEST);

        List<Korisnik> korisnici = korisnikService.findAll();
        return ResponseEntity.ok(korisnici);
    }

}
