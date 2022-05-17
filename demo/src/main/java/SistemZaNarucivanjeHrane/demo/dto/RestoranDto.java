package SistemZaNarucivanjeHrane.demo.dto;

public class RestoranDto {

    private String naziv;

    private String tip;

    private double geografskaDuzina;

    private double geografskaSirina;

    private String adresa;

    private String menadzer;

    public RestoranDto(String naziv, String tip, double geografskaDuzina, double geografskaSirina, String adresa, String menazder) {
        this.naziv = naziv;
        this.tip = tip;
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;
        this.adresa = adresa;
        this.menadzer = menazder;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getGeografskaSirina() {
        return geografskaSirina;
    }

    public void setGeografskaSirina(double geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public double getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public void setGeografskaDuzina(double geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMenadzer() {
        return menadzer;
    }

    public void setMenadzer(String menadzer) {
        this.menadzer = menadzer;
    }
}
