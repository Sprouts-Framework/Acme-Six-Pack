package es.us.lsi.dp.utilities;

public class CustomInternationalization {

	private String messagePrefix;
	private Class<?> type;
	private String field;

	public CustomInternationalization(String messagePrefix, Class<?> type, String field) {
		super();
		this.messagePrefix = messagePrefix;
		this.type = type;
		this.field = field;
	}

	public String getMessagePrefix() {
		return messagePrefix;
	}

	public void setMessagePrefix(String messagePrefix) {
		this.messagePrefix = messagePrefix;
	}

	public Class<?> getType() {
		return type;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

}
