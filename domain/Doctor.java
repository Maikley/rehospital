package ru.kaiko.rehospital.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Doctor extends User {

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Set<Record> records = new HashSet<>();

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Disease.class, fetch = FetchType.EAGER)
    @CollectionTable(name="diseases")
    private Set<Disease> diseases = new HashSet<>();
}