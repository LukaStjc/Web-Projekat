package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PorucenArtikal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "artikal_id")
    private Artikal artikal;

    private int kolicina;

    public PorucenArtikal(Artikal artikal, int kolicina) {
        this.artikal = artikal;
        this.kolicina = kolicina;
    }

    public PorucenArtikal() {
    }

    public Artikal getArtikal() {
        return artikal;
    }

    public void setArtikal(Artikal artikal) {
        this.artikal = artikal;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

}
