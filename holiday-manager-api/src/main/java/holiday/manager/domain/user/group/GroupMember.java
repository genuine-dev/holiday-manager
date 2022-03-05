package holiday.manager.domain.user.group;

public class GroupMember {
	private Integer userId;
	private String userName;
	private boolean isManager;
	private boolean isMember;

	public GroupMember(Integer userId, String userName, boolean isManager, boolean isMember) {
		this.userId = userId;
		this.userName = userName;
		this.isManager = isManager;
		this.isMember = isMember;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}
	public boolean isMember() {
		return isMember;
	}
	public void setMember(boolean isMember) {
		this.isMember = isMember;
	}
}
