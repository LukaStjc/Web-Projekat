package SistemZaNarucivanjeHrane.demo.repository;

import SistemZaNarucivanjeHrane.demo.model.Kupac;
import SistemZaNarucivanjeHrane.demo.model.Porudzbina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface KupacRepository extends JpaRepository<Kupac,Long> {
    Kupac findByKorisnickoIme(String korisnickoIme);
}
