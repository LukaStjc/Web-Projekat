package SistemZaNarucivanjeHrane.demo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Porudzbina implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private int UUID;
    private ArrayList<Artikal> poruceniArtikli;
    private Restoran restoran;
    private Date datum;
}
