package SistemZaNarucivanjeHrane.demo.repository;

import SistemZaNarucivanjeHrane.demo.model.TipKupca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipKupcaRepository extends JpaRepository<TipKupca,Long> {
}
