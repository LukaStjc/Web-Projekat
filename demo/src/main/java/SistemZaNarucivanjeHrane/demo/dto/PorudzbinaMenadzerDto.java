package SistemZaNarucivanjeHrane.demo.dto;

import SistemZaNarucivanjeHrane.demo.model.Status;

import java.time.LocalDateTime;
import java.util.*;

public class PorudzbinaMenadzerDto {

    private UUID id;
    private List<PorucenArtikalDto> poruceniArtikli = new ArrayList<>();
    private LocalDateTime datumIVreme;
    private double cena;
    private String idKupca;
    private Status status;

    public PorudzbinaMenadzerDto(UUID id, List<PorucenArtikalDto> poruceniArtikli, LocalDateTime datumIVreme, double cena, String idKupca, Status status) {
        this.id = id;
        this.poruceniArtikli = poruceniArtikli;
        this.datumIVreme = datumIVreme;
        this.cena = cena;
        this.idKupca = idKupca;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<PorucenArtikalDto> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(List<PorucenArtikalDto> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public LocalDateTime getDatumIVreme() {
        return datumIVreme;
    }

    public void setDatumIVreme(LocalDateTime datumIVreme) {
        this.datumIVreme = datumIVreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public String getIdKupca() {
        return idKupca;
    }

    public void setIdKupca(String idKupca) {
        this.idKupca = idKupca;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
