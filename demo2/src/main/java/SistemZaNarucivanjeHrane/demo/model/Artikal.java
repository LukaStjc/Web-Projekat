package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Id;
import java.io.Serializable;

public class Artikal implements Serializable {

    @Id
    private String naziv;

    private TipArtikla tip;

    private Long kolicina; //grami ili mililitri????

    private String opis;

    public Artikal() {
    }

    public String getNaziv() { return naziv; }

    public void setNaziv(String naziv) { this.naziv = naziv; }

    public TipArtikla getTip() { return tip; }

    public void setTip(TipArtikla tip) { this.tip = tip; }

    public Long getKolicina() { return kolicina; }

    public void setKolicina(Long kolicina) { this.kolicina = kolicina; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }
}
