package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "korisnickoImeKupca")
    private Kupac kupac;

    @ManyToOne
    @JoinColumn(name = "nazivRestorana")
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
    public void setOcena(int ocena) {  if(ocena > 0 && ocena < 6) {this.ocena = ocena;} else this.ocena = 0;}

    public Kupac getKupac() { return kupac; }

    public void setKupac(Kupac kupac) { this.kupac = kupac; }
}
