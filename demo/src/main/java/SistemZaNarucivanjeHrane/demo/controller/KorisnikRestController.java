package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.KorisnikZaLoginDto;
import SistemZaNarucivanjeHrane.demo.dto.LoginDto;
import SistemZaNarucivanjeHrane.demo.dto.NoviKorisnikDto;
import SistemZaNarucivanjeHrane.demo.model.Korisnik;
import SistemZaNarucivanjeHrane.demo.model.TipUloge;
import SistemZaNarucivanjeHrane.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value="/api/")
public class KorisnikRestController {

    @Autowired
    KorisnikService korisnikService;

    @PostMapping("korisnik/registracija")
    public ResponseEntity<String> registracija(@RequestBody NoviKorisnikDto noviKorisnikDto) throws ParseException {
        return korisnikService.registracija(noviKorisnikDto);
    }

    @PostMapping("korisnik/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty()) {
            return new ResponseEntity("Uneli ste nevalidne podatke", HttpStatus.BAD_REQUEST);
        }

        Korisnik ulogovaniKorisnik = korisnikService.findByKorisnickoIme(loginDto.getKorisnickoIme());
        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Korisnik ne postoji", HttpStatus.NOT_FOUND);
        if(!ulogovaniKorisnik.getLozinka().equals(loginDto.getLozinka())){
            return new ResponseEntity("Pogresna lozinka", HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("Korisnik",ulogovaniKorisnik);
        String uloga = new String();

        if(ulogovaniKorisnik.getTipUloge().equals(TipUloge.KUPAC))
            uloga = "kupac";
        else if(ulogovaniKorisnik.getTipUloge().equals(TipUloge.ADMIN))
            uloga = "admin";
        else if(ulogovaniKorisnik.getTipUloge().equals(TipUloge.MENADZER))
            uloga = "menadzer";
        else
            uloga = "dostavljac";
        KorisnikZaLoginDto korisnikZaLoginDto = new KorisnikZaLoginDto(ulogovaniKorisnik.getKorisnickoIme(), ulogovaniKorisnik.getLozinka(), uloga);
        return ResponseEntity.ok(uloga);
    }

    @PostMapping("korisnik/logout")
    public ResponseEntity<String> logout(HttpSession session){
        return korisnikService.logout(session);
    }

    //TODO videti za ovaj endpoint da li uopste staviti da mozes da vidis preko korisnickog imena ili da bude nesto samo /mojipodaci
    @GetMapping("korisnik/{korisnickoIme}")
    public ResponseEntity<Korisnik> getUlogovaniKorisnik(@PathVariable(name = "korisnickoIme") String korisnickoIme, HttpSession session) {
        return korisnikService.getUlogovaniKorisnik(korisnickoIme, session);
    }

    //TODO dodaj da izbaci neku poruku ako pokusas da promenis korisnicko ime u neko koje vec postoji
    @PutMapping("korisnik/izmena")
    public ResponseEntity<String> izmeniPodatkeUlogovanogKorisnika(@RequestBody NoviKorisnikDto noviKorisnikDto, HttpSession session) {
        return korisnikService.izmeniPodatkeUlogovanogKorisnika(noviKorisnikDto, session);
    }

    @GetMapping("/korisnici")
    public ResponseEntity<List<Korisnik>> getKorisnici(HttpSession session) {
        return korisnikService.getKorisnici(session);
    }

    @GetMapping("/pretrazi_korisnike/{parametar}")
    public ResponseEntity<List<Korisnik>> pretraziKorisnike(HttpSession session, @PathVariable String parametar) {
        return korisnikService.pretraga(session, parametar);
    }

    @GetMapping("/korisnik/uloga")
    public ResponseEntity<String> getUloga(HttpSession session) {
        Korisnik korisnik = (Korisnik) session.getAttribute("Korisnik");
        if(korisnik.getTipUloge().equals(TipUloge.KUPAC))
            return ResponseEntity.ok("kupac");
        else if(korisnik.getTipUloge().equals(TipUloge.ADMIN))
            return ResponseEntity.ok("admin");
        else if(korisnik.getTipUloge().equals(TipUloge.MENADZER))
            return ResponseEntity.ok("menadzer");
        else
            return ResponseEntity.ok("dostavljac");
    }

}
