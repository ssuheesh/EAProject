package com.sukhee.eacourse.springboot.eaproject.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Authority {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String authority;
}
