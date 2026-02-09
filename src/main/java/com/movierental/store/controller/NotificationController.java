package com.movierental.store.controller;

import com.movierental.store.model.Notification;
import com.movierental.store.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{userId}")
    public List<Notification> getMyNotes(@PathVariable Long userId) {
        return notificationService.getUserNotifications(userId);
    }
}