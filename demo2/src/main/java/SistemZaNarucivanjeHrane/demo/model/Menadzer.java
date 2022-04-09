package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Menadzer extends Korisnik implements Serializable {
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private long zaduzenZaRestoran;

    Menadzer() {
        super();
    }

    public void setZaduzenZaRestoran(long r) {
        this.zaduzenZaRestoran = r;
    }

    public long getZaduzenZaRestoran() {
        return zaduzenZaRestoran;
    }
}
