package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private KupacService kupacService;

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

    public Restoran findRestoranById(Long id){
        return restoranService.findRestoranById(id);
    }

    public boolean isArtikalURestoranu(Long id1, Long id2){
        return restoranService.isArtikalURestoranu(id1, id2);
    }
}
