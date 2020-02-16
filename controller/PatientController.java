package ru.kaiko.rehospital.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kaiko.rehospital.domain.Disease;
import ru.kaiko.rehospital.domain.Patient;
import ru.kaiko.rehospital.domain.User;
import ru.kaiko.rehospital.repo.DoctorRepo;
import ru.kaiko.rehospital.repo.RecordRepo;

@Controller
@RequestMapping("/patient")
@PreAuthorize(value = "hasAuthority('USER')")
public class PatientController {

    @Autowired
    private DoctorRepo doctorRepo;

    @Autowired
    private RecordRepo recordRepo;

//    cabinet page
    @GetMapping("cabinet")
    public String cabinet() {
        return "patient/cabinet";
    }

//    action - форма для запроса - поиск по проблеме(болезни)
    @GetMapping("/record")
    public String formRecord() {
        return "patient/record";
    }

//    action - страничка с подходящими докторами
    @PostMapping("/recordbydoc")
    public String findRecord(@RequestParam("ill") String ill, Model model) {
        var doctors = doctorRepo.findAllByDiseases(Disease.valueOf(ill));
        model.addAttribute("doctors", doctors);
        return "patient/record2";
    }

//    action - запись на прием
    @GetMapping("/recordme/{id}")
    public String recordMe(@AuthenticationPrincipal User user, @PathVariable("id") Long id) {
        var recordToSave = recordRepo.findById(id).get();
        recordToSave.setPatient((Patient) user);
        recordRepo.save(recordToSave);
        return "redirect:/patient/cabinet";
    }

//    action - страничка с подходящим расписанием
//    @PostMapping("/schedule")
//    public String findSchedule(@RequestParam("ill") String ill, Model model) {
//        var records = recordRepo.findAllByDiseases(Disease.valueOf(ill));
//        model.addAttribute("records", records);
//        return "schedule";
//    }

    @GetMapping("report")
    public String getAllReports(@AuthenticationPrincipal User user, Model model) {
        var records = recordRepo.findAllByPatient((Patient) user);
        model.addAttribute("records", records);
        return "patient/report";
    }
}
