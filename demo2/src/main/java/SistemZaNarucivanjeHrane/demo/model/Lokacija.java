package SistemZaNarucivanjeHrane.demo.model;

import java.io.Serializable;

public class Lokacija {
    private String geografskaDuzina;
    private String getGeografskaSirina;
    private String adresa;

    public Lokacija() {
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setGeografskaDuzina(String geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public void setGetGeografskaSirina(String getGeografskaSirina) {
        this.getGeografskaSirina = getGeografskaSirina;
    }
}
