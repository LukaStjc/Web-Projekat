package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.dto.LoginDto;
import SistemZaNarucivanjeHrane.demo.dto.NoviKorisnikDto;
import SistemZaNarucivanjeHrane.demo.model.Korisnik;
import SistemZaNarucivanjeHrane.demo.model.TipPola;
import SistemZaNarucivanjeHrane.demo.model.TipUloge;
import SistemZaNarucivanjeHrane.demo.repository.KorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class KorisnikService {

    @Autowired
    KorisnikRepository korisnikRepository;

    @Autowired
    KupacService kupacService;

    public Korisnik findByKorisnickoIme(String korisnickoIme){
        return korisnikRepository.findByKorisnickoIme(korisnickoIme);
    }

    public List<Korisnik> findAll() {
        return korisnikRepository.findAll();
    }

    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    public ResponseEntity<String> login(LoginDto loginDto, HttpSession session) {
        if(loginDto.getKorisnickoIme().isEmpty() || loginDto.getLozinka().isEmpty()) {
            return new ResponseEntity<>("Uneli ste nevalidne podatke", HttpStatus.BAD_REQUEST);
        }

        Korisnik ulogovaniKorisnik = korisnikRepository.findByKorisnickoIme(loginDto.getKorisnickoIme());
        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Korisnik ne postoji", HttpStatus.NOT_FOUND);
        if(!ulogovaniKorisnik.getLozinka().equals(loginDto.getLozinka())){
            return new ResponseEntity<>("Pogresna lozinka", HttpStatus.BAD_REQUEST);
        }

        session.setAttribute("Korisnik",ulogovaniKorisnik);
        return ResponseEntity.ok("Korisnik uspesno ulogovan");
    }

    public ResponseEntity<String> logout(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niko nije ulogovan", HttpStatus.FORBIDDEN);

        session.invalidate();
        return ResponseEntity.ok("Uspesno ste se izlogovali");
    }

    public ResponseEntity<String> registracija(NoviKorisnikDto noviKorisnikDto){
        if(noviKorisnikDto.getKorisnickoIme().isEmpty() || noviKorisnikDto.getIme().isEmpty() || noviKorisnikDto.getDatumRodjenja().isEmpty() || noviKorisnikDto.getLozinka().isEmpty() || noviKorisnikDto.getPrezime().isEmpty() || noviKorisnikDto.getTipPola()==null) {
            return new ResponseEntity<>("Uneli ste nevalidne podatke", HttpStatus.BAD_REQUEST);
        }
        if(!(noviKorisnikDto.getTipPola().equals("MUSKI")) && !(noviKorisnikDto.getTipPola().equals("ZENSKI"))) {
            return new ResponseEntity<>("Uneli ste nevalidne podatke za pol", HttpStatus.BAD_REQUEST);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(noviKorisnikDto.getDatumRodjenja(), formatter);
        TipPola pol = TipPola.valueOf(noviKorisnikDto.getTipPola());

        Korisnik registrovaniKupac = kupacService.registracija(noviKorisnikDto.getKorisnickoIme(), noviKorisnikDto.getLozinka(), noviKorisnikDto.getIme(), noviKorisnikDto.getPrezime(), pol, datum);
        if(registrovaniKupac == null) {
            return new ResponseEntity<>("Uneli ste korisnicko ime koje vec postoji", HttpStatus.BAD_REQUEST);
        }
        else return ResponseEntity.ok("Uspesno ste se registrovali");
    }

    public ResponseEntity<Korisnik> getUlogovaniKorisnik(String korisnickoIme, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");
        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

        if(!korisnickoIme.equals(ulogovaniKorisnik.getKorisnickoIme()))
            return new ResponseEntity("Mozete da pristupite samo svojim podacima", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(findByKorisnickoIme(ulogovaniKorisnik.getKorisnickoIme()));
    }

    //TODO on kreira novog korisnika umesto da preko starog upise nove podatke
    public ResponseEntity<String> izmeniPodatkeUlogovanogKorisnika(NoviKorisnikDto noviKorisnikDto, HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if(ulogovaniKorisnik == null)
            return new ResponseEntity<>("Niste ulogovani", HttpStatus.BAD_REQUEST);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(noviKorisnikDto.getDatumRodjenja(), formatter);
        TipPola pol = TipPola.valueOf(noviKorisnikDto.getTipPola());

        ulogovaniKorisnik.setKorisnickoIme(noviKorisnikDto.getKorisnickoIme());
        ulogovaniKorisnik.setLozinka(noviKorisnikDto.getLozinka());
        ulogovaniKorisnik.setIme(noviKorisnikDto.getIme());
        ulogovaniKorisnik.setPrezime(noviKorisnikDto.getPrezime());
        ulogovaniKorisnik.setTipPola(pol);
        ulogovaniKorisnik.setDatumRodjenja(datum);

        session.setAttribute("Korisnik",ulogovaniKorisnik);

        korisnikRepository.save(ulogovaniKorisnik);
        return ResponseEntity.ok("Uspesno ste izmenili svoje podatke");
    }

    public ResponseEntity<List<Korisnik>> getKorisnici(HttpSession session){
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovaniKorisnik.getTipUloge() != TipUloge.ADMIN)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo administratorima", HttpStatus.BAD_REQUEST);

        List<Korisnik> korisnici = findAll();
        return ResponseEntity.ok(korisnici);
    }

    public ResponseEntity<List<Korisnik>> pretraga(HttpSession session, String parametar) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovaniKorisnik.getTipUloge() != TipUloge.ADMIN)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo administratorima", HttpStatus.BAD_REQUEST);

        List<Korisnik> sviKorisnici = findAll();
        List<Korisnik> trazeniKorisnici = new ArrayList<>();
        for(Korisnik korisnik : sviKorisnici) {
            if(korisnik.getIme().contains(parametar) || korisnik.getPrezime().contains(parametar) || korisnik.getKorisnickoIme().contains(parametar))
                trazeniKorisnici.add(korisnik);
        }

        return ResponseEntity.ok(trazeniKorisnici);


    }
}
