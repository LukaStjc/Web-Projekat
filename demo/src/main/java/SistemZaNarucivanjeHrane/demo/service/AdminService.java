package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Dostavljac;
import SistemZaNarucivanjeHrane.demo.model.Menadzer;
import SistemZaNarucivanjeHrane.demo.model.Restoran;
import SistemZaNarucivanjeHrane.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    RestoranService restoranService;

    @Autowired
    MenadzerService menadzerService;

    @Autowired
    DostavljacService dostavljacService;

    public Restoran findRestoranByNaziv(String naziv) {
        return restoranService.findByNaziv(naziv);
    }

    public Menadzer saveMenadzer(Menadzer menadzer) {
        return menadzerService.save(menadzer);
    }

    public Dostavljac saveDostavljac(Dostavljac dostavljac) {
        return dostavljacService.save(dostavljac);
    }
}
