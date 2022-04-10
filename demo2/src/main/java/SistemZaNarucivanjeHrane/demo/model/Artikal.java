package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.io.Serializable;

public class Artikal implements Serializable {

    //TODO svuda dodaj cascade i fetch type

    @Id
    private String naziv;

    private Long cena;

    @Enumerated(EnumType.STRING)
    private TipArtikla tip;

    private Long kolicina; //TODO grami ili mililitri????

    private String opis;

    public Artikal() {
    }

    public String getNaziv() { return naziv; }

    public void setNaziv(String naziv) { this.naziv = naziv; }

    public Long getCena() { return cena; }

    public void setCena(Long cena) { this.cena = cena; }

    public TipArtikla getTip() { return tip; }

    public void setTip(TipArtikla tip) { this.tip = tip; }

    public Long getKolicina() { return kolicina; }

    public void setKolicina(Long kolicina) { this.kolicina = kolicina; }

    public String getOpis() { return opis; }

    public void setOpis(String opis) { this.opis = opis; }

}
