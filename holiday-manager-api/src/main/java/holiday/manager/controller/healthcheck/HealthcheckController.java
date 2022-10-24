package holiday.manager.controller.healthcheck;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {

	@GetMapping("healthcheck")
	public String healthcheck() {
		return "OK";
	}
}
