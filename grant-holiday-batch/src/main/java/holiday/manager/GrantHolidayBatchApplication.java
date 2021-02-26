package holiday.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GrantHolidayBatchApplication {


	public static void main(String[] args) {
		SpringApplication.run(GrantHolidayBatchApplication.class, args);
	}

}
