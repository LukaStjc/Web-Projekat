package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.NoviMenadzerDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.AdminService;
import SistemZaNarucivanjeHrane.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class AdminRestController {

    @Autowired
    AdminService adminService;

    @Autowired
    KorisnikService korisnikService;

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


    @PostMapping("/api/kreirajmenadzera")
    public ResponseEntity<String> kreirajMenadzera(@RequestBody NoviMenadzerDto noviMenadzerDto, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovaniKorisnik.getTipUloge() != TipUloge.ADMIN)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo administratorima", HttpStatus.BAD_REQUEST);
        if(adminService.findRestoranByNaziv(noviMenadzerDto.getRestoran()) == null)
            return new ResponseEntity("Uneli ste restoran koji ne postoji", HttpStatus.BAD_REQUEST);
        if(korisnikService.findByKorisnickoIme(noviMenadzerDto.getKorisnickoIme()) != null)
            return new ResponseEntity("Korisnicko ime vec postoji", HttpStatus.BAD_REQUEST);
        //da ne bi izbacivalo neki tekst greske u postmanu stavila sam ovo da lepo pise ako korisnicko ime vec postoji

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(noviMenadzerDto.getDatumRodjenja(), formatter);
        TipPola pol = TipPola.valueOf(noviMenadzerDto.getTipPola());
        Restoran restoran = adminService.findRestoranByNaziv(noviMenadzerDto.getRestoran());

        Menadzer menadzer = new Menadzer(noviMenadzerDto.getKorisnickoIme(), noviMenadzerDto.getLozinka(), noviMenadzerDto.getIme(), noviMenadzerDto.getPrezime(), pol, datum, restoran);
        adminService.saveMenadzer(menadzer);
        return ResponseEntity.ok("Uspesno ste kreirali menadzera");


    }


}

