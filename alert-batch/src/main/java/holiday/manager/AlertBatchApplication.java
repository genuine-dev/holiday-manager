package holiday.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlertBatchApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlertBatchApplication.class, args);
	}
}
