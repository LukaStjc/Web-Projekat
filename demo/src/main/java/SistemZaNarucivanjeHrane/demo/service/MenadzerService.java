package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.repository.MenadzerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenadzerService {

    @Autowired
    private MenadzerRepository menadzerRepository;
}
