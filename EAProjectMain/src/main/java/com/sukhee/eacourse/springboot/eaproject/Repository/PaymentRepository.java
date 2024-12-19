package com.sukhee.eacourse.springboot.eaproject.Repository;

import com.sukhee.eacourse.springboot.eaproject.Domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
