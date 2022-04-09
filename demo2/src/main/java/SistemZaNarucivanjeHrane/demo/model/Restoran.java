package SistemZaNarucivanjeHrane.demo.model;

import com.sun.xml.bind.v2.TODO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;

@Entity
public class Restoran implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String naziv;
    private String tip;
    private ArrayList<Artikal> jelovnik;
    //private Lokacija lokacija;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Menadzer menadzer;

    //TODO mapiraj povezanost izmedju artikla i restorana

    //TODO konstruktor kad zavrsis sa svim poljima


    public Restoran() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNaziv() { return naziv; }

    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getTip() { return tip; }

    public void setTip(String tip) { this.tip = tip; }

    public ArrayList<Artikal> getJelovnik() { return jelovnik; }

    public void setJelovnik(ArrayList<Artikal> jelovnik) { this.jelovnik = jelovnik; }
}
