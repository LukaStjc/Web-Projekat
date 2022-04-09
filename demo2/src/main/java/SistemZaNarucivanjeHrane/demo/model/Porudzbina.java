package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Porudzbina implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int UUID;

    //vise porudzbina, moze da ima vise artikala
    @OneToMany(mappedBy = "poruceni_artikli", cascade = CascadeType.ALL)
    private Set<Artikal> poruceniArtikli = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "naziv_restorana") //porudzbina ima kolonu naziv restorana
    private Restoran restoran;

    private DateTimeFormatter datumIVreme; //koristimo LocalDateTime.now()

    private Long cena;

    //@ManytoOne vise porudzbina na jednog kupca
    //@JoinColumn(name = "korisnickoIme")
    //private Kupac kupac;

    private Status status;

    //TODO kupac metoda
    //TODO geteri seteri
    //TODO konst sa parametrima
    //TODO veze

    public Porudzbina() {
    }

    public int getUUID() { return UUID; }

    public void setUUID(int UUID) { this.UUID = UUID; }

    public Set<Artikal> getPoruceniArtikli() { return poruceniArtikli; }

    public void setPoruceniArtikli(Set<Artikal> poruceniArtikli) { this.poruceniArtikli = poruceniArtikli; }

    public Restoran getRestoran() { return restoran; }

    public void setRestoran(Restoran restoran) { this.restoran = restoran; }

    public DateTimeFormatter getDatumIVreme() { return datumIVreme; }

    //da li treba set date and time, da li to uopste moze da se menja
    //public void setDatumIVreme() { this.datumIVreme = new LocalDateTime(); }

    public Long getCena() { return cena; }

    public void setCena(Long cena) { this.cena = cena; }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
}
