package com.sukhee.eacourse.springboot.eaproject.Repository;

import com.sukhee.eacourse.springboot.eaproject.Domain.Event;
import com.sukhee.eacourse.springboot.eaproject.Domain.Payment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT e FROM Event e WHERE e.id = :id")
    Event findByIdWithLock(@Param("id") Long id);

//
//    List<Event> findEventsByFilters(String title, LocalDate startDate, LocalDate endDate, Double minPrice, Double maxPrice) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Event> cq = cb.createQuery(Event.class);
//        Root<Event> event = cq.from(Event.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (title != null && !title.isEmpty()) {
//            predicates.add(cb.like(cb.lower(event.get("title")), "%" + title.toLowerCase() + "%"));
//        }
//        if (startDate != null) {
//            predicates.add(cb.greaterThanOrEqualTo(event.get("eventDate"), startDate));
//        }
//        if (endDate != null) {
//            predicates.add(cb.lessThanOrEqualTo(event.get("eventDate"), endDate));
//        }
//        if (minPrice != null) {
//            predicates.add(cb.greaterThanOrEqualTo(event.get("price"), minPrice));
//        }
//        if (maxPrice != null) {
//            predicates.add(cb.lessThanOrEqualTo(event.get("price"), maxPrice));
//        }
//
//        cq.where(cb.and(predicates.toArray(new Predicate[0])));
//        return entityManager.createQuery(cq).getResultList();
//    }

}
