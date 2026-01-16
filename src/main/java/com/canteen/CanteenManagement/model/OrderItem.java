package com.canteen.CanteenManagement.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // menu item MUST exist in DB
    @ManyToOne(optional = false)
    @JoinColumn(name = "menu_id", nullable = false)
    private MenuItem menuItem;

    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    private int quantity;
}
