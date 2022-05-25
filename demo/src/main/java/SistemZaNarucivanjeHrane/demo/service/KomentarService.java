package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Komentar;
import SistemZaNarucivanjeHrane.demo.repository.KomentarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KomentarService {

    @Autowired
    KomentarRepository komentarRepository;

    public List<Komentar> findAll(){
        return komentarRepository.findAll();
    }
}
