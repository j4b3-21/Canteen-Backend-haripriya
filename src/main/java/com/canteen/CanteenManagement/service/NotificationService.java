package com.canteen.CanteenManagement.service;

import com.canteen.CanteenManagement.model.Notification;
import com.canteen.CanteenManagement.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendNotification(Long userId, String message) {
        Notification notification = new Notification(userId, message, false, LocalDateTime.now());
        notificationRepository.save(notification);
    }

    // Add this method
    public Notification createNotification(Long userId, String message, boolean read, LocalDateTime timestamp) {
        Notification notification = new Notification(userId, message, read, timestamp == null ? LocalDateTime.now() : timestamp);
        return notificationRepository.save(notification);
    }
}
