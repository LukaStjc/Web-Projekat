package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.model.Restoran;
import SistemZaNarucivanjeHrane.demo.service.RestoranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("")
public class RestoranRestController {

    @Autowired
    private RestoranService restoranService;

    @GetMapping("/api/restoran/{naziv}")
    public ResponseEntity<Restoran> getRestoranPoNazivu(String naziv) {
        Restoran restoran = restoranService.findByNaziv(naziv);
        if (restoran == null) {
            return new ResponseEntity("Ne postoji restoran sa tim nazivom", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(restoran, HttpStatus.OK);
    }

    @GetMapping("/api/restoran/{tip}")
    public ResponseEntity<Restoran> getRestoranPoTipu(String tip) {
        Restoran restoran =restoranService.findByTip(tip);
        if(restoran==null){
            return new ResponseEntity("Ne postoji restoran sa tim tipom", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(restoran, HttpStatus.OK);
    }
}
