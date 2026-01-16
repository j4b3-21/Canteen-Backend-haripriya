package com.canteen.CanteenManagement.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="order_id", unique=true)
    @JsonBackReference  // Prevent recursive JSON serialization
    private Order order;

    @Column(nullable=false)
    private BigDecimal amount;

    private String method; // UPI/CARD/CASH
    private String status; // SUCCESS/FAILED/PENDING

    private LocalDateTime paidAt = LocalDateTime.now();
}
