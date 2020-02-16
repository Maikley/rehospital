package ru.kaiko.rehospital.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class Patient extends User {

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private Set<Record> records = new HashSet<>();
}
