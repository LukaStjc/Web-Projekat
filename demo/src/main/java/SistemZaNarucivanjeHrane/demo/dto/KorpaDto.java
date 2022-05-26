package SistemZaNarucivanjeHrane.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class KorpaDto {

    private List<PorucenArtikalDto> poruceniArtikli = new ArrayList<>();
    private double cena;

    public KorpaDto(List<PorucenArtikalDto> poruceniArtikli, double cena) {
        this.poruceniArtikli = poruceniArtikli;
        this.cena = cena;
    }

    public List<PorucenArtikalDto> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public void setPoruceniArtikli(List<PorucenArtikalDto> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
