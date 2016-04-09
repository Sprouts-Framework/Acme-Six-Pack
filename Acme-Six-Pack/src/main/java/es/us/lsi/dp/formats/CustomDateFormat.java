package es.us.lsi.dp.formats;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class CustomDateFormat extends CustomFormat {

	@Inject
	protected MessageSource messageSource;

	public CustomDateFormat(String codePrefix, Class<?> type, String field) {
		super(codePrefix, type, field);
	}

	@Override
	public SimpleDateFormat getFormat() {
		String dateFormatStr;
		String codeDateFormatStr = getCodePrefix() + "date.format";
		Locale locale;

		locale = LocaleContextHolder.getLocale();

		dateFormatStr = messageSource.getMessage(codeDateFormatStr, null, locale);

		return new SimpleDateFormat(dateFormatStr);
	}

}
