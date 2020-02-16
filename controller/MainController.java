package ru.kaiko.rehospital.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kaiko.rehospital.domain.*;
import ru.kaiko.rehospital.repo.ComplainRepo;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private ComplainRepo complainRepo;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //    redirect to right place: doctor cabinet or patient cabinet
    @GetMapping("/direct")
    public String direct(@AuthenticationPrincipal User user) {
        if (user instanceof Patient) return "redirect:/patient/cabinet";
        if (user instanceof Doctor) return "redirect:/doctor/cabinet";
        if (user instanceof Reference) return "redirect:/reference/cabinet";
        return "redirect:/";
    }

    @GetMapping("/registration")
    public String getFormRegistration() {
        return "";
    }

    @PostMapping("/registration")
    public String createUser() {
        return "";
    }

    @GetMapping("/complain")
    public String complains(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("complains", complainRepo.findAllByOrderByIdDesc());
        model.addAttribute("me", user != null ? user.getUsername() : "");
        return "complain";
    }

    @ResponseBody
    @PreAuthorize(value = "hasAuthority('USER') or hasAuthority('REFERENCE')")
    @PostMapping(value = "complain", produces = "application/json")
    public Complain leftComplain(@AuthenticationPrincipal User user,
                                 @RequestBody Complain complain) {

        System.out.println(complain);
        complain.setUser(user);
        complain.setName(user.getFirstName());
        complainRepo.save(complain);
        return complain;
    }


    @PreAuthorize(value = "hasAuthority('USER') or hasAuthority('REFERENCE')")
    @GetMapping("complain/{id}/delete")
    public String deleteComplain(@AuthenticationPrincipal User user,
                                 @PathVariable Long id) {

        complainRepo.deleteById(id);
        return "redirect:/complain";
    }

    @ResponseBody
    @PreAuthorize(value = "hasAuthority('USER') or hasAuthority('REFERENCE')")
    @PostMapping(value = "complain/{id}/edit", produces = "text/html")
    public String editComplain(@AuthenticationPrincipal User user,
                                 @PathVariable Long id,
                               @RequestBody Complain complain) {

        var complainDB = complainRepo.findById(id).get();
        complainDB.setMessage(complain.getMessage());
        complainRepo.save(complainDB);
        return "ok";
    }






}
