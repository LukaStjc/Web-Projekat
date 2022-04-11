package SistemZaNarucivanjeHrane.demo.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "porudzbina_tabela") //ovo sam videla da je ona iskucala na svom projektu, ne znam jel treba za sve
public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID ID; //sve ovo sam prepisala sa njenog projekta sto je pokazivala na vezbama

    @ManyToMany //undirektno
    @JoinTable(name = "poruceno",
            joinColumns = { @JoinColumn(name = "porudzbina_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "artikal_id", referencedColumnName = "id") }
    )
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    @ManyToOne //undirektna
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datumIVreme;

    private Long cena;

    @ManyToOne //bidirektna
    //@JoinColumn(name = "kupac_id") mislim da ovde ne treba ovo i  da ide samo mapped by "kupac" kod OneToMany u kupcu
    private Kupac kupac;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Porudzbina() {
    }

    public Porudzbina(UUID ID, Set<Artikal> poruceniArtikli, Restoran restoran, Date datumIVreme, Long cena, Kupac kupac, Status status) {
        this.ID = ID;
        this.poruceniArtikli = poruceniArtikli;
        this.restoran = restoran;
        this.datumIVreme = datumIVreme;
        this.cena = cena;
        this.kupac = kupac;
        this.status = status;
    }

    public UUID getID() { return ID; }

    public void setID(UUID ID) { this.ID = ID; }

    public Set<Artikal> getPoruceniArtikli() { return poruceniArtikli; }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) { this.poruceniArtikli = poruceniArtikli; }

    public Restoran getRestoran() { return restoran; }

    public void setRestoran(Restoran restoran) { this.restoran = restoran; }

    public Date getDatumIVreme() { return datumIVreme; }

    public void setDatumIVreme(Date datumIVreme) { this.datumIVreme = datumIVreme; }

    public Long getCena() { return cena; }

    public void setCena(Long cena) { this.cena = cena; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public Kupac getKupac() { return kupac; }

    public void setKupac(Kupac kupac) { this.kupac = kupac; }
}
