package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Porudzbina implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer UUID;

    @OneToMany(mappedBy = "poruceni_artikli", cascade = CascadeType.ALL)
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "naziv_restorana")
    private Restoran restoran;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datumIVreme;

    private Long cena;

    @ManyToOne
    @JoinColumn(name = "korisnickoImeKupca")
    private Kupac kupac;

    @Enumerated(EnumType.STRING)
    private Status status;

    //TODO geteri seteri

    public Porudzbina() {
    }

    public Integer getUUID() { return UUID; }

    public void setUUID(Integer UUID) { this.UUID = UUID; }

    public Set<Artikal> getPoruceniArtikli() { return poruceniArtikli; }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) { this.poruceniArtikli = poruceniArtikli; }

    public Restoran getRestoran() { return restoran; }

    public void setRestoran(Restoran restoran) { this.restoran = restoran; }

    public Date getDatumIVreme() { return datumIVreme; }

    //da li treba set date and time, da li to uopste moze da se menja
    public void setDatumIVreme(Date datumIVreme) { this.datumIVreme = datumIVreme; }

    public Long getCena() { return cena; }

    public void setCena(Long cena) { this.cena = cena; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }

    public Kupac getKupac() { return kupac; }

    public void setKupac(Kupac kupac) { this.kupac = kupac; }
}
