package ru.kaiko.rehospital;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.kaiko.rehospital.domain.*;
import ru.kaiko.rehospital.repo.*;

import java.util.Date;
import java.util.Set;

@SpringBootApplication
public class RehospitalApplication {


    public static void main(String[] args) {
        SpringApplication.run(RehospitalApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner preload(DoctorRepo doctorRepo, RecordRepo recordRepo,
//                                     PatientRepo patientRepo, ReferenceRepo referenceRepo,
//                                     ReportRepo reportRepo, PasswordEncoder passwordEncoder) {
//        return args -> {
//            Doctor doctor1 = new Doctor();
//            doctor1.setFirstName("Gregory");
//            doctor1.setLastName("Haus");
//            doctor1.setDiseases(Set.of(Disease.GLAUCOMA, Disease.MONONUCLEOSIS,
//                    Disease.ASTHMA, Disease.BOTULISM,
//                    Disease.CANCER, Disease.STOMACHACHE,
//                    Disease.HEADACHE));
//            doctor1.setActive(true);
//            doctor1.setCreateOn(new Date());
//            doctor1.setEmail("haus@gmail.com");
//            doctor1.setPhone("8-800-000-00-01");
//            doctor1.setUsername("haus");
//            doctor1.setPassword(passwordEncoder.encode("user"));
//            doctor1.setRoles(Set.of(Role.DOCTOR));
//
//            Doctor doctor2 = new Doctor();
//            doctor2.setFirstName("James");
//            doctor2.setLastName("Wilson");
//            doctor2.setDiseases(Set.of(Disease.CANCER));
//            doctor2.setActive(true);
//            doctor2.setCreateOn(new Date());
//            doctor2.setEmail("james@gmail.com");
//            doctor2.setPhone("8-800-000-00-02");
//            doctor2.setUsername("james");
//            doctor2.setPassword(passwordEncoder.encode("user"));
//            doctor2.setRoles(Set.of(Role.DOCTOR));
//
//            Doctor doctor3 = new Doctor();
//            doctor3.setFirstName("Eric");
//            doctor3.setLastName("Forman");
//            doctor3.setDiseases(Set.of(Disease.STOMACHACHE, Disease.HEADACHE));
//            doctor3.setActive(true);
//            doctor3.setCreateOn(new Date());
//            doctor3.setEmail("eric@gmail.com");
//            doctor3.setPhone("8-800-000-00-03");
//            doctor3.setUsername("eric");
//            doctor3.setPassword(passwordEncoder.encode("user"));
//            doctor3.setRoles(Set.of(Role.DOCTOR));
//
//            doctorRepo.save(doctor1);
//            doctorRepo.save(doctor2);
//            doctorRepo.save(doctor3);
//
//            var patient = new Patient();
//            patient.setFirstName("Cate");
//            patient.setLastName("Milton");
//
//            patient.setActive(true);
//            patient.setCreateOn(new Date());
//            patient.setEmail("user@gmail.com");
//            patient.setPhone("8-800-000-00-00");
//            patient.setUsername("user");
//            patient.setPassword(passwordEncoder.encode("user"));
//            patient.setRoles(Set.of(Role.USER));
//            patientRepo.save(patient);
//
//            Report report1 = new Report().setCreateOn(new Date()).setUpdateOn(new Date());
//            Report report2 = new Report().setCreateOn(new Date()).setUpdateOn(new Date());
//            Report report3 = new Report().setCreateOn(new Date()).setUpdateOn(new Date());
//            Report report4 = new Report().setCreateOn(new Date()).setUpdateOn(new Date());
//            Report report5 = new Report().setCreateOn(new Date()).setUpdateOn(new Date());
//            Report report6 = new Report().setCreateOn(new Date()).setUpdateOn(new Date());
//            Report report7 = new Report().setCreateOn(new Date()).setUpdateOn(new Date());
//            Report report8 = new Report().setCreateOn(new Date()).setUpdateOn(new Date());
//
//            reportRepo.save(report1);
//            reportRepo.save(report2);
//            reportRepo.save(report3);
//            reportRepo.save(report4);
//            reportRepo.save(report5);
//            reportRepo.save(report6);
//            reportRepo.save(report7);
//            reportRepo.save(report8);
//
//            //            doctor 1 - take cabinet #1 - all day
//            Record record1 = new Record();
//            record1.setCabinet(1L);
//            record1.setDate(new Date());
//            record1.setDoctor(doctor1);
//            record1.setHour("9");
//            record1.setReport(report1);
//
//
//            Record record2 = new Record();
//            record2.setCabinet(1L);
//            record2.setDate(new Date());
//            record2.setDoctor(doctor1);
//            record2.setHour("10");
//            record2.setReport(report2);
//
//            Record record3 = new Record();
//            record3.setCabinet(1L);
//            record3.setDate(new Date());
//            record3.setDoctor(doctor1);
//            record3.setHour("11");
//            record3.setReport(report3);
//
//
//            //            doctor 2 - take cabinet #2 - 9th and 10th
//            Record record4 = new Record();
//            record4.setCabinet(2L);
//            record4.setDate(new Date());
//            record4.setDoctor(doctor2);
//            record4.setHour("9");
//            record4.setReport(report4);
//
//            Record record5 = new Record();
//            record5.setCabinet(2L);
//            record5.setDate(new Date());
//            record5.setDoctor(doctor2);
//            record5.setHour("10");
//            record5.setReport(report5);
//
//            //            doctor 3 - take cabinet #2 - hours 11th
//            Record record6 = new Record();
//            record6.setCabinet(2L);
//            record6.setDate(new Date());
//            record6.setDoctor(doctor3);
//            record6.setHour("11");
//            record6.setReport(report6);
//
//            //            doctor 3 - take cabinet #3 - hours 9th and 10th
//            Record record7 = new Record();
//            record7.setCabinet(3L);
//            record7.setDate(new Date());
//            record7.setDoctor(doctor3);
//            record7.setHour("9");
//            record7.setReport(report7);
//
//            Record record8 = new Record();
//            record8.setCabinet(3L);
//            record8.setDate(new Date());
//            record8.setDoctor(doctor3);
//            record8.setHour("10");
//            record8.setReport(report8);
//
//            recordRepo.save(record1);
//            recordRepo.save(record2);
//            recordRepo.save(record3);
//            recordRepo.save(record4);
//            recordRepo.save(record5);
//            recordRepo.save(record6);
//            recordRepo.save(record7);
//            recordRepo.save(record8);
//
//            var reference = new Reference();
//            reference.setUsername("ref");
//            reference.setPassword(passwordEncoder.encode("user"));
//            reference.setFirstName("lisa");
//            reference.setLastName("cuddy");
//            reference.setEmail("list@mail.com");
//            reference.setPhone("8-900-000-00-00");
//            reference.setActive(true);
//            reference.setCreateOn(new Date());
//            reference.setRoles(Set.of(Role.REFERENCE));
//            referenceRepo.save(reference);
//        };
//    }
}
