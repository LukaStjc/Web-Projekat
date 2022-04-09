package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@ManyToOne
    //@
    //private Kupac kupac;

    @ManyToOne
    @JoinColumn(name = "naziv restorana") //komentar ima kolonu sa nazivima restorana
    private Restoran restoran;

    private String tekst;

    private int ocena; //1-5

    public Komentar() {
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Restoran getRestoran() { return restoran; }

    public void setRestoran(Restoran restoran) { this.restoran = restoran; }

    public String getTekst() { return tekst; }

    public void setTekst(String tekst) { this.tekst = tekst; }

    public int getOcena() { return ocena; }

    //ne sme biti nesto drugo sem 1 i 5
    public void setOcena(int ocena) { this.ocena = ocena; }
}
