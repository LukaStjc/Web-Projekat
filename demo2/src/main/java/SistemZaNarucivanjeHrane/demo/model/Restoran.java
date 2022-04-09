package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Restoran implements Serializable {
    @Id
    private String naziv;

    private String tip;

    @OneToMany(mappedBy = "jelovnik", cascade = CascadeType.ALL) //kaskadiranje???
    private Set<Artikal> jelovnik = new HashSet<>();

    //jedna lokacija na jedan restoran
    //@OneToOne
    //private Lokacija lokacija;

    //TODO konstruktor kad zavrsis sa svim poljima

    public Restoran() {
    }

    public String getNaziv() { return naziv; }

    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getTip() { return tip; }

    public void setTip(String tip) { this.tip = tip; }

    public Set<Artikal> getJelovnik() { return jelovnik; }

    public void setJelovnik(Set<Artikal> jelovnik) { this.jelovnik = jelovnik; }
}
