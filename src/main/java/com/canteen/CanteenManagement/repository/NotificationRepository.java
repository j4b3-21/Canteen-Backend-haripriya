package com.canteen.CanteenManagement.repository;

import com.canteen.CanteenManagement.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Already present
    List<Notification> findByUserIdAndReadFalse(Long userId);

    // Add this for pagination support
    Page<Notification> findByUserId(Long userId, Pageable pageable);

    // If you want list instead of Page (but Spring Data prefers Page):
    // List<Notification> findByUserId(Long userId, Pageable pageable);
}
