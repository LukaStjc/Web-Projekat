package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.model.Kupac;
import SistemZaNarucivanjeHrane.demo.service.KupacService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KupacRestController {

    @Autowired
    private KupacService kupacService;

    /*@PostMapping("/api/registracija")
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
        Kupac registrovaniKupac = kupacService.registracija(noviKorisnikDto.getKorisnickoIme(), noviKorisnikDto.getLozinka(), noviKorisnikDto.getIme(), noviKorisnikDto.getPrezime(), pol, datum);
        if(registrovaniKupac == null) {
            return new ResponseEntity("Uneli ste korisnicko ime koje vec postoji", HttpStatus.BAD_REQUEST);
        }
        else return ResponseEntity.ok("Uspesno ste se registrovali");
    }*/

    //klasicno mapiranje samo da bismo proverili da li je registrovan korisnik umemorisan
    @GetMapping("/api/kupci")
    public List<Kupac> getKupci() {
        List<Kupac> kupacList = kupacService.findAll();
        return kupacList;

    }

}
