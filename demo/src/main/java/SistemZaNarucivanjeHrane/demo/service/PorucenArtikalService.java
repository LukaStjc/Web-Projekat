package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.PorucenArtikal;
import SistemZaNarucivanjeHrane.demo.repository.PorucenArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PorucenArtikalService {

    @Autowired
    PorucenArtikalRepository porucenArtikalRepository;

    public void save(PorucenArtikal porucenArtikal){
        porucenArtikalRepository.save(porucenArtikal);
    }

    public void delete(PorucenArtikal porucenArtikal) { porucenArtikalRepository.delete(porucenArtikal);}
}
