package tn.pi.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Setter @Getter @NoArgsConstructor
@AllArgsConstructor @ToString @Builder

public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "patient_generator")
//    @SequenceGenerator
//            (name = "patient_generator" , sequenceName = "tpatient_seq" , initialValue = 10005 )
    private Long id ;
    @Size(min = 4, max = 50)
    private    String nom ;
    private LocalDate dateNaissance ;
    private boolean malade ;
    @Min(100)
    private int Score ;


}
