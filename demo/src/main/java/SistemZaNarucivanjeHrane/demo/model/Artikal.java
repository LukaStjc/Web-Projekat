package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;

public class Artikal implements Serializable {

    //TODO cao luka, svuda dodaj cascade i fetch type gde mislis da treba
    //slobodno dodaj i column gde mislis da treba, ja sam ti svuda navela koja je veza u pitanju, kontam to da ostavimo zakomentarisano i na odbrani da nam bude lakse
    //kod kupca u tvojoj klasi sam ti ostavila porukicu
    //i ostale porukice procitaj ali ja mislim da je to to, koliko sam ja nju shvatila na vezbama
    // :)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false) //ne moze naziv da bude null
    private String naziv;

    private Long cena;

    @Enumerated(EnumType.STRING)
    private TipArtikla tip;

    private Long kolicina;

    private String opis;

    public Artikal() {
    }

    public Artikal(String naziv, Long cena, TipArtikla tip, Long kolicina, String opis) {
        this.naziv = naziv;
        this.cena = cena;
        this.tip = tip;
        this.kolicina = kolicina;
        this.opis = opis;
    }

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

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
