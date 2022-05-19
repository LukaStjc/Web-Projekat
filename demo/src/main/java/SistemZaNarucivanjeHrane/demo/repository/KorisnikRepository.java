package SistemZaNarucivanjeHrane.demo.repository;

import SistemZaNarucivanjeHrane.demo.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik,String> {

    Korisnik findByKorisnickoIme(String korisnickoIme);

    //Korisnik save(Korisnik korisnik);
}
