package com.canteen.CanteenManagement.service;

import com.canteen.CanteenManagement.model.Notification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Test
    public void testCreateNotification() {
        Notification notification = notificationService.createNotification(1L, "Test message", false, null);
        assertNotNull(notification);
        assertEquals("Test message", notification.getMessage());
    }
}
