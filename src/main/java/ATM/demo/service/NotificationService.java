package ATM.demo.service;

import ATM.demo.config.NotificationAlertConfiguration;
import ATM.demo.domain.Notification;
import ATM.demo.domain.enumeration.BanknoteType;
import ATM.demo.domain.enumeration.Currency;
import ATM.demo.domain.enumeration.NotificationType;
import ATM.demo.factory.NotificationFactory;
import ATM.demo.repository.BanknoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationService {

    private final BanknoteRepository banknoteRepository;

    private final NotificationFactory notificationFactory;

    private final NotificationAlertConfiguration notificationAlertConfiguration;

    public void sendNotificationsIfNecessarily(Currency currency) {
        checkFiftyBanknotes(currency);

        checkOneHundredBanknotes(currency);
    }

    public void checkFiftyBanknotes(Currency currency) {
        banknoteRepository.findByCurrencyAndType(currency, BanknoteType.ONE_HUNDRED).ifPresent(banknote -> {
            if (banknote.getQuantity() < 45) {
                Notification notification = notificationFactory.getNotification(NotificationType.EMAIL);
                notification.sendNotification(notificationAlertConfiguration.getEmailAlertDestination(),
                        "Atentie, au ramas mai putin de 45 bancnote de 100");
            }

            if (banknote.getQuantity() < 40) {
                Notification notification = notificationFactory.getNotification(NotificationType.SMS);
                notification.sendNotification(notificationAlertConfiguration.getSmsAlertDestination(),
                        "Atentie, au ramas mai putin de 40 bancnote de 100");
            }
        });
    }

    public void checkOneHundredBanknotes(Currency currency) {
        banknoteRepository.findByCurrencyAndType(currency, BanknoteType.FIFTY).ifPresent(banknote -> {
            if (banknote.getQuantity() < 45) {
                Notification notification = notificationFactory.getNotification(NotificationType.EMAIL);
                notification.sendNotification(notificationAlertConfiguration.getEmailAlertDestination(),
                        "Atentie, au ramas mai putin de 45 bancnote de 50!");
            }
        });
    }
}
