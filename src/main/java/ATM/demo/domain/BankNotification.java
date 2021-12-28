package ATM.demo.domain;

import lombok.Data;

@Data
public class BankNotification implements Notification {

    public void sendNotification(String destination, String message) {
        System.out.println("Bank Notification Destination: " + destination + "\n" + message);
    }
}
