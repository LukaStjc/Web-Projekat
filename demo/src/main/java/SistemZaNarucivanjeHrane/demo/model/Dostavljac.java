package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @OneToMany(fetch = FetchType.EAGER) // unidirektno
    @JoinColumn(name="porudzbina_id")
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac() {
        super();
        super.setTipUloge(TipUloge.DOSTAVLJAC);
    }

    public Dostavljac(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, LocalDate datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, tipPola, datumRodjenja);
        super.setTipUloge(TipUloge.DOSTAVLJAC);
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }


}
