package com.canteen.CanteenManagement.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequest {

    @NotNull(message = "menuId cannot be null")
    private Long menuId;

    @NotNull(message = "quantity cannot be null")
    @Min(value = 1, message = "quantity must be at least 1")
    private Integer quantity;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
