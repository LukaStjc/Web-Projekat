package SistemZaNarucivanjeHrane.demo.dto;

import SistemZaNarucivanjeHrane.demo.model.TipArtikla;
import org.springframework.web.multipart.MultipartFile;

public class ArtikalSaSlikomDto {
    private String naziv;
    private double cena;
    private TipArtikla tip;
    private double kolicina;
    private String opis;
    private MultipartFile slika;

    public ArtikalSaSlikomDto(String naziv, double cena, TipArtikla tip, double kolicina, String opis, MultipartFile slika) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
        this.slika = slika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public TipArtikla getTip() {
        return tip;
    }

    public void setTip(TipArtikla tip) {
        this.tip = tip;
    }

    public double getKolicina() {
        return kolicina;
    }

    public void setKolicina(double kolicina) {
        this.kolicina = kolicina;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public MultipartFile getSlika() {
        return slika;
    }

    public void setSlika(MultipartFile slika) {
        this.slika = slika;
    }
}
