package com.sukhee.eacourse.springboot.eaproject.Repository;

import com.sukhee.eacourse.springboot.eaproject.Domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {

}
