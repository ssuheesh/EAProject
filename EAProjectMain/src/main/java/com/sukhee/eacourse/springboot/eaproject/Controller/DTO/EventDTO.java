package com.sukhee.eacourse.springboot.eaproject.Controller.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter
public class EventDTO {
    private String title;
    private LocalDate eventDate;
}