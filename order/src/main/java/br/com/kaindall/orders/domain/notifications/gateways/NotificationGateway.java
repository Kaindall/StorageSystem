package br.com.kaindall.orders.domain.notifications.gateways;

import br.com.kaindall.orders.domain.notifications.entities.Notification;

public interface NotificationGateway {
    void notify(Notification notification);
}
