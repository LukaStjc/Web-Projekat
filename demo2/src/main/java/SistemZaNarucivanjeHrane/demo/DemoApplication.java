package SistemZaNarucivanjeHrane.demo;

import SistemZaNarucivanjeHrane.demo.model.Dostavljac;
import SistemZaNarucivanjeHrane.demo.repository.DostavljacRepository;
import SistemZaNarucivanjeHrane.demo.repository.TipKupcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import SistemZaNarucivanjeHrane.demo.model.TipPola;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Override
    public void run(String... args){
    }


    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
