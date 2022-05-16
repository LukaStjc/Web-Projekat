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
    private String geografskaDuzina; //int?

    @Column
    private String geografskaSirina; //int?

    @Column
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
