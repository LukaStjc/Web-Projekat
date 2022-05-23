package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Korpa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany (fetch = FetchType.EAGER)
    private List<Artikal> artikli = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "kupac_id")
    private Kupac kupac;

    public Korpa() {
    }

    public Korpa(Kupac kupac) {
        this.kupac = kupac;
    }

    public List<Artikal> getArtikli() {
        return artikli;
    }

    public void setArtikli(List<Artikal> artikli) {
        this.artikli = artikli;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public double getCena() {
        double cena = 0;
        for (Artikal artikal : artikli) {
            cena += artikal.getCena();
        }
        return cena;
    }
}
