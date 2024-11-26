package tn.pi;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tn.pi.entities.Patient;
import tn.pi.repositories.PatientRepository;

import java.time.LocalDate;

@SpringBootApplication
//@RequiredArgsConstructor //3eme methode
public class HospitalWebAppApplication {

    //1ere methode par attribut
    //@Autowired
    //private final PatientRepository patientRepository; //2eme methode par constructeur

//    public HospitalWebAppApplication(PatientRepository patientRepository) {
//        this.patientRepository = patientRepository;
//    }

    public static void main(String[] args) {
        SpringApplication.run(HospitalWebAppApplication.class, args);
    }

    //creation d'une methode init
    //@Bean //pour lire cette methode au debut de l'excecution
    public CommandLineRunner init(PatientRepository patientRepository) { //
        return args -> {
            /**
             * Begin transaction
             */
            //Using empty constructor
            Patient patient1 = new Patient();
            patient1.setNom("Mohamed");
            patient1.setDateNaissance(LocalDate.now());
            patient1.setMalade(true);
            patient1.setScore(1234);

            patientRepository.save(patient1);

            //Using parametric constructor
            Patient patient2 = new Patient(null,"Aicha",LocalDate.now(),false , 2136);

            patientRepository.save(patient2);

            //Using Lombock
            Patient patient3= Patient.builder()
                    .nom("Emna")
                    .dateNaissance(LocalDate.of(2001 , 10 , 24))
                    .Score(6897)
                    .malade(true)
                    .build();

            patientRepository.save(patient3);
            /**
             * End transaction
             */
        };
    }

}
