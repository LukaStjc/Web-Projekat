package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Id;
import java.util.Date;

public abstract class Korisnik {

    @Id
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private TipPola tipPola;
    private Date datumRodjenja;
    private TipUloge tipUloge;


}
