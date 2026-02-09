package com.movierental.store.service;

import com.movierental.store.model.Notification;
import com.movierental.store.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void send(Long userId, String message) {
        Notification note = new Notification();
        note.setUserId(userId);
        note.setMessage(message);
        note.setCreatedAt(LocalDateTime.now());
        notificationRepository.save(note);
    }

    public List<Notification> getUserNotifications(Long userId) {
        return notificationRepository.findByUserId(userId);
    }
}