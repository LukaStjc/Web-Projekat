package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false)
    private String naziv;

    private String tip;

    @OneToMany(fetch = FetchType.EAGER)  //undirektna, bila neka greska u postmanu pa mora eager
    @JoinColumn(name = "restoran_id")
    private Set<Artikal> jelovnik = new HashSet<>();

    @OneToOne   //undirektna
    @JoinColumn(name = "lokacija_id")
    private Lokacija lokacija;

    boolean radi;

    public Restoran() {
    }

    //ne menjaj konstruktor, neka bude null jelovnik na pocetku uvek, a posle ako hoces setuj ga preko setera
    public Restoran(String naziv, String tip, Lokacija lokacija) {
        this.naziv = naziv;
        this.tip = tip;
        this.lokacija = lokacija;
        radi = true;
    }

    public Long getID() {
        return ID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Set<Artikal> getJelovnik() {
        return jelovnik;
    }

    public void setJelovnik(Set<Artikal> jelovnik) {
        this.jelovnik = jelovnik;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public boolean isRadi() {
        return radi;
    }

    public void setRadi(boolean radi) {
        this.radi = radi;
    }
}
