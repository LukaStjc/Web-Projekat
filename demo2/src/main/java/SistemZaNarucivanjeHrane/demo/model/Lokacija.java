package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Lokacija {
    private String geografskaDuzina;
    private String geografskaSirina;
    private String adresa;

    public Lokacija() {
    }

    public Lokacija(String geografskaDuzina, String geografskaSirina, String adresa) {
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;
        this.adresa = adresa;
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

    public String getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public String getGeografskaSirina() {
        return geografskaSirina;
    }

    public String getAdresa() {
        return adresa;
    }
}
