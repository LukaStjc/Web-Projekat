package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.LoginDto;
import SistemZaNarucivanjeHrane.demo.dto.NoviKorisnikDto;
import SistemZaNarucivanjeHrane.demo.model.Korisnik;
import SistemZaNarucivanjeHrane.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(value="/api/")
public class KorisnikRestController {

    @Autowired
    KorisnikService korisnikService;

    @PostMapping("korisnik/registracija")
    public ResponseEntity<String> registracija(@RequestBody NoviKorisnikDto noviKorisnikDto) {
        return korisnikService.registracija(noviKorisnikDto);
    }

    @PostMapping("korisnik/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        return korisnikService.login(loginDto, session);
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

}
