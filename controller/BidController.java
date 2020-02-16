package ru.kaiko.rehospital.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.kaiko.rehospital.domain.Bid;
import ru.kaiko.rehospital.domain.Complain;
import ru.kaiko.rehospital.domain.Patient;
import ru.kaiko.rehospital.domain.User;
import ru.kaiko.rehospital.repo.BidRepo;
import ru.kaiko.rehospital.repo.ComplainRepo;
import ru.kaiko.rehospital.repo.PatientRepo;

@Slf4j
@RestController
@RequestMapping("/api")
public class BidController {

    @Autowired
    private BidRepo bidRepo;

    @GetMapping(produces = "text/html")
    public String get(@RequestParam("name") String name,
                      @RequestParam("phone") String phone) {

        var bid = new Bid().setName(name).setPhone(phone);
        bidRepo.save(bid);
        return "Received request - GET";
    }

    @PostMapping(path = "/bid", produces = "text/html")
    public String createBid(@AuthenticationPrincipal User user, @RequestBody Bid bid) {
        if (bid != null) {
            if (user != null && user instanceof Patient) {
                bid.setPatient((Patient) user);
                bidRepo.save(bid);
            } else bidRepo.save(bid);
        }

        return "Received request - POST";
    }

    @GetMapping(path = "/bids", produces = "application/json")
    public Iterable<Bid> getAllBids() {
        return bidRepo.findAll();
    }

}