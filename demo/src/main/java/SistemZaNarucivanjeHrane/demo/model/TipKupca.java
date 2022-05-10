package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class TipKupca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String rang;    // u specifikaciji zadatka pise ime, ja sam to nazvao rang

    private double popust;  /* implementiracu ovo tako da popust bude izmedju 0 i 100 i da to predstavlja %
                               ali takodje moze biti Long pa da se samo oduzima od cene*/
    private int trazeniBrojBodova;

    public TipKupca(){}

    public TipKupca(String rang, double popust, int trazeniBrojBodova) {
        this.rang = rang;
        this.popust = popust;
        this.trazeniBrojBodova = trazeniBrojBodova;
    }

    public String getRang() {
        return rang;
    }

    public void setRang(String rang) {
        this.rang = rang;
    }

    public double getPopust() {
        return popust;
    }

    public void setPopust(double popust) {
        this.popust = popust;
    }

    public int getTrazeniBrojBodova() {
        return trazeniBrojBodova;
    }

    public void setTrazeniBrojBodova(int trazeniBrojBodova) {
        this.trazeniBrojBodova = trazeniBrojBodova;
    }

    @Override
    public String toString() {
        return "TipKupca : " +
                "ID = " + ID +
                ", rang = " + rang + '\'' +
                ", popust = " + popust +
                ", trazeniBrojBodova = " + trazeniBrojBodova +
                '\'';
    }
}
