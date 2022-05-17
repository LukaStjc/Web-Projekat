package SistemZaNarucivanjeHrane.demo.configuration;

import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Configuration
public class DatabaseConfiguration {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private TipKupcaRepository tipKupcaRepository;

    @Bean
    public boolean instantiate() {
        Artikal pastrmka = new Artikal("Pastrmka sa krompir salatom", 649, TipArtikla.JELO, 8, "Pastrmka sveze pecana sa Jadranskog mora");
        artikalRepository.save(pastrmka);
        Artikal pizza = new Artikal("Pica", 150, TipArtikla.JELO, 3, "Mala capricciosa 30cm");
        artikalRepository.save(pizza);
        Artikal pasta = new Artikal("Pasta carbonare", 360, TipArtikla.JELO, 2, "Pasta carbonare pravljena kao iz Italije");
        artikalRepository.save(pasta);

        Dostavljac dostavljacSale = new Dostavljac("salecarina", "123456789", "Aleksandar", "Maric", TipPola.MUSKI, LocalDate.of(1988, 1, 8));
        dostavljacRepository.save(dostavljacSale);
        Dostavljac dostavljacMarija = new Dostavljac("marijapetrovic", "marijamarija", "Marija", "Petrovic", TipPola.ZENSKI, LocalDate.of(1998, 10, 14));
        dostavljacRepository.save(dostavljacMarija);
        Dostavljac dostavljacIvana = new Dostavljac("ivanajovanovic", "ivanavolimarka", "Ivana", "Jovanovic", TipPola.ZENSKI, LocalDate.of(1995, 12, 03));
        dostavljacRepository.save(dostavljacIvana);

        TipKupca tipKupca = new TipKupca("zlatni", 0.3, 33); //Lupila sam koji su podaci za tip kupca, to treba smisliti lepo

        Kupac kupacSara = new Kupac("sarasavic", "sarasara", "Sara" , "Savic", TipPola.ZENSKI, LocalDate.of(1999, 12 , 10), 52, tipKupca);
        kupacRepository.save(kupacSara);
        Kupac kupacJovan = new Kupac("jovanmaric", "jasamkralj", "Jovan" , "Maric", TipPola.ZENSKI, LocalDate.of(1989, 11 , 05), 28, tipKupca);
        kupacRepository.save(kupacJovan);
        Kupac kupacAnja = new Kupac("anjamilosevic", "anjamanjam", "Anja" , "Milosevic", TipPola.ZENSKI, LocalDate.of(1978, 05 , 10), 24, tipKupca);
        kupacRepository.save(kupacAnja);

        Lokacija lokacijaSrpskaSicilija = new Lokacija(60 , 22, "Veselina Maslese 54A");
        lokacijaRepository.save(lokacijaSrpskaSicilija);
        Lokacija lokacijaMorskiRaj = new Lokacija(25 , 89, "Kace Dejanovic 42");
        lokacijaRepository.save(lokacijaMorskiRaj);

        Set<Artikal> jelovnikSrpskaSicilija = new HashSet<>();
        jelovnikSrpskaSicilija.add(pizza);
        jelovnikSrpskaSicilija.add(pasta);

        Set<Artikal> jelovnikMorskiRaj = new HashSet<>();
        jelovnikMorskiRaj.add(pastrmka);

        Restoran restoranSrpskaSicilija = new Restoran("Srpska Sicilija", "italijanski", lokacijaSrpskaSicilija);
        restoranSrpskaSicilija.setJelovnik(jelovnikSrpskaSicilija);
        restoranRepository.save(restoranSrpskaSicilija);
        Restoran restoranMorskiRaj = new Restoran("Morski raj", "mediteranski", lokacijaMorskiRaj);
        restoranMorskiRaj.setJelovnik(jelovnikMorskiRaj);
        restoranRepository.save(restoranMorskiRaj);

        Menadzer menadzerAna = new Menadzer("anaparovic", "anabanana", "Ana", "Parovic", TipPola.ZENSKI, LocalDate.of(2001, 12, 13), restoranSrpskaSicilija);
        menadzerRepository.save(menadzerAna);
        Menadzer menadzerLuka = new Menadzer("lukastajic", "lukaluka", "Luka", "Stajic", TipPola.MUSKI, LocalDate.of(2001, 07, 07), restoranMorskiRaj);
        menadzerRepository.save(menadzerLuka);

        Komentar komentar = new Komentar(kupacSara,restoranMorskiRaj, "Prelepa hrana, prelepa usluga, uzivam da dolazim sa porodicom", 5);
        komentarRepository.save(komentar);

        Porudzbina porudzbina = new Porudzbina(UUID.randomUUID(), jelovnikSrpskaSicilija, restoranSrpskaSicilija, LocalDateTime.now(), 510, kupacJovan, Status.U_PRIPREMI);
        porudzbinaRepository.save(porudzbina);

        Admin admin = new Admin("anicadjukic", "jasamasistent", "Anica", "Djokic", TipPola.ZENSKI, LocalDate.of(1999,05,11));
        adminRepository.save(admin);
        return true;
    }
}
