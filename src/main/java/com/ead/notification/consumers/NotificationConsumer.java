package com.ead.notification.consumers;

import com.ead.notification.dtos.NotificationRecordCommandDto;
import com.ead.notification.services.NotificationService;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    final NotificationService notificationService;

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(bindings =  @QueueBinding(
            value = @Queue(value = "${ead.broker.queue.notification-command-queue.name}", durable = "true"),
            exchange = @Exchange(value = "${ead.broker.exchange.notification-command-exchange}", type = ExchangeTypes.TOPIC, ignoreDeclarationExceptions = "true"),
            key = "${ead.broker.key.notification-command-key}"
    ))
    public void listen(@Payload NotificationRecordCommandDto notificationRecordCommandDto) {
        notificationService.saveNotification(notificationRecordCommandDto);
    }
}
