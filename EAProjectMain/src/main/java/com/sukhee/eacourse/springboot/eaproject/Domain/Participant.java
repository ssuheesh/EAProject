package com.sukhee.eacourse.springboot.eaproject.Domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
@DiscriminatorValue("Participant")
public class Participant extends User {
    private String firstName;
    private String lastName;
}
