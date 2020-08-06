package holiday.manager.domain.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AggregateRoot {
	private static final String MUTATOR_METHOD_NAME = "on";

	private static Map<String, Method> mutatorMethods = new HashMap<>();

	private List<DomainEvent> mutatingEvents;
	private int unmutatedVersion;

	public int mutatedVersion() {
		return this.unmutatedVersion() + 1;
	}

	public List<DomainEvent> mutatingEvents() {
		return this.mutatingEvents;
	}

	public int unmutatedVersion() {
		return this.unmutatedVersion;
	}

	protected AggregateRoot(
            List<DomainEvent> eventStream,
            int streamVersion) {

        this();

        for (DomainEvent event : eventStream) {
            this.mutateWhen(event);
        }

        this.setUnmutatedVersion(streamVersion);
    }

	protected AggregateRoot() {
        super();

        this.setMutatingEvents(new ArrayList<DomainEvent>(2));
    }

	protected void apply(DomainEvent domainEvent) {

		this.mutatingEvents().add(domainEvent);

		this.mutateWhen(domainEvent);
	}

	protected void mutateWhen(DomainEvent domainEvent) {

		Class<? extends AggregateRoot> rootType = this.getClass();

		Class<? extends DomainEvent> eventType = domainEvent.getClass();

		String key = rootType.getName() + ":" + eventType.getName();

		Method mutatorMethod = mutatorMethods.get(key);

		if (mutatorMethod == null) {
			mutatorMethod = this.cacheMutatorMethodFor(key, rootType, eventType);
		}

		try {
			mutatorMethod.invoke(this, domainEvent);

		} catch (InvocationTargetException e) {
			if (e.getCause() != null) {
				throw new RuntimeException(
						"Method "
								+ MUTATOR_METHOD_NAME
								+ "("
								+ eventType.getSimpleName()
								+ ") failed. See cause: "
								+ e.getMessage(),
						e.getCause());
			}

			throw new RuntimeException(
					"Method "
							+ MUTATOR_METHOD_NAME
							+ "("
							+ eventType.getSimpleName()
							+ ") failed. See cause: "
							+ e.getMessage(),
					e);

		} catch (IllegalAccessException e) {
			throw new RuntimeException(
					"Method "
							+ MUTATOR_METHOD_NAME
							+ "("
							+ eventType.getSimpleName()
							+ ") failed because of illegal access. See cause: "
							+ e.getMessage(),
					e);
		}
	}

	private Method cacheMutatorMethodFor(
			String key,
			Class<? extends AggregateRoot> rootType,
			Class<? extends DomainEvent> eventType) {

		synchronized (mutatorMethods) {
			try {
				Method method = this.hiddenOrPublicMethod(rootType, eventType);

				method.setAccessible(true);

				mutatorMethods.put(key, method);

				return method;

			} catch (Exception e) {
				throw new IllegalArgumentException(
						"I do not understand "
								+ MUTATOR_METHOD_NAME
								+ "("
								+ eventType.getSimpleName()
								+ ") because: "
								+ e.getClass().getSimpleName() + ">>>" + e.getMessage(),
						e);
			}
		}
	}

	private Method hiddenOrPublicMethod(
			Class<? extends AggregateRoot> rootType,
			Class<? extends DomainEvent> eventType)
			throws Exception {

		Method method = null;

		try {
			method = rootType.getDeclaredMethod(
					MUTATOR_METHOD_NAME,
					eventType);

		} catch (Exception e) {
			method = rootType.getMethod(
					MUTATOR_METHOD_NAME,
					eventType);
		}

		return method;
	}

	private void setMutatingEvents(List<DomainEvent> mutatingEventsList) {
		this.mutatingEvents = mutatingEventsList;
	}

	private void setUnmutatedVersion(int streamVersion) {
		this.unmutatedVersion = streamVersion;
	}
}
