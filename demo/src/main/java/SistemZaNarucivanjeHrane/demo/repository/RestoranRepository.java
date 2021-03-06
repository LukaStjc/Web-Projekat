package SistemZaNarucivanjeHrane.demo.repository;

import SistemZaNarucivanjeHrane.demo.model.Lokacija;
import SistemZaNarucivanjeHrane.demo.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran,Long> {

     Restoran findByNaziv(String naziv);
     Restoran findByTip(String tip);
     Restoran findByLokacija(Lokacija lokacija);
     Optional<Restoran> findById(Long id);
     void delete(Restoran restoran);
}
