package com.sukhee.eacourse.springboot.eaproject.Domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue("Organizer")
public class Organizer extends User {
    private String companyName;
}

