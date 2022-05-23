package SistemZaNarucivanjeHrane.demo.repository;

import SistemZaNarucivanjeHrane.demo.model.Korpa;
import SistemZaNarucivanjeHrane.demo.model.Kupac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorpaRepository extends JpaRepository<Korpa, Long> {

    Korpa findByKupac(Kupac kupac);
}
