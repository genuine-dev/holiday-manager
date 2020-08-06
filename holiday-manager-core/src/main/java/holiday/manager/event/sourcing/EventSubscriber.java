package holiday.manager.event.sourcing;

public interface EventSubscriber<T> {
	public void handleEvent(T event);

	public Class<T> subscribeToEventType();
}
