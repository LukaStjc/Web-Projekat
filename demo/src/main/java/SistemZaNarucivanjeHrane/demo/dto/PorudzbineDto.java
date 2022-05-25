package SistemZaNarucivanjeHrane.demo.dto;

import SistemZaNarucivanjeHrane.demo.model.Status;

import java.util.ArrayList;
import java.util.List;

public class PorudzbineDto {

    private List<PorucenArtikalDto> artikli = new ArrayList<>();
    private double ukupnaCena;
    private Status stanje;
    private String restoran;

    public PorudzbineDto(List<PorucenArtikalDto> artikli, double ukupnaCena, Status stanje, String restoran) {
        this.artikli = artikli;
        this.ukupnaCena = ukupnaCena;
        this.stanje = stanje;
        this.restoran = restoran;
    }

    public List<PorucenArtikalDto> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<PorucenArtikalDto> artikli) {
        this.artikli = artikli;
    }

    public double getUkupnaCena() {
        return ukupnaCena;
    }

    public void setUkupnaCena(double ukupnaCena) {
        this.ukupnaCena = ukupnaCena;
    }

    public void dodajArtikal(PorucenArtikalDto porucenArtikalDto) {
        this.artikli.add(porucenArtikalDto);
    }

    public Status getStanje() {
        return stanje;
    }

    public void setStanje(Status stanje) {
        this.stanje = stanje;
    }

    public String getRestoran() {
        return restoran;
    }

    public void setRestoran(String restoran) {
        this.restoran = restoran;
    }

    @Override
    public String toString() {
        return "PorudzbineDto {" +
                "artikli =" + artikli +
                ", ukupnaCena =" + ukupnaCena +
                '}';
    }
}
