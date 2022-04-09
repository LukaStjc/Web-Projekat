package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public abstract class Korisnik {

    @Id
    private String korisnickoIme;
    private String lozinka;

    @Column
    private String ime;

    @Column
    private String prezime;

    private TipPola tipPola;
    private Date datumRodjenja;
    private TipUloge tipUloge;

    Korisnik(){}

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }
    //TODO kada se kreira korisnicko ime, potrebno je proci kroz sve do sada kreirane korisnike i proveriti da li vec postoji neko sa tim imenom

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public TipPola getTipPola() {
        return tipPola;
    }

    public void setTipPola(TipPola tipPola) {
        this.tipPola = tipPola;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public TipUloge getTipUloge() {
        return tipUloge;
    }

    public void setTipUloge(TipUloge tipUloge) {
        this.tipUloge = tipUloge;
    }
}
