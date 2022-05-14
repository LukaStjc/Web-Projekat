package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class Admin extends Korisnik{
    public Admin() {
        super();
        super.setTipUloge(TipUloge.ADMIN);
    }

    public Admin(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, LocalDate datumRodjenja) {
        super(korisnickoIme, lozinka, ime, prezime, tipPola, datumRodjenja);
        super.setTipUloge(TipUloge.ADMIN);
    }


}
