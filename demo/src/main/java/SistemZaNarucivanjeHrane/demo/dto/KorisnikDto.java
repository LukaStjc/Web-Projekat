package SistemZaNarucivanjeHrane.demo.dto;

import SistemZaNarucivanjeHrane.demo.model.TipPola;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.LocalDate;

public class KorisnikDto {

    @Id
    @Column(unique = true, nullable = false)
    private String korisnickoIme;

    private String lozinka;

    private String ime;

    private String prezime;

    private TipPola tipPola;

    private LocalDate datumRodjenja;


    public KorisnikDto(String korisnickoIme, String lozinka, String ime, String prezime, TipPola tipPola, LocalDate datumRodjenja) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.tipPola = tipPola;
        this.datumRodjenja = datumRodjenja;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
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
}
