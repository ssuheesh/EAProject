package com.sukhee.eacourse.springboot.eaproject.Repository.Specification;

import com.sukhee.eacourse.springboot.eaproject.Domain.Event;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;

public class EventSpecifications {
    public static org.springframework.data.jpa.domain.Specification<Event> hasTitle(String title) {
        return (root, query, cb) ->
                title != null && !title.isEmpty()
                        ? cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%")
                        : cb.conjunction();
    }

    public static org.springframework.data.jpa.domain.Specification<Event> hasEventDateAfter(LocalDate startDate) {
        return (root, query, cb) ->
                startDate != null
                        ? cb.greaterThanOrEqualTo(root.get("eventDate"), startDate)
                        : cb.conjunction();
    }

    public static org.springframework.data.jpa.domain.Specification<Event> hasEventDateBefore(LocalDate endDate) {
        return (root, query, cb) ->
                endDate != null
                        ? cb.lessThanOrEqualTo(root.get("eventDate"), endDate)
                        : cb.conjunction();
    }

    public static org.springframework.data.jpa.domain.Specification<Event> hasPriceGreaterThanOrEqualTo(Double minPrice) {
        return (root, query, cb) ->
                minPrice != null
                        ? cb.greaterThanOrEqualTo(root.get("price"), minPrice)
                        : cb.conjunction();
    }

    public static org.springframework.data.jpa.domain.Specification<Event> hasPriceLessThanOrEqualTo(Double maxPrice) {
        return (root, query, cb) ->
                maxPrice != null
                        ? cb.lessThanOrEqualTo(root.get("price"), maxPrice)
                        : cb.conjunction();
    }

    public static org.springframework.data.jpa.domain.Specification<Event> hasParticipant(Long participantId) {
        return (root, query, cb) -> {
            if (participantId == null) {
                return cb.conjunction();
            }
            query.distinct(true); // Ensure unique results when joining
            return cb.equal(root.join("participants").get("id"), participantId);
        };
    }

    public static org.springframework.data.jpa.domain.Specification<Event> hasOrganizer(Long organizerId) {
        return (root, query, cb) ->
                organizerId != null
                        ? cb.equal(root.get("organizer").get("id"), organizerId)
                        : cb.conjunction();
    }

    public static org.springframework.data.jpa.domain.Specification<Event> isInVenue(Long venueId) {
        return (root, query, cb) ->
                venueId != null
                        ? cb.equal(root.get("venue").get("id"), venueId)
                        : cb.conjunction();
    }
}
