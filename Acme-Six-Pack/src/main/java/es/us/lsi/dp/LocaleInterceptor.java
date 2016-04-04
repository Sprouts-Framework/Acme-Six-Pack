package es.us.lsi.dp;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LocaleInterceptor extends HandlerInterceptorAdapter {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Locale thisLocale = LocaleContextHolder.getLocale();
		if(thisLocale.getCountry().equals("")){
			Locale spanish = new Locale("es", "ES");
			Locale english = new Locale("en", "US");
			
			if (thisLocale.getLanguage().equals("es"))
				LocaleContextHolder.setLocale(spanish);
			else if (thisLocale.getLanguage().equals("en"))
				LocaleContextHolder.setLocale(english);
		}
		return true;
	}
}
