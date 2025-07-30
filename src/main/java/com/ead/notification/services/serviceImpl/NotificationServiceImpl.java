package com.ead.notification.services.serviceImpl;

import com.ead.notification.repository.NotificationRepository;
import com.ead.notification.services.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
}
