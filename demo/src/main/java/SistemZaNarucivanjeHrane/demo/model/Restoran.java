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

    @OneToMany(fetch = FetchType.LAZY)  //undirektna
    @JoinColumn(name = "restoran_id")
    private Set<Artikal> jelovnik = new HashSet<>();

    @OneToOne   //undirektna
    private Lokacija lokacija;

    public Restoran() {
    }

    public Restoran(String naziv, String tip, Set<Artikal> jelovnik, Lokacija lokacija) {
        this.naziv = naziv;
        this.tip = tip;
        this.jelovnik = jelovnik;
        this.lokacija = lokacija;
    }

    public Long getID() { return ID; }

    public String getNaziv() { return naziv; }

    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getTip() { return tip; }

    public void setTip(String tip) { this.tip = tip; }

    public Set<Artikal> getJelovnik() { return jelovnik; }

    public Lokacija getLokacija() { return lokacija; }

    public void setLokacija(Lokacija lokacija) { this.lokacija = lokacija; }
}
