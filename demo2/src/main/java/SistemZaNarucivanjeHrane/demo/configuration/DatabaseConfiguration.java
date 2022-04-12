package SistemZaNarucivanjeHrane.demo.configuration;

import SistemZaNarucivanjeHrane.demo.model.*;
import SistemZaNarucivanjeHrane.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;




@Configuration
public class DatabaseConfiguration {

    @Autowired
    private ArtikalRepository artikalRepository;

    @Autowired
    private LokacijaRepository lokacijaRepository;

    @Autowired
    private PorudzbinaRepository porudzbinaRepository;

    @Autowired
    private RestoranRepository restoranRepository;

    @Autowired
    private DostavljacRepository dostavljacRepository;

    @Autowired
    private KupacRepository kupacRepository;

    @Autowired
    private MenadzerRepository menadzerRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Bean
    public boolean instantiate(){
        
        Artikal a1 = new Artikal("Jabuke", 352, TipArtikla.JELO, 1.03, "Sveze i slatke");

        artikalRepository.save(a1);

        TipKupca novi=new TipKupca("srebrni", 2.3, 10);
        Kupac k1 = new Kupac("peraperic", "4jasam 2genije 0", "Pera", "Peric", TipPola.MUSKI, java.time.LocalDate.now(), 12, novi);

        kupacRepository.save(k1);
        /*
        Department department2 = new Department("second department");

        department1.setCompany(company);
        department2.setCompany(company);
        departmentRepository.saveAll(
                List.of(department1, department2)
        );

        Employee pera = new Employee(
                "Pera", "Peric", "Rukovodilac", department1
        );
        Employee mika = new Employee(
                "Mika", "Mikic", "Menadzer", department1
        );
        Employee zika = new Employee(
                "Zika", "Zikic", "Radnik", department2
        );

        employeeRepository.saveAll(
                List.of(pera, mika, zika)
        );

        Project project1 = new Project(
                "Projekat 1", new Date(125, Calendar.JULY, 4)
        );

        Project project2 = new Project(
                "Projekat 2", new Date(129, Calendar.DECEMBER, 3)
        );

        projectRepository.saveAll(
                List.of(project1, project2)
        );

        mika.getProjects().add(project1);
        mika.getProjects().add(project2);

        zika.getProjects().add(project2);

        employeeRepository.save(mika);
        employeeRepository.save(zika);
        */
        return true;
    }
}