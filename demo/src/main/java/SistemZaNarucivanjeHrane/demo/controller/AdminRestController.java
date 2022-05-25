package SistemZaNarucivanjeHrane.demo.controller;

import SistemZaNarucivanjeHrane.demo.dto.MenadzerDostavljacDto;
import SistemZaNarucivanjeHrane.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AdminRestController {

    @Autowired
    AdminService adminService;

    //TODO ne radi kada za ulogu stavimo DOSTAVLJAC ili dostavljac
    @PostMapping("/api/kreiraj_zaposlenog")
    public ResponseEntity<String> kreirajZaposlenog(@RequestBody MenadzerDostavljacDto menadzerDostavljacDto, HttpSession session) {
        return adminService.kreirajZaposlenog(menadzerDostavljacDto, session);

    }

}

