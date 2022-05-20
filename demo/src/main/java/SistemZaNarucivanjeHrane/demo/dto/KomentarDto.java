package SistemZaNarucivanjeHrane.demo.dto;

public class KomentarDto {
    private String tekst;
    private int ocena;

    public KomentarDto(String tekst, int ocena) {
        this.tekst = tekst;
        this.ocena = ocena;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
