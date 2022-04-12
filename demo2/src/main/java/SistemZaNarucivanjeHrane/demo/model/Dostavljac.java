package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @OneToMany(fetch = FetchType.LAZY) // unidirektno
    @JoinColumn(name="porudzbina_id")
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac() {
        super();
        super.setTipUloge(TipUloge.DOSTAVLJAC);
    }

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, Date datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, tipPola, datumRodjenja);
        super.setTipUloge(TipUloge.DOSTAVLJAC);
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }
}
