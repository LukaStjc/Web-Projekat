package SistemZaNarucivanjeHrane.demo.service;

import SistemZaNarucivanjeHrane.demo.dto.KorpaDto;
import SistemZaNarucivanjeHrane.demo.dto.PorucenArtikalDto;
import SistemZaNarucivanjeHrane.demo.dto.PorudzbinaMenadzerDto;
import SistemZaNarucivanjeHrane.demo.dto.PorudzbineDto;
import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.repository.PorudzbinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PorudzbinaService {

    @Autowired
    PorudzbinaRepository porudzbinaRepository;

    @Autowired
    ArtikalService artikalService;

    @Autowired
    RestoranService restoranService;

    @Autowired
    KupacService kupacService;

    @Autowired
    PorucenArtikalService porucenArtikalService;

    @Autowired
    DostavljacService dostavljacService;

    public List<Porudzbina> findAll() {
        return porudzbinaRepository.findAll();
    }

    public Porudzbina findById(UUID id) { return porudzbinaRepository.findById(id).get(); }

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

    public List<PorudzbinaMenadzerDto> getPorudzbineFromMenadzer(Menadzer menadzer) {

        List<Porudzbina> porudzbine = findAll();
        List<PorucenArtikalDto> poruceniArtikliDto = new ArrayList<>();
        List<PorudzbinaMenadzerDto> porudzbineDto = new ArrayList<>();
        for(Porudzbina p : porudzbine) {
            if(p.getRestoran().getID().equals(menadzer.getRestoran().getID())) {
                for(PorucenArtikal a : p.getPoruceniArtikli()) {
                    poruceniArtikliDto.add(new PorucenArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getKolicina()));
                }
                PorudzbinaMenadzerDto porudzbinaDto = new PorudzbinaMenadzerDto(p.getID(), new ArrayList<PorucenArtikalDto>(poruceniArtikliDto), p.getDatumIVreme(), p.getCena(), p.getKupac().getKorisnickoIme(), p.getStatus());
                porudzbineDto.add(porudzbinaDto);
                poruceniArtikliDto.removeAll(poruceniArtikliDto);
            }

        }

        return porudzbineDto;
    }

    public List<PorudzbinaMenadzerDto> getPorudzbineFromDostavljac(Dostavljac dostavljac) {
        Set<Porudzbina> porudzbineDostavljaca = dostavljac.getPorudzbine();
        List<PorucenArtikalDto> poruceniArtikliDto = new ArrayList<>();
        List<PorudzbinaMenadzerDto> porudzbineDto = new ArrayList<>();
        //njegove porudzbine
        for(Porudzbina p : porudzbineDostavljaca) {
            for(PorucenArtikal a : p.getPoruceniArtikli()) {
                poruceniArtikliDto.add(new PorucenArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getKolicina()));
            }
            PorudzbinaMenadzerDto porudzbinaDto = new PorudzbinaMenadzerDto(p.getID(), new ArrayList<PorucenArtikalDto>(poruceniArtikliDto), p.getDatumIVreme(), p.getCena(), p.getKupac().getKorisnickoIme(), p.getStatus());
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
                PorudzbinaMenadzerDto porudzbinaDto = new PorudzbinaMenadzerDto(p.getID(), new ArrayList<PorucenArtikalDto>(poruceniArtikliDto), p.getDatumIVreme(), p.getCena(), p.getKupac().getKorisnickoIme(), p.getStatus());
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

    public void deleteFromKorpa(Kupac kupac, Long id) { //TODO videti da li treba brisati korpu
        Porudzbina korpa = kupac.korpa();

        for(PorucenArtikal a : korpa.getPoruceniArtikli()) {
            if(a.getArtikal().getID().equals(id)) {
                if(a.getKolicina() != 1) {
                    a.setKolicina(a.getKolicina() - 1);
                    savePorucenArtikal(a);
                    korpa.setCena(korpa.getCena() - a.getArtikal().getCena());
                    save(korpa);
                    saveKupac(kupac);
                    return;
                }
                else {
                    korpa.getPoruceniArtikli().remove(a);
                    korpa.setCena(korpa.getCena() - a.getArtikal().getCena());
                    porucenArtikalService.delete(a);
                    save(korpa);
                    saveKupac(kupac);
                    return;
                }
            }
        }

        if(korpa.getPoruceniArtikli().isEmpty()) {
            kupac.getPorudzbine().remove(korpa);
            saveKupac(kupac);
        } //TODO da li brisati korpu ako je prazna, ja mislim da je bolje da obrisemo jer korpa sadrzi ime restorana a mozda se kupac predomislio i hoce drugi restoran
    }

    public KorpaDto getKorpa(Kupac kupac) {
        Porudzbina korpa = kupac.korpa();

        List<PorucenArtikalDto> poruceniArtikli = new ArrayList<>();
        for(PorucenArtikal a : korpa.getPoruceniArtikli()) {
            poruceniArtikli.add(new PorucenArtikalDto(a.getArtikal().getNaziv(), a.getArtikal().getCena(), a.getKolicina()));
        }

        KorpaDto korpaDto = new KorpaDto(poruceniArtikli, korpa.getCena());
        return korpaDto;

    }

    public String submitKorpe(Kupac kupac) {
        Porudzbina korpa = kupac.korpa();
        korpa.setStatus(Status.OBRADA);
        porudzbinaRepository.save(korpa);
        return "Uspesno ste napravili porudzbinu";
    }

    public boolean isMenadzerOfPorudzbina(Menadzer menadzer, UUID id) {
        Porudzbina porudzbina = findById(id);
        if(porudzbina.getRestoran().getID().equals(menadzer.getRestoran().getID()))
            return true;
        return false;
    }

    public boolean isDostavljacOfPorudzbina(Dostavljac dostavljac, UUID id) {
        Porudzbina porudzbina = findById(id);
        if(dostavljac.getPorudzbine().contains(porudzbina))
            return true;
        return false;
    }

    public String changeToUPripremi(UUID id) {
        Porudzbina porudzbina = findById(id);
        if(porudzbina.getStatus().equals(Status.OBRADA)) {
            porudzbina.setStatus(Status.U_PRIPREMI);
            save(porudzbina);
            return "Uspesno ste izmenili stanje korpe u U_PRIPREMI";
        }
        else
            return "Ova opcija je moguca samo za porudzbinu koja je u obradi";
    }

    public String changeToCekaDostavljaca(UUID id, String id_d) {
        Porudzbina porudzbina = findById(id);
        Dostavljac dostavljac = dostavljacService.findByKorisnickoIme(id_d);
        if(dostavljac == null)
            return "Dostavljac sa datim korisnickim imenom ne postoji";
        if(porudzbina.getStatus().equals(Status.U_PRIPREMI)) {
            porudzbina.setStatus(Status.CEKA_DOSTAVLJACA);
            save(porudzbina);
            dostavljac.dodajPorudzbinu(porudzbina);
            dostavljacService.save(dostavljac);
            return "Uspesno ste izmenili stanje korpe u CEKA_DOSTAVLJACA";
        }
        else
            return "Ova opcija je moguca samo za porudzbinu koja je u pripremi";
    }

    public String changeToUTransportu(UUID id) {
        Porudzbina porudzbina = findById(id);
        if(porudzbina.getStatus().equals(Status.U_PRIPREMI)) {
            porudzbina.setStatus(Status.U_TRANSPORTU);
            save(porudzbina);
            return "Uspesno ste izmenili stanje porudzbine u U_TRANSPORTU";
        }
        else
            return "Ova opcija je moguca samo za porudzbinu koja je u pripremi";

    }

    public String changeToDostavljena(UUID id) {
        Porudzbina porudzbina = findById(id);
        if(porudzbina.getStatus().equals(Status.U_TRANSPORTU)) {
            porudzbina.setStatus(Status.DOSTAVLJENA);
            save(porudzbina);
            Kupac kupac = porudzbina.getKupac();
            kupac.setBrojBodova((kupac.getBrojBodova() + porudzbina.getCena()/1000*133));
            saveKupac(kupac);
            //kada promeni broj bodova proveri da li treba da promeni ulogu
            return "Uspesno ste izmenili stanje korpe u DOSTAVLJENA";
        }
        else
            return "Ova opcija je moguca samo za porudzbinu koja je u transportu";

    }
}
