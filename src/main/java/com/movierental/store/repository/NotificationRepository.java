package com.movierental.store.repository;

import com.movierental.store.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    // Find all messages for a specific user
    List<Notification> findByUserId(Long userId);
}