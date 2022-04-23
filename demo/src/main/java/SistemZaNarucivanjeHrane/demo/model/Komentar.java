package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Komentar implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //undirektna
    @JoinColumn(name = "kupac_id")
    private Kupac kupac;

    @ManyToOne(fetch = FetchType.LAZY) //undirektna
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    private String tekst;

    private int ocena; //1-5

    public Komentar() {
    }

    public Komentar(Kupac kupac, Restoran restoran, String tekst, int ocena) {
        this.kupac = kupac;
        this.restoran = restoran;
        this.tekst = tekst;
        this.ocena = ocena;
    }

    public Long getId() { return id; }

    public Restoran getRestoran() { return restoran; }

    public void setRestoran(Restoran restoran) { this.restoran = restoran; }

    public String getTekst() { return tekst; }

    public void setTekst(String tekst) { this.tekst = tekst; }

    public int getOcena() { return ocena; }

    //provera da li je izmedju 1 i 5
    public void setOcena(int ocena) {  if(ocena > 0 && ocena < 6) {this.ocena = ocena;} else this.ocena = 0;}

    public Kupac getKupac() { return kupac; }

    public void setKupac(Kupac kupac) { this.kupac = kupac; }
}
