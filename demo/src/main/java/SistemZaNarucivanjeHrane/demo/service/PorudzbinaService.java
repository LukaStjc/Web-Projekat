package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorudzbinaService {

    @Autowired
    PorudzbinaRepository porudzbinaRepository;

    @Autowired
    ArtikalService artikalService;

    @Autowired
    RestoranService restoranService;

    @Autowired
    KupacService kupacService;

    @Autowired
    PorucenArtikalService porucenArtikalService;

    public List<Porudzbina> findAll() {
        return porudzbinaRepository.findAll();
    }

    public List<Porudzbina> findAllByStatus(Status status){return porudzbinaRepository.findAllByStatus(status);}

    public void saveKupac(Kupac kupac){
        kupacService.save(kupac);
    }

    public Artikal findArtikalById(Long id){
        return artikalService.findArtikalById(id);
    }

    public void savePorucenArtikal(PorucenArtikal porucenArtikal){
        porucenArtikalService.save(porucenArtikal);
    }

    public Restoran findRestoranById(Long id){
        return restoranService.findRestoranById(id);
    }

    public boolean isArtikalURestoranu(Long id1, Long id2){
        return restoranService.isArtikalURestoranu(id1, id2);
    }

    public void save(Porudzbina porudzbina){
        porudzbinaRepository.save(porudzbina);
    }
}
