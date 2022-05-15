package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.model.Restoran;
import SistemZaNarucivanjeHrane.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;

    public Restoran findByNaziv(String naziv) {
        return restoranRepository.findByNaziv(naziv);
    }

    public List<Restoran> findAll() {
        return restoranRepository.findAll();
    }
}
