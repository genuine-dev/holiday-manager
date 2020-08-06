package holiday.manager.domain.model;

import java.util.Date;

public interface DomainEvent {
	public int getVersion();

	public Date getOccurredOn();
}
