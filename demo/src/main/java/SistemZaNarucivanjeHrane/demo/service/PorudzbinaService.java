package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Porudzbina;
import SistemZaNarucivanjeHrane.demo.model.Status;
import SistemZaNarucivanjeHrane.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    public List<Porudzbina> findAll() {
        return porudzbinaRepository.findAll();
    }

    public List<Porudzbina> findAllByStatus(Status status){return porudzbinaRepository.findAllByStatus(status);}
}
