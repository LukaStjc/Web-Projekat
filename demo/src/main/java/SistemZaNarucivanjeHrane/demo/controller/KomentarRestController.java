package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.KomentarDto;
import SistemZaNarucivanjeHrane.demo.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class KomentarRestController {

    @Autowired
    KomentarService komentarService;

    @PostMapping("komentar/{id}") //id porudzbine
    public ResponseEntity<String> dodajKomentar(@PathVariable UUID id, KomentarDto komentarDto, HttpSession session) {
        return komentarService.dodajKomentar(id, komentarDto, session);
    }
}
