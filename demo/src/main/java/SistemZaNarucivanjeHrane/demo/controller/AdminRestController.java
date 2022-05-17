package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.MenadzerDostavljacDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.AdminService;
import SistemZaNarucivanjeHrane.demo.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class AdminRestController {

    @Autowired
    AdminService adminService;

    @Autowired
    KorisnikService korisnikService;


    //TODO ne radi kada za ulogu stavimo DOSTAVLJAC ili dostavljac
    //TODO videti u koji rest controller ovo staviti
    @PostMapping("/api/kreiraj_zaposlenog")
    public ResponseEntity<String> kreirajZaposlenog(@RequestBody MenadzerDostavljacDto menadzerDostavljacDto, HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if(ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani.", HttpStatus.BAD_REQUEST);
        if(ulogovaniKorisnik.getTipUloge() != TipUloge.ADMIN)
            return new ResponseEntity("Ova funkcionalnost je dostupna samo administratorima", HttpStatus.BAD_REQUEST);
        if(korisnikService.findByKorisnickoIme(menadzerDostavljacDto.getKorisnickoIme()) != null)
            return new ResponseEntity("Korisnicko ime vec postoji", HttpStatus.BAD_REQUEST);
        //da ne ispise neku gresku u postmanu nego da bude lepo obavestenje

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate datum = LocalDate.parse(menadzerDostavljacDto.getDatumRodjenja(), formatter);
        TipPola pol = TipPola.valueOf(menadzerDostavljacDto.getTipPola());

        if(menadzerDostavljacDto.getUloga().equals("Menadzer")){
            Menadzer menadzer = new Menadzer(menadzerDostavljacDto.getKorisnickoIme(), menadzerDostavljacDto.getLozinka(), menadzerDostavljacDto.getIme(), menadzerDostavljacDto.getPrezime(), pol, datum, null);
            adminService.saveMenadzer(menadzer);
            return ResponseEntity.ok("Uspesno kreiran menadzer");
        }

        if(menadzerDostavljacDto.getUloga().equals("Dostavljac")){
            Dostavljac dostavljac = new Dostavljac(menadzerDostavljacDto.getKorisnickoIme(), menadzerDostavljacDto.getLozinka(), menadzerDostavljacDto.getIme(), menadzerDostavljacDto.getPrezime(), pol, datum);
            adminService.saveDostavljac(dostavljac);
            return ResponseEntity.ok("Uspesno kreiran dostavljac");
        }

        return new ResponseEntity<>("Dozvoljeno je kreirati samo dostavljaca ili menadzera", HttpStatus.BAD_REQUEST);

    }

}

