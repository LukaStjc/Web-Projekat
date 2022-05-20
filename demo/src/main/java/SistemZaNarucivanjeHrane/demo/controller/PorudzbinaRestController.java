package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.PorudzbinaDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.service.PorudzbinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/porudzbine")
public class PorudzbinaRestController {

    @Autowired
    private PorudzbinaService porudzbinaService;

    @GetMapping
    public ResponseEntity<List<PorudzbinaDto>> getPorudzbine(HttpSession session) {
        Korisnik ulogovaniKorisnik = (Korisnik) session.getAttribute("Korisnik");

        if (ulogovaniKorisnik == null)
            return new ResponseEntity("Niste ulogovani", HttpStatus.BAD_REQUEST);

        List<Porudzbina> porudzbine = new ArrayList<>();
        List<PorudzbinaDto> porudzbineDto = new ArrayList<>();

        if (ulogovaniKorisnik.getTipUloge() == TipUloge.MENADZER) {
            Menadzer ulogovaniMenadzer = (Menadzer) ulogovaniKorisnik;
            Restoran restoran = ulogovaniMenadzer.getRestoran();

            porudzbine = porudzbinaService.findAll();
            for (Porudzbina p : porudzbine) {
                if (p.getRestoran().getID().equals(restoran.getID())) {   // ako je ta porudzbina vezana za restoran menadzera
                    PorudzbinaDto tmp = new PorudzbinaDto(p.getPoruceniArtikli(), p.getDatumIVreme(), p.getCena(), p.getStatus());
                    porudzbineDto.add(tmp);
                }
            }

            if (porudzbineDto.isEmpty()) {
                return new ResponseEntity("Trenutno nema porudzbina vezanih za Vas restoran", HttpStatus.NO_CONTENT);
            } else {
                return ResponseEntity.ok(porudzbineDto);
            }
        }
        return new ResponseEntity("TMP", HttpStatus.OK);    // dodao sam za sada da bih mogao da proverim program, inace kad se zavrsi ova funkcionalnost, izbrisacu
    }
}
