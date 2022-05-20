package SistemZaNarucivanjeHrane.demo.dto;

import SistemZaNarucivanjeHrane.demo.model.Artikal;
import SistemZaNarucivanjeHrane.demo.model.Komentar;
import SistemZaNarucivanjeHrane.demo.model.Lokacija;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RestoranDetaljno {
    private String naziv;
    private String tip;
    private boolean radi;
    private Lokacija lokacija;
    private double ocena;
    private List<KomentarDto> komentari = new ArrayList<>();
    private Set<Artikal> jelovnik = new HashSet<>();

    public RestoranDetaljno(String naziv, String tip, boolean radi, Lokacija lokacija, List<KomentarDto> komentari, Set<Artikal> jelovnik, double ocena) {
        this.naziv = naziv;
        this.tip = tip;
        this.radi = radi;
        this.lokacija = lokacija;
        this.jelovnik = jelovnik;
        this.komentari = komentari;
        this.ocena = ocena;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public boolean isRadi() {
        return radi;
    }

    public void setRadi(boolean radi) {
        this.radi = radi;
    }

    public Lokacija getLokacija() {
        return lokacija;
    }

    public void setLokacija(Lokacija lokacija) {
        this.lokacija = lokacija;
    }

    public double getOcena() {
        return ocena;
    }

    public List<KomentarDto> getKomentari() {
        return komentari;
    }

    public Set<Artikal> getJelovnik() {
        return jelovnik;
    }
}
