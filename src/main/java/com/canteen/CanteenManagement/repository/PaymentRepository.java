package com.canteen.CanteenManagement.repository;

import com.canteen.CanteenManagement.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {}
