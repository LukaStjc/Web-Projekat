package SistemZaNarucivanjeHrane.demo.repository;

import SistemZaNarucivanjeHrane.demo.model.Menadzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenadzerRepository extends JpaRepository<Menadzer, Long> {

    public Menadzer findByKorisnickoIme(String korisnickoIme);
}
