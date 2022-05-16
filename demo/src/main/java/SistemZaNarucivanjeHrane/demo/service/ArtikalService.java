package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.repository.ArtikalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtikalService {

    @Autowired
    private ArtikalRepository artikalRepository;
}
