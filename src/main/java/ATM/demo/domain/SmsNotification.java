package ATM.demo.domain;

import lombok.Data;

@Data
public class SmsNotification implements Notification {

    public void sendNotification(String destination, String message) {
        System.out.println("SMS destination: " + destination + "\n" + message);
    }
}
