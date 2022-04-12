package SistemZaNarucivanjeHrane.demo.model;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID ID; //ova anotacija sluzi tome da se generise veliki string brojeva, koji je naravno jedinstven

    @ManyToMany(fetch = FetchType.LAZY) //undirektno
    @JoinTable(name = "poruceno",
            joinColumns = { @JoinColumn(name = "porudzbina_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "artikal_id", referencedColumnName = "id") }
    )
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY) //undirektna
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datumIVreme;

    private long cena;

    @ManyToOne(fetch = FetchType.LAZY) //bidirektna, mappedBy ide u klasu Kupac
    private Kupac kupac;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Porudzbina() {
    }

    public Porudzbina(UUID ID, Set<Artikal> poruceniArtikli, Restoran restoran, Date datumIVreme, long cena, Kupac kupac, Status status) {
        this.ID = ID;
        this.poruceniArtikli = poruceniArtikli;
        this.restoran = restoran;
        this.datumIVreme = datumIVreme;
        this.cena = cena;
        this.kupac = kupac;
        this.status = status;
    }

    public UUID getID() { return ID; }

    public Set<Artikal> getPoruceniArtikli() { return poruceniArtikli; }

    public Restoran getRestoran() { return restoran; }

    public void setRestoran(Restoran restoran) { this.restoran = restoran; }

    public Date getDatumIVreme() { return datumIVreme; }

    public void setDatumIVreme(Date datumIVreme) { this.datumIVreme = datumIVreme; }

    public long getCena() { return cena; }

    public void setCena(long cena) { this.cena = cena; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public Kupac getKupac() { return kupac; }

    public void setKupac(Kupac kupac) { this.kupac = kupac; }
}
