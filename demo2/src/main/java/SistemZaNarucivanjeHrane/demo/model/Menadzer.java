package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Menadzer extends Korisnik implements Serializable {

    @OneToOne   // unidirektna
    @JoinColumn(name = "restoran_id")
    private Restoran restoran;

    Menadzer() {
        super();
        super.setTipUloge(TipUloge.MENADZER);
    }

    public Menadzer(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, Date datumRodjenja, Restoran restoran) {
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
