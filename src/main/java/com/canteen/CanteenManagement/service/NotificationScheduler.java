package com.canteen.CanteenManagement.service;

import com.canteen.CanteenManagement.model.User;
import com.canteen.CanteenManagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificationScheduler {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    // Runs every day at 3 PM
//    @Scheduled(cron = "0 0 15 * * ?")
//    public void sendIceCreamNotifications() {
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            if (user.getFavoriteItems() != null && user.getFavoriteItems().contains("Ice Cream")) {
//                notificationService.sendNotification(user.getId(), "It's time to eat ice cream!");
//            }
//        }
//    }
}
