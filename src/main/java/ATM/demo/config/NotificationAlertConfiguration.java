package ATM.demo.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "notification-alert")
@NoArgsConstructor
public class NotificationAlertConfiguration {

    private String emailAlertDestination;
    private String smsAlertDestination;
}
