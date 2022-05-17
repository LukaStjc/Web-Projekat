package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Lokacija implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column
    private double geografskaDuzina;

    @Column
    private double geografskaSirina;

    @Column
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
