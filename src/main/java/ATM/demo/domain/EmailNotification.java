package ATM.demo.domain;

import lombok.Data;

@Data
public class EmailNotification implements Notification {

    public void sendNotification(String destination, String message) {
        System.out.println("Email Destination: " + destination + "\n" + message);
    }
}
