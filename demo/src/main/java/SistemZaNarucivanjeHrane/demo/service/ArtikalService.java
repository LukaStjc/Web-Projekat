package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Artikal;
import SistemZaNarucivanjeHrane.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
