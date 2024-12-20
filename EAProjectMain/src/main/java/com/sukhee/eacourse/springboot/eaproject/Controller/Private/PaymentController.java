package com.sukhee.eacourse.springboot.eaproject.Controller.Private;

import com.sukhee.eacourse.springboot.eaproject.Controller.DTO.PaymentDTO;
import com.sukhee.eacourse.springboot.eaproject.Domain.Participant;
import com.sukhee.eacourse.springboot.eaproject.Domain.Payment;
import com.sukhee.eacourse.springboot.eaproject.Service.PaymentService;
import com.sukhee.eacourse.springboot.eaproject.Service.UserService;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final UserService userService;

    public PaymentController(PaymentService paymentService, UserService userService) {
        this.paymentService = paymentService;
        this.userService = userService;
    }

    @PostMapping(path = "/pay-request")
    @PreAuthorize("hasRole('USER')")
    public Payment payment(@Valid @RequestBody PaymentDTO paymentDTO, @RequestHeader("Authorization") String token) {
        Participant participant = (Participant) userService.getUserByToken(token);
        return paymentService.processPayment(paymentDTO, participant);
    }

    @GetMapping(path="/{id}")
    @PreAuthorize("hasRole('USER')")
    public Payment getPayment(@PathVariable("id") Long id) {
        return paymentService.getPaymentById(id);
    }

}
