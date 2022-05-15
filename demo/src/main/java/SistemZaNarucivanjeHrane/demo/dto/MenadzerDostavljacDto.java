package SistemZaNarucivanjeHrane.demo.dto;

public class MenadzerDostavljacDto {

    private String korisnickoIme;

    private String lozinka;

    private String ime;

    private String prezime;

    private String tipPola;

    private String datumRodjenja;

    private String uloga;

    public MenadzerDostavljacDto(String korisnickoIme, String lozinka, String ime, String prezime, String tipPola, String datumRodjenja, String uloga) {
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.tipPola = tipPola;
        this.datumRodjenja = datumRodjenja;
        this.uloga = uloga;
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

    public String getTipPola() {
        return tipPola;
    }

    public void setTipPola(String tipPola) {
        this.tipPola = tipPola;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}
