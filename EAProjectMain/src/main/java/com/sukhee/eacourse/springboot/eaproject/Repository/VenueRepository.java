package com.sukhee.eacourse.springboot.eaproject.Repository;

import com.sukhee.eacourse.springboot.eaproject.Domain.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue, Long> {
}
