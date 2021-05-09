package jp.co.genuine.hm.model.user;

public class Password {
	private String value;
	private boolean empty;

	public Password() {
	}

	public Password(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
}
