package com.canteen.CanteenManagement.repository;

import com.canteen.CanteenManagement.model.Order;
import com.canteen.CanteenManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    // Used in OrderController
    List<Order> findByUser(User user);

    // Used in RecommendationService
    List<Order> findTop10ByUser_IdOrderByOrderDateDesc(Long userId);
}
