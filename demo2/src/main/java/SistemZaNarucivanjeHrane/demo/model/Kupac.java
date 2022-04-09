package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Kupac extends Korisnik implements Serializable {
    @OneToMany(mappedBy = "Kupac", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    private int brojBodova;
    //TODO kako se radi ovaj tip kupca; da li je klasa ili enum koji ne bih znao uraditi

    // tip je struct sa enum poljem i int

    public Kupac() {
        super();
    }
}
