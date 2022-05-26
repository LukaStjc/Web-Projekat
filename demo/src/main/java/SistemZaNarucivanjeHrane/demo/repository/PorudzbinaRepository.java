package SistemZaNarucivanjeHrane.demo.repository;

import SistemZaNarucivanjeHrane.demo.model.Porudzbina;
import SistemZaNarucivanjeHrane.demo.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PorudzbinaRepository extends JpaRepository<Porudzbina, UUID> {
    List<Porudzbina> findAllByStatus(Status status);
    Optional<Porudzbina> findById(UUID id);
}
