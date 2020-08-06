package holiday.manager.domain.model.user;

import java.util.Collections;
import java.util.List;

public class User {
	private UserId id;

	private List<User> managedMembers;

	public User(UserId id, List<User> managedMembers) {
		this.id = id;
		this.managedMembers = managedMembers;
	}

	public UserId getId() {
		return id;
	}

	public List<User> getManagedMembers() {
		return Collections.unmodifiableList(managedMembers);
	}

}
