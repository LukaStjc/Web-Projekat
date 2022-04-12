package SistemZaNarucivanjeHrane.demo.repository;

import SistemZaNarucivanjeHrane.demo.model.Artikal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtikalRepository extends JpaRepository<Artikal, Long> {
}
