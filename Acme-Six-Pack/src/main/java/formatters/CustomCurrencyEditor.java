package formatters;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;

import org.springframework.util.StringUtils;

public class CustomCurrencyEditor extends PropertyEditorSupport {

	private CustomCurrencyFormatter customCurrencyFormatter;

	public CustomCurrencyEditor(CustomCurrencyFormatter customCurrencyFormatter) {
		super();
		this.customCurrencyFormatter = customCurrencyFormatter;
	}

	/**
	 * Parse the Currency from the given text, using the specified
	 * CustomCurrencyFormatter.
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Number parsed = customCurrencyFormatter.parse(text);
		setValue(parsed);
	}

	/**
	 * Format the Currency as String, using the specified
	 * CustomCurrencyFormatter.
	 */
	@Override
	public String getAsText() {
		Number value = (Number) getValue();
		return (value != null ? customCurrencyFormatter.print(value) : "");

	}

}
