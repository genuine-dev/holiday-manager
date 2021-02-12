package jp.co.genuine.hm.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"jp.co.genuine.hm.api", "holiday.manager"})
//@EnableSwagger2
public class HolidayManagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HolidayManagerApiApplication.class, args);
	}

}
