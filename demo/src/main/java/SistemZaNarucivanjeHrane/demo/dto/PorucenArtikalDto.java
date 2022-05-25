package SistemZaNarucivanjeHrane.demo.dto;

public class PorucenArtikalDto {

    private String naziv;
    private double cena;
    private int kolicina;

    public PorucenArtikalDto(String naziv, double cena, int kolicina) {
        this.naziv = naziv;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        return "PorucenArtikalDto {" +
                "naziv ='" + naziv + '\'' +
                ", cena =" + cena +
                ", kolicina =" + kolicina +
                '}';
    }
}
