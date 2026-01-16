package com.canteen.CanteenManagement.controller;

import com.canteen.CanteenManagement.model.Notification;
import com.canteen.CanteenManagement.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    // 1. Get all unread notifications for a user
    @GetMapping("/user/{userId}/unread")
    public List<Notification> getUnreadNotifications(@PathVariable Long userId) {
        return notificationRepository.findByUserIdAndReadFalse(userId);
    }

    // 2. Get all notifications (read + unread) for a user with pagination
    @GetMapping("/user/{userId}")
    public Page<Notification> getUserNotifications(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return notificationRepository.findByUserId(userId, PageRequest.of(page, size));
    }

    // 3. Mark a notification as read
    @PatchMapping("/{notificationId}/read")
    public Notification markAsRead(@PathVariable Long notificationId) {
        Optional<Notification> optionalNotification = notificationRepository.findById(notificationId);
        Notification notification = optionalNotification.orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        return notificationRepository.save(notification);
    }
}
