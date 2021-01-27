package holiday.manager.application.holiday.holiday;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Date expirationDate1 = Date.from(ZonedDateTime.now().plusYears(2).toInstant());
		Date expirationDate2 = Date.from(ZonedDateTime.now().plusDays(2).toInstant());
		Date expirationDate3 = Date.from(ZonedDateTime.now().plusYears(1).toInstant());

		List<Date> list = new ArrayList<>();
		list.add(expirationDate1);
		list.add(expirationDate2);
		list.add(expirationDate3);

		list.stream()
				.sorted((h1,h2) -> h1.compareTo(h2))
				.forEach(date -> System.out.println(date));
	}
}
