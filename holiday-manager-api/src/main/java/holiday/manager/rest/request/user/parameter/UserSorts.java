package holiday.manager.rest.request.user.parameter;

import java.util.ArrayList;
import java.util.List;

public class UserSorts {
	private List<UserSort> sortList;

	public UserSorts(List<UserSort> sortList) {
		if (sortList == null) {
			this.sortList = new ArrayList<UserSort>();
		}
		if (sortList != null) {
			this.sortList = sortList;
		}
	}

	public List<UserSort> getSortList() {
		return sortList;
	}
}
