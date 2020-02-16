package ru.kaiko.rehospital.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kaiko.rehospital.domain.Doctor;
import ru.kaiko.rehospital.domain.Report;
import ru.kaiko.rehospital.repo.RecordRepo;
import ru.kaiko.rehospital.repo.ReportRepo;

import java.util.Date;

@Controller
@RequestMapping("/doctor")
@PreAuthorize(value = "hasAuthority('DOCTOR')")
public class DoctorController {

    @Autowired
    private RecordRepo recordRepo;

    @Autowired
    private ReportRepo reportRepo;

    @GetMapping("cabinet")
    public String cabinet() {
        return "doc/cabinet";
    }

    @GetMapping("/schedule")
    public String findDoctorById(@AuthenticationPrincipal Doctor doctor, Model model) {
        var records = recordRepo.findAllByDoctor(doctor);
        model.addAttribute("records", records);
        return "doc/schedule";
    }

    @GetMapping("/report/{id}")
    public String getReportForm(@PathVariable("id") Long id, Model model) {
        var report = reportRepo.findById(id).get();
        model.addAttribute("report", report);
        return "doc/report";
    }

    @PostMapping("/report/{id}")
    public String createReport(@PathVariable("id") Long id, Report report) {
        var reportToSave = reportRepo.findById(id).get();
        if (report.getDescription() != null) reportToSave.setDescription(report.getDescription());
        if (report.getDiagnosis() != null) reportToSave.setDiagnosis(report.getDiagnosis());
        if (report.getPrescription() != null) reportToSave.setPrescription(report.getPrescription());
        reportToSave.setUpdateOn(new Date());
        reportRepo.save(reportToSave);
        return "redirect:/doctor/schedule";
    }
}