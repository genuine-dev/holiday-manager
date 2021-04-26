package holiday.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import holiday.manager.batch.service.AlertService;

@Component
public class ScheduledTask {
	@Autowired
	private AlertService alertService;

	@Scheduled(cron = "${cron.daily}")
	public void run() {
		alertService.alert();
	}
}
