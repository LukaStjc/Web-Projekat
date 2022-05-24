package SistemZaNarucivanjeHrane.demo.dto;

import SistemZaNarucivanjeHrane.demo.model.TipArtikla;

public class ArtikalDto {
    private String naziv;
    private double cena;
    private TipArtikla tip;
    private double kolicina;
    private String opis;

    public ArtikalDto(String naziv, double cena, TipArtikla tip, double kolicina, String opis){
        this.naziv=naziv;
        this.cena=cena;
        this.tip=tip;
        this.kolicina=kolicina;
        this.opis=opis;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCena() {
        return cena;
    }

    public TipArtikla getTip() {
        return tip;
    }

    public double getKolicina() {
        return kolicina;
    }

    public String getOpis() {
        return opis;
    }

    @Override
    public String toString() {
        return "Artikal: {" +
                "naziv ='" + naziv + '\'' +
                ", cena =" + cena +
                ", tip =" + tip +
                ", kolicina =" + kolicina +
                ", opis ='" + opis + '\'' +
                '}';
    }
}
