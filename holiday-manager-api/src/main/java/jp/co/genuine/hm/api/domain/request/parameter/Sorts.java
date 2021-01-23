package jp.co.genuine.hm.api.domain.request.parameter;

import java.util.ArrayList;
import java.util.List;

public class Sorts {
	private List<Sort> sortList;

	public Sorts(List<Sort> sortList) {
		if (sortList == null) {
			this.sortList = new ArrayList<Sort>();
		}
		if (sortList != null) {
			this.sortList = sortList;
		}
	}

	public List<Sort> getSortList() {
		return sortList;
	}
}
