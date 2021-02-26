package holiday.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import holiday.manager.batch.service.GrantHolidayService;

@Component
public class ScheduledTask {
	@Autowired
	private GrantHolidayService service;

	@Scheduled(cron = "${cron.daily}")
	public void run() {
		service.grant();
	}
}
