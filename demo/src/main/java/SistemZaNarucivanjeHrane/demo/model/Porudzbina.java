package SistemZaNarucivanjeHrane.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID ID; //ova anotacija sluzi tome da se generise dugacak string brojeva, koji je naravno jedinstven

    @OneToMany(fetch = FetchType.EAGER) //undirektno, mora eager jer ne radi sa lazy
    private Set<PorucenArtikal> poruceniArtikli = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER) //undirektna
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    private final LocalDateTime datumIVreme = LocalDateTime.now();

    private double cena = 0;

    @ManyToOne(fetch = FetchType.LAZY) //bidirektna, mappedBy ide u klasu Kupac, imamo JsonIgnore
    private Kupac kupac;

    @Enumerated(EnumType.STRING)
    private Status status = Status.U_KORPI;

    public Porudzbina() {
    }

    public Porudzbina(Set<PorucenArtikal> poruceniArtikli, Restoran restoran, double cena, Kupac kupac, Status status) {
        this.poruceniArtikli = poruceniArtikli;
        this.restoran = restoran;
        this.cena = cena;
        this.kupac = kupac;
        this.status = status;
    }

    public UUID getID() {
        return ID;
    }

    public Set<PorucenArtikal> getPoruceniArtikli() {
        return poruceniArtikli;
    }

    public Restoran getRestoran() {
        return restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public void setPoruceniArtikli(Set<PorucenArtikal> poruceniArtikli) {
        this.poruceniArtikli = poruceniArtikli;
    }

    public LocalDateTime getDatumIVreme() {
        return datumIVreme;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    @Override
    public String toString() {
        return "Porudzbina : " +
                "ID = " + ID +
                ", poruceniArtikli = " + poruceniArtikli +
                ", restoran = " + restoran +
                ", datumIVreme = " + datumIVreme +
                ", cena = " + cena +
                ", kupac = " + kupac +
                ", status = " + status +
                '\'';
    }
}
