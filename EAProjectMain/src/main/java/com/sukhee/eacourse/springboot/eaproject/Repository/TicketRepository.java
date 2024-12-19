package com.sukhee.eacourse.springboot.eaproject.Repository;

import com.sukhee.eacourse.springboot.eaproject.Domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
