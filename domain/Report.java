package ru.kaiko.rehospital.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@Accessors(chain = true)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String diagnosis;
    private String description;
    private String prescription;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @OneToOne(mappedBy = "report")
    private Record record;
}