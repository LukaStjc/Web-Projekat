package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Lokacija implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private double geografskaDuzina;
    private double geografskaSirina;
    private String adresa;

    public Lokacija() {
    }

    public Lokacija(double geografskaDuzina, double geografskaSirina, String adresa) {
        this.geografskaDuzina = geografskaDuzina;
        this.geografskaSirina = geografskaSirina;
        this.adresa = adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setGeografskaDuzina(double geografskaDuzina) {
        this.geografskaDuzina = geografskaDuzina;
    }

    public void setGeografskaSirina(double geografskaSirina) {
        this.geografskaSirina = geografskaSirina;
    }

    public double getGeografskaDuzina() {
        return geografskaDuzina;
    }

    public double getGeografskaSirina() {
        return geografskaSirina;
    }

    public String getAdresa() {
        return adresa;
    }
}
