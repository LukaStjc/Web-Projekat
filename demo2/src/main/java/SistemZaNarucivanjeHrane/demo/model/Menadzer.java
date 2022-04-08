package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;

@Entity
public class Menadzer extends Korisnik implements Serializable {
    private long zaduzenZaRestoran;
}
