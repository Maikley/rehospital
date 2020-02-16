package ru.kaiko.rehospital.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;

@Entity
@Data
@Accessors(chain = true)
public class Reference extends User {
}
