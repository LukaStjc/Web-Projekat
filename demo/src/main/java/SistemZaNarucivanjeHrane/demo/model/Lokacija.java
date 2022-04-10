package SistemZaNarucivanjeHrane.demo.model;

public class Lokacija {
    private String geografskaDuzina;
    private String geografskaSirina;
    private String adresa;

    public Lokacija() {
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setGeografskaDuzina(String geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public void setGeografskaSirina(String geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }
}
