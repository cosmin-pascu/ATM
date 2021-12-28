package ATM.demo.factory;

import ATM.demo.domain.EmailNotification;
import ATM.demo.domain.Notification;
import ATM.demo.domain.SmsNotification;
import ATM.demo.domain.enumeration.NotificationType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class NotificationFactory {

    public Notification getNotification(NotificationType notificationType) {
        if (notificationType.equals(NotificationType.EMAIL)) {
            return new EmailNotification();
        }

        if (notificationType.equals(NotificationType.SMS)) {
            return new SmsNotification();
        }

        return null;
    }
}
