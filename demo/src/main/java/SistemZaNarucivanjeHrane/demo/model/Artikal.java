package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Artikal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false) //ne moze naziv da bude null
    private String naziv;

    private double cena;

    @Enumerated(EnumType.STRING)
    private TipArtikla tip;

    private double kolicina;

    private String opis;

    public Artikal() {
    }

    public Artikal(String naziv, double cena, TipArtikla tip, double kolicina, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public Long getID() { return ID; }

    public String getNaziv() { return naziv; }

    public void setNaziv(String naziv) { this.naziv = naziv; }

    public double getCena() { return cena; }

    public void setCena(double cena) { this.cena = cena; }

    public TipArtikla getTip() { return tip; }

    public void setTip(TipArtikla tip) { this.tip = tip; }

    public double getKolicina() { return kolicina; }

    public void setKolicina(double kolicina) { this.kolicina = kolicina; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

}
