package SistemZaNarucivanjeHrane.demo.dto;

public class PoruciArtikalDto {

    public String nazivArtikla;
    public String nazivRestorana;

    public PoruciArtikalDto(String nazivArtikla, String nazivRestorana) {
        this.nazivArtikla = nazivArtikla;
        this.nazivRestorana = nazivRestorana;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    public String getNazivRestorana() {
        return nazivRestorana;
    }

    public void setNazivRestorana(String nazivRestorana) {
        this.nazivRestorana = nazivRestorana;
    }
}
