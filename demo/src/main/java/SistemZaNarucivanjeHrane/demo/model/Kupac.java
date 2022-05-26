package SistemZaNarucivanjeHrane.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {

    @OneToMany(mappedBy = "kupac", fetch = FetchType.EAGER) // mora eager
    @JsonIgnore// bidirektna
    private Set<Porudzbina> porudzbine = new HashSet<>();

    private double brojBodova;

    //TODO @JoinColumn(name = "tipKupca_rang") u tabeli treba nekako dodati da pise rang jer ne moze da pise klasa
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
        return this.porudzbine;
    }

    public double getBrojBodova() {
        return brojBodova;
    }

    public void setBrojBodova(double brojBodova) {
        this.brojBodova = brojBodova;
    }

    public TipKupca getTipKupca() {
        return tipKupca;
    }

    public void setTipKupca(TipKupca tipKupca) {
        this.tipKupca = tipKupca;
    }

    public void dodajPorudzbinu(Porudzbina porudzbina) {
        this.porudzbine.add(porudzbina);
    }

    public Porudzbina korpa() {
        for(Porudzbina p : porudzbine) {
            if (p.getStatus().equals(Status.U_KORPI))
                return p;
        }
        return null;
    }

    /*@Override
    public String toString() {
        String ret = new String("");
        ret+="Porudzbine:";
        for(Porudzbina p:porudzbine){

        }
        return ret;
    }*/
}
