package ru.kaiko.rehospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kaiko.rehospital.domain.Bid;
import ru.kaiko.rehospital.domain.Disease;
import ru.kaiko.rehospital.repo.BidRepo;
import ru.kaiko.rehospital.repo.DoctorRepo;
import ru.kaiko.rehospital.repo.PatientRepo;
import ru.kaiko.rehospital.repo.RecordRepo;

@Controller
@RequestMapping("/reference")
@PreAuthorize(value = "hasAuthority('REFERENCE')")
public class ReferenceController {

    @Autowired
    private BidRepo bidRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private RecordRepo recordRepo;

    @Autowired
    private PatientRepo patientRepo;

    @GetMapping("cabinet")
    public String cabinet() {
        return "reference/cabinet";
    }

    @GetMapping("bids")
    public String getAllBids(Model model) {
        model.addAttribute("bids", bidRepo.findAll());
        return "reference/bids";
    }

    @GetMapping("/bid/{id}/delete")
    public String deleteBidById(@PathVariable("id") Long id) {
        bidRepo.deleteById(id);
        return "redirect:/reference/bids";
    }

    @GetMapping("/bid/{id}")
    public String createAppointment(@PathVariable("id") String id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("bid", bidRepo.findById(Long.valueOf(id)).get());
        model.addAttribute("doctors", null);
        return "/reference/appointment";
    }

    @GetMapping("/meet/{id}")
    public String createNewAppointment(@PathVariable("id") String id, Model model) {
        var patient = patientRepo.findById(Long.valueOf(id)).get();
        var bid = new Bid();
        bid.setPatient(patient).setPhone(patient.getPhone()).setName(patient.getFirstName());
        var bidSave = bidRepo.save(bid);

        model.addAttribute("id", bidSave.getId());
        model.addAttribute("bid", bid);
        model.addAttribute("doctors", null);
        return "/reference/appointment";
    }




    //    form for Appointment
    @PostMapping("recordbydoc")
    public String createAppointment(@RequestParam("id") String id, @RequestParam("ill") String ill, Model model) {
        var doctors = doctorRepo.findAllByDiseases(Disease.valueOf(ill));
        model.addAttribute("doctors", doctors);
        model.addAttribute("id", id);
        model.addAttribute("bid", bidRepo.findById(Long.valueOf(id)).get());
        return "/reference/appointment";
    }

    @GetMapping("/recordme/{id}")
    public String recordMe(@PathVariable("id") Long id, @RequestParam("bid") String bidId) {
        var recordToSave = recordRepo.findById(id).get();
        var patient = bidRepo.findById(Long.valueOf(bidId)).get().getPatient();
        recordToSave.setPatient(patient);

        recordRepo.save(recordToSave);
        bidRepo.deleteById(Long.valueOf(bidId));
        return "redirect:/reference/bids";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("patients", null);
        return "reference/users";
    }

    @PostMapping("/finduser")
    public String findUser(@RequestParam("firstname") String firstName,
                           @RequestParam("lastname") String lastName, Model model
    ) {

        model.addAttribute("patients", patientRepo.
                findAllByFirstNameAndLastName(firstName, lastName));
        return "reference/users";
    }

    @GetMapping("/finduser/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {

        var user = patientRepo.findById(id).get();
        var records = recordRepo.findAllByPatient(user);
        model.addAttribute("records", records);

        model.addAttribute("patient", user);
        return "reference/user";
    }

    @GetMapping("/cancel/{id}")
    public String cancelAppointment(@PathVariable("id") Long id,
                                    @RequestParam("patient") String patientId, Model model) {


        var record = recordRepo.findById(id).get();
        var patient = patientRepo.findById(Long.valueOf(patientId)).get();
        patient.getRecords().remove(record);
        patientRepo.save(patient);
        record.setPatient(null);
        recordRepo.save(record);

        return "redirect:/reference/finduser/" + patientId;
    }

}
