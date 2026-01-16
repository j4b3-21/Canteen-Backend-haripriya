package com.canteen.CanteenManagement.controller;

import com.canteen.CanteenManagement.dto.CreateOrderRequest;
import com.canteen.CanteenManagement.dto.OrderItemRequest;
import com.canteen.CanteenManagement.model.*;
import com.canteen.CanteenManagement.repository.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderController(OrderRepository orderRepository,
                           UserRepository userRepository,
                           MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.menuItemRepository = menuItemRepository;
    }

    // 🔐 Use hasAuthority (SAFE & GUARANTEED)
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @PostMapping(value="/orders")
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        System.out.println("Inside create order--------------");
        // ✅ Get logged-in user email from SecurityContext
        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        // ✅ Fetch user from DB
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Create order
        Order order = new Order();
        order.setUser(user);
        order.setStatus("PLACED");

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        // ✅ Create order items
        for (OrderItemRequest itemReq : request.getItems()) {

            if (itemReq.getMenuId() == null) {
                throw new RuntimeException("menuId cannot be null");
            }

            MenuItem menuItem = menuItemRepository.findById(itemReq.getMenuId())
                    .orElseThrow(() ->
                            new RuntimeException("Menu item not found: " + itemReq.getMenuId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setMenuItem(menuItem);
            orderItem.setQuantity(itemReq.getQuantity());

            orderItems.add(orderItem);

            total = total.add(
                    menuItem.getPrice()
                            .multiply(BigDecimal.valueOf(itemReq.getQuantity()))
            );
        }

        order.setItems(orderItems);
        order.setTotalPrice(total);

        // ✅ Save order (cascade will save items)
        return orderRepository.save(order);
    }
}