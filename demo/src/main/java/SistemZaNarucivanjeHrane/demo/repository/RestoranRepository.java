package SistemZaNarucivanjeHrane.demo.repository;

import SistemZaNarucivanjeHrane.demo.model.Lokacija;
import SistemZaNarucivanjeHrane.demo.model.Restoran;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestoranRepository extends JpaRepository<Restoran,Long> {

    public Restoran findByNaziv(String naziv);
    public Restoran findByTip(String tip);
    public Restoran findByLokacija(Lokacija lokacija);
}
