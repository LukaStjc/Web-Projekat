package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private MenadzerService menadzerService;

    @Autowired
    private LokacijaService lokacijaService;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private KomentarService komentarService;

    public Restoran findByNaziv(String naziv) {
        return restoranRepository.findByNaziv(naziv);
    }

    public Restoran findByTip(String tip) {
        return restoranRepository.findByTip(tip);
    }

    public Restoran findRestoranById(Long id){
        List<Restoran> restorani = new ArrayList<>();
        restorani= restoranRepository.findAll();

        for(Restoran r:restorani){
            if(r.getID().equals(id)){
                return r;
            }
        }

        return null;
    }

    public List<Restoran> findAll() {
        return restoranRepository.findAll();
    }

    public Restoran save(Restoran restoran) { return restoranRepository.save(restoran); }

    public Menadzer findMenadzerByKorisnickoIme(String korisnickoIme) { return menadzerService.findByKorisnickoIme(korisnickoIme); }

    public Lokacija saveLokacija(Lokacija lokacija) {
        return lokacijaService.save(lokacija);
    }

    public Menadzer saveMenadzer(Menadzer menadzer) { return menadzerService.save(menadzer); }

    public void saveArtikal(Artikal artikal){
        artikalService.save(artikal);
    }

    public List<Komentar> findAllKomentari(){
        return komentarService.findAll();
    }

    public void deleteArtikal(Artikal artikal){
        artikalService.delete(artikal);
    }

}
