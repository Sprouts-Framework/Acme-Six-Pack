package formatters;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.format.number.NumberFormatter;

public class CustomCurrencyFormatter extends NumberFormatter {

	private String format;
	private DecimalFormatSymbols decimalFormatSymbols;

	public CustomCurrencyFormatter(String decimalMark, String groupingSeparator, String format) {
		super();

		this.decimalFormatSymbols = DecimalFormatSymbols.getInstance();
		this.decimalFormatSymbols.setDecimalSeparator(decimalMark.charAt(0));
		this.decimalFormatSymbols.setGroupingSeparator(groupingSeparator.charAt(0));
		this.format = format;
	}

	// public void setCurrencyName(String currencyName) {
	// this.currencyName = currencyName;
	// }

	@Override
	public DecimalFormat getNumberFormat(Locale locale) {
		return new DecimalFormat(format, decimalFormatSymbols);
	}

	public Number parse(String text) {
		Number result = null;
		try {
			result = super.parse(text, LocaleContextHolder.getLocale());
		} catch (Throwable e) {
			new RuntimeException(e);
		}
		return result;
	}

	public String print(Number number) {
		String result = null;
		try {
			result = super.print(number, LocaleContextHolder.getLocale());
		} catch (Throwable e) {
			new RuntimeException(e);
		}
		return result;
	}

	// public String getFormatFromLocale(Locale locale) {
	// String format = "";
	// String language = locale.getLanguage();
	//
	// switch (language) {
	// case "en":
	// format = currencyName + "###,###.##";
	// break;
	// case "es":
	// format = "###,###.## " + currencyName;
	// break;
	// }
	//
	// return format;
	// }

	/*
	 * private static final boolean roundingModeOnDecimalFormat =
	 * ClassUtils.hasMethod(DecimalFormat.class, "setRoundingMode",
	 * RoundingMode.class); private int fractionDigits = 2; private RoundingMode
	 * roundingMode; private Currency currency; private String currencyName;
	 * public String getCurrencyName() { return currencyName; } public void
	 * setCurrencyName(String currencyName) { this.currencyName = currencyName;
	 * }
	 * @Override protected NumberFormat getNumberFormat(Locale locale) {
	 * DecimalFormat format = (DecimalFormat)
	 * NumberFormat.getCurrencyInstance(locale);
	 * format.setCurrency(Currency.getInstance(currencyName)); format.
	 * format.setParseBigDecimal(true);
	 * format.setMaximumFractionDigits(fractionDigits);
	 * format.setMinimumFractionDigits(this.fractionDigits); if
	 * (this.roundingMode != null && roundingModeOnDecimalFormat) {
	 * format.setRoundingMode(this.roundingMode); } if (this.currency != null) {
	 * format.setCurrency(this.currency); } return format; }
	 */

	// @Override
	// public BigDecimal parse(String text, Locale locale) throws ParseException
	// {
	// NumberFormatter formatter = new NumberFormatter();
	// NumberFormat format = formatter.getNumberFormat(locale);
	// ParsePosition position = new ParsePosition(0);
	// Number number = format.parse(text, position);
	// if (position.getErrorIndex() != -1) {
	// throw new ParseException(text, position.getIndex());
	// }
	// if (!this.lenient) {
	// if (text.length() != position.getIndex()) {
	// // indicates a part of the string that was not parsed
	// throw new ParseException(text, position.getIndex());
	// }
	// }
	//
	// BigDecimal decimal = (BigDecimal) number;
	//
	// if (decimal != null) {
	// if (this.roundingMode != null) {
	// decimal = decimal.setScale(this.fractionDigits, this.roundingMode);
	// } else {
	// decimal = decimal.setScale(this.fractionDigits);
	// }
	// }
	// return decimal;
	// }

}
