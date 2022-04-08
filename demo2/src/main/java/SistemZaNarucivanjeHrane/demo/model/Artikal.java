package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class Artikal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String naziv;
    private TipArtikla tip;
    private Long kolicina;
    private String opis;

    public Artikal() {
    }

    public Artikal(String naziv, TipArtikla tip, Long kolicina, String opis) {
        this.naziv = naziv;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }

    public void setNaziv(String naziv) { this.naziv = naziv; }

    public TipArtikla getTip() { return tip; }

    public void setTip(TipArtikla tip) { this.tip = tip; }

    public Long getKolicina() { return kolicina; }

    public void setKolicina(Long kolicina) { this.kolicina = kolicina; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }
}
