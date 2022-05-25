package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.dto.PorucenArtikalDto;
import SistemZaNarucivanjeHrane.demo.dto.PorudzbineDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PorudzbinaService {

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private ArtikalService artikalService;

    @Autowired
    private RestoranService restoranService;

    @Autowired
    private KupacService kupacService;

    @Autowired
    private PorucenArtikalService porucenArtikalService;

    public List<Porudzbina> findAll() {
        return porudzbinaRepository.findAll();
    }

    public List<Porudzbina> findAllByStatus(Status status){return porudzbinaRepository.findAllByStatus(status);}

    public void saveKupac(Kupac kupac){
        kupacService.save(kupac);
    }

    public Artikal findArtikalById(Long id){
        return artikalService.findArtikalById(id);
    }

    public void savePorucenArtikal(PorucenArtikal porucenArtikal){
        porucenArtikalService.save(porucenArtikal);
    }

    public Restoran findRestoranById(Long id){
        return restoranService.findRestoranById(id);
    }

    public boolean isArtikalURestoranu(Long id1, Long id2){
        return restoranService.isArtikalURestoranu(id1, id2);
    }

    public void save(Porudzbina porudzbina){
        porudzbinaRepository.save(porudzbina);
    }

    public void saveArtikal(Artikal artikal){
        artikalService.save(artikal);
    }

    public Restoran findRestoranByNaziv(String naziv) { return restoranService.findByNaziv(naziv); }

    public Artikal findArtikalByNaziv(String naziv) { return artikalService.findByNaziv(naziv); }

    public List<PorudzbineDto> getPorudzbineFromKupac (Kupac kupac) {
        Set<Porudzbina> porudzbineKupca = kupac.getPorudzbine();
        List<PorucenArtikalDto> poruceniArtikliDto = new ArrayList<>();
        List<PorudzbineDto> porudzbineDto = new ArrayList<>();
        for(Porudzbina p : porudzbineKupca) {
            for(PorucenArtikal a : p.getPoruceniArtikli()) {
                poruceniArtikliDto.add(new PorucenArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getKolicina()));
            }
            PorudzbineDto porudzbinaDto = new PorudzbineDto(new ArrayList<PorucenArtikalDto>(poruceniArtikliDto), p.getCena(), p.getStatus(), p.getRestoran().getNaziv());
            porudzbineDto.add(porudzbinaDto);
            poruceniArtikliDto.removeAll(poruceniArtikliDto);
        }

        return porudzbineDto;
    }

    public List<PorudzbineDto> getPorudzbineFromMenadzer(Menadzer menadzer) {
        List<Porudzbina> porudzbine = findAll();
        List<PorucenArtikalDto> poruceniArtikliDto = new ArrayList<>();
        List<PorudzbineDto> porudzbineDto = new ArrayList<>();
        for(Porudzbina p : porudzbine) {
            if(p.getRestoran().getID().equals(menadzer.getRestoran().getID())) {
                for(PorucenArtikal a : p.getPoruceniArtikli()) {
                    poruceniArtikliDto.add(new PorucenArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getKolicina()));
                }
                PorudzbineDto porudzbinaDto = new PorudzbineDto(new ArrayList<PorucenArtikalDto>(poruceniArtikliDto), p.getCena(), p.getStatus(), p.getRestoran().getNaziv());
                porudzbineDto.add(porudzbinaDto);
                poruceniArtikliDto.removeAll(poruceniArtikliDto);
            }

        }

        return porudzbineDto;
    }

    public List<PorudzbineDto> getPorudzbineFromDostavljac(Dostavljac dostavljac) {
        Set<Porudzbina> porudzbineDostavljaca = dostavljac.getPorudzbine();
        List<PorucenArtikalDto> poruceniArtikliDto = new ArrayList<>();
        List<PorudzbineDto> porudzbineDto = new ArrayList<>();
        //njegove porudzbine
        for(Porudzbina p : porudzbineDostavljaca) {
            for(PorucenArtikal a : p.getPoruceniArtikli()) {
                poruceniArtikliDto.add(new PorucenArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getKolicina()));
            }
            PorudzbineDto porudzbinaDto = new PorudzbineDto(new ArrayList<PorucenArtikalDto>(poruceniArtikliDto), p.getCena(), p.getStatus(), p.getRestoran().getNaziv());
            porudzbineDto.add(porudzbinaDto);
            poruceniArtikliDto.removeAll(poruceniArtikliDto);
        }

        //porudzbine koje cekaju
        List<Porudzbina> porudzbine = findAll();
        for(Porudzbina p : porudzbine) {
            if(p.getStatus().equals(Status.CEKA_DOSTAVLJACA)) {
                for(PorucenArtikal a : p.getPoruceniArtikli()) {
                    poruceniArtikliDto.add(new PorucenArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getKolicina()));
                }
                PorudzbineDto porudzbinaDto = new PorudzbineDto(new ArrayList<PorucenArtikalDto>(poruceniArtikliDto), p.getCena(), p.getStatus(), p.getRestoran().getNaziv());
                porudzbineDto.add(porudzbinaDto);
                poruceniArtikliDto.removeAll(poruceniArtikliDto);
            }

        }

        return porudzbineDto;
    }

    public Boolean daLiJeArtikalUKorpi(Kupac kupac, Long id) {
        Set<Porudzbina> porudzbineKupca = kupac.getPorudzbine();
        Porudzbina korpa = new Porudzbina();
        boolean k = false;
        for(Porudzbina p : porudzbineKupca) {
            if(p.getStatus().equals(Status.U_KORPI)) {
                korpa = p;
                k = true;
            }
        }

        if(!k)
            return false;

        for(PorucenArtikal a : korpa.getPoruceniArtikli()) {
            if(a.getArtikal().getID().equals(id))
                return true;
        }

        return false;

    }

    public void deleteFromKorpa(Kupac kupac, Long id) { //TODO promeni ukupnu cenu i ako je prazan obrisi korpu
        Porudzbina korpa = new Porudzbina();
        for(Porudzbina p : kupac.getPorudzbine()) {
            if(p.getStatus().equals(Status.U_KORPI)) {
                korpa = p;
                break;
            }
        }

        for(PorucenArtikal a : korpa.getPoruceniArtikli()) {
            if(a.getArtikal().getID().equals(id)) {
                korpa.getPoruceniArtikli().remove(a);
                korpa.setCena(korpa.getCena() - a.getArtikal().getCena());
                save(korpa);
                saveKupac(kupac);
                break;
            }
        }

        /*if(korpa.getPoruceniArtikli().isEmpty()) {
            kupac.getPorudzbine().remove(korpa);
            saveKupac(kupac);
        }*/
    }


}
