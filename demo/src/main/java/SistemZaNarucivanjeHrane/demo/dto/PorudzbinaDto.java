package SistemZaNarucivanjeHrane.demo.dto;

import SistemZaNarucivanjeHrane.demo.model.Status;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class PorudzbinaDto {

    private Set<ArtikalDto> poruceniArtikli = new HashSet<>();
    private LocalDateTime datumIVreme;
    private double cena;
    private Status status;

    public PorudzbinaDto(Set<ArtikalDto> poruceniArtikli, LocalDateTime datumIVreme, double cena, Status status) {
        this.poruceniArtikli = poruceniArtikli;
        this.datumIVreme = datumIVreme;
        this.cena = cena;
        this.status = status;
    }

    public Set<ArtikalDto> getPoruceniArtikli() {
        return poruceniArtikli;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Porudzbina: {" +
                "poruceniArtikli =" + poruceniArtikli +
                ", datumIVreme =" + datumIVreme +
                ", cena =" + cena +
                ", status =" + status +
                '}';
    }
}
