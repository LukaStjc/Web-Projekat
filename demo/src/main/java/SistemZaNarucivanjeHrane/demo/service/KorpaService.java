package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Artikal;
import SistemZaNarucivanjeHrane.demo.model.Korpa;
import SistemZaNarucivanjeHrane.demo.model.Kupac;
import SistemZaNarucivanjeHrane.demo.repository.KorpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KorpaService {

    @Autowired
    private KorpaRepository korpaRepository;

    @Autowired
    private ArtikalService artikalService;

    public Artikal findArtikalByNaziv(String naziv) { return artikalService.findByNaziv(naziv); }

    public Artikal findArtikalById(Long id) { return artikalService.findArtikalById(id); }

    public Korpa findByKupac(Kupac kupac) { return korpaRepository.findByKupac(kupac); }

    public void save(Korpa korpa) { korpaRepository.save(korpa); }



}
