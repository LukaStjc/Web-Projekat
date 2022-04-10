package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Dostavljac extends Korisnik implements Serializable {

    @OneToMany(mappedBy = "Dostavljac", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Porudzbina> porudzbine = new HashSet<>();

    public Dostavljac() {
        super();
    }


}