package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

public abstract class Korisnik {

    @Id
    @Column(unique = true, nullable = false)
    private String korisnickoIme;

    private String lozinka;

    private String ime;

    private String prezime;

    private TipPola tipPola;

    private LocalDate datumRodjenja;

    private TipUloge tipUloge;

    public Korisnik() {
    }

    public Korisnik(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, LocalDate datumRodjenja) {
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.tipPola = tipPola;
        this.datumRodjenja = datumRodjenja;

        // sada treba proveriti da li vec postoji korisnik sa tim imenom
        // mislim da to ide nesto sa SQL upitom
        this.korisnickoIme=korisnickoIme;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

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

    public LocalDate getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(LocalDate datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public TipUloge getTipUloge() {
        return tipUloge;
    }

    public void setTipUloge(TipUloge tipUloge) {
        this.tipUloge = tipUloge;
    }
}
