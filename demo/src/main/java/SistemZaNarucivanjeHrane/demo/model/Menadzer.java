package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    @OneToOne(fetch = FetchType.EAGER)   // unidirektna
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    Menadzer() {
        super();
        super.setTipUloge(TipUloge.MENADZER);
    }

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, LocalDate datumRodjenja, Restoran restoran) {
        super(korisnickoIme, lozinka, ime, prezime, tipPola, datumRodjenja);
        super.setTipUloge(TipUloge.MENADZER);
        this.restoran = restoran;
    }

    public void setRestoran(Restoran restoran) {
        this.restoran = restoran;
    }

    public Restoran getRestoran() {
        return restoran;
    }
}
