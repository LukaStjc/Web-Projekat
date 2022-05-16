package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.repository.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestoranService {

    @Autowired
    private RestoranRepository restoranRepository;
}
