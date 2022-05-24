package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Artikal;
import SistemZaNarucivanjeHrane.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;

    public void save(Artikal artikal){
        artikalRepository.save(artikal);
    }

    public void delete(Artikal artikal){
        artikalRepository.delete(artikal);
    }

    public List<Artikal> findAll(){
        return artikalRepository.findAll();
    }

    public Artikal findArtikalById(Long id){
        List<Artikal> artikli = artikalRepository.findAll();
        for (Artikal a : artikli) {
            if (a.getID().equals(id)) {
                return a;
            }
        }
        return null;
    }

    public Artikal findById(Long id){
        return artikalRepository.findById(id).get();
    }

    public Artikal findByNaziv(String naziv) { return artikalRepository.findByNaziv(naziv); }
}
