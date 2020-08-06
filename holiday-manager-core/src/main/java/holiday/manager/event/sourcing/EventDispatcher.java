package holiday.manager.event.sourcing;

public interface EventDispatcher {
	public void dispatch(DispatchableDomainEvent event);
}
