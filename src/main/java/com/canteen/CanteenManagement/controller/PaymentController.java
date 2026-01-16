package com.canteen.CanteenManagement.controller;

import com.canteen.CanteenManagement.model.Payment;
import com.canteen.CanteenManagement.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Payment> create(@RequestBody Payment p) {
        Payment savedPayment = paymentRepository.save(p);
        return ResponseEntity.ok(savedPayment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Payment>> all() {
        List<Payment> payments = paymentRepository.findAll();
        return ResponseEntity.ok(payments);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return paymentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
