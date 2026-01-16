package com.canteen.CanteenManagement.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "menu_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Column(nullable = false)
    private BigDecimal price;
    private String category;
    private Boolean available;
    private Integer preparationTime;
    private String imageUrl;
    private Boolean vegetarian;
}