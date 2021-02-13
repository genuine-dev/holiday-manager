package jp.co.genuine.hm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"jp.co.genuine.hm.api", "holiday.manager"})
@EnableJpaRepositories(basePackages = {"holiday.manager.port.adapter.persistence.repository"})
@EntityScan(basePackages = {"holiday.manager.port.adapter.persistence.repository"})
public class HolidayManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayManagerApiApplication.class, args);
	}

}
