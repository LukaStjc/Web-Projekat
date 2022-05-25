package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.repository.TipKupcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipKupcaService {

    @Autowired
    TipKupcaRepository tipKupcaRepository;
}
