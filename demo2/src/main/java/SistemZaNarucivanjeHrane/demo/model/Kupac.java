package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {

    @OneToMany(mappedBy = "kupac", fetch = FetchType.LAZY)  // bidirektna
    private Set<Porudzbina> porudzbine = new HashSet<>();

    private int brojBodova;

    private TipKupca tipKupca;

    public Kupac() {
        super();
        super.setTipUloge(TipUloge.KUPAC);
    }

    public Kupac(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, LocalDate datumRodjenja, int brojBodova, TipKupca tipKupca) {
        super(korisnickoIme, lozinka, ime, prezime, tipPola, datumRodjenja);
        this.brojBodova = brojBodova;
        this.tipKupca = tipKupca;
        super.setTipUloge(TipUloge.KUPAC);
    }

    public Set<Porudzbina> getPorudzbine() {
        return porudzbine;
    }

    public int getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(int brojBodova) {
        this.brojBodova = brojBodova;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }
}
