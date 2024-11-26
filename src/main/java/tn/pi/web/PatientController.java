package tn.pi.web;

import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import tn.pi.entities.Patient;
import tn.pi.repositories.PatientRepository;

import javax.naming.Binding;
import java.util.List;

@Controller
public class PatientController {

    private final PatientRepository patientRepository; //k t3ml final par obligation t3ml constructeur

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/index")
    public String index(Model model ,
                        @RequestParam(name = "page" , defaultValue = "0") int page ,
                        @RequestParam(name = "size" , defaultValue = "4")int size ,
                        @RequestParam(name = "keyword" , defaultValue = "")String keyword
    )
    {
//        List<Patient> patients = patientRepository.findAll();
//        Page<Patient> patients = patientRepository.findAll(PageRequest.of(page, size));
        Page<Patient> patients = patientRepository.findByNomContains(keyword ,PageRequest.of(page ,size));
        model.addAttribute("patients", patients.getContent());
        model.addAttribute("pages", new int[patients.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients"; //retourne le nom de la page html sans .html
    }
    @GetMapping("/delete")
    public String delete(Long id, String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/formPatient")
    public String formPatient(Model model){
        model.addAttribute("patient" , new Patient());
        return "formPatient";
    }
    @PostMapping("/savePatient")
    public String savePatient(Model model, @Valid Patient patient , BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "formPatient";
        patientRepository.save(patient);
        model.addAttribute("patient" , new Patient());
        return "formPatient";
    }
    @GetMapping("/editPatient")
    public String editPatient(Model model,
                              @RequestParam(name = "id") Long id) {
        Patient patient = patientRepository.findById(id).get();
        model.addAttribute("patient" , patient);
        return "editPatient";
    }
}