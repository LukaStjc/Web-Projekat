package SistemZaNarucivanjeHrane.demo.dto;

public class RestoranIzlazniDto {

    private Long id;

    private String naziv;

    private String tip;

    private String adresa;

    public RestoranIzlazniDto(Long id, String naziv, String tip, String adresa) {
        this.id = id;
        this.naziv = naziv;
        this.tip = tip;
        this.adresa = adresa;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getTip() {
        return tip;
    }

    public String getAdresa() {
        return adresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Restoran {" +
                "naziv ='" + naziv + '\'' +
                ", tip ='" + tip + '\'' +
                ", adresa ='" + adresa + '\'' +
                '}';
    }
}
